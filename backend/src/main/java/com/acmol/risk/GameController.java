package com.acmol.risk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Controller that handles the RISK game logic.
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public class GameController {

    @JsonIgnore
    public final int MAX_PLAYERS = 6;
    @JsonIgnore
    public final int MIN_PLAYERS = 3;
    @JsonIgnore
    public final int MAX_UNIT_PER_ATTACK = 3;
    @JsonIgnore
    public static final String MAP_CONFIG_FILE = "mapconfig.json";
    @JsonIgnore
    public static final String[] ACTIONS = {"REINFORCE", "ATTACK", "FORTIFY", "FINISH"};
    @JsonIgnore
    public final int DICE_MIN_VAL = 1;
    @JsonIgnore
    public final int DICE_MAX_VAL = 6;
    @JsonIgnore
    public final int MIN_REINFORCE_UNITS = 3;

    public boolean gameIsOver;
    public String winnerPlayerName;
    @JsonIgnore
    public HashMap<String, Player> players;
    public boolean gameStarted;
    public int currentPlayerNumber;
    public int currentActionNumber;
    public ArrayList<Continent> continents;
    public String gameMessage;
    public int currentReinforce;
    
    public GameController() {
        this.players = new HashMap<>();
        this.gameStarted = false;
        this.currentPlayerNumber = 0;
        this.currentActionNumber = 0;
        this.gameIsOver = false;
        this.winnerPlayerName = "No winner";
        this.continents = new ArrayList<>();
        this.gameMessage = "";
        this.currentReinforce=0;
    }

    /**
     * Used when a player join the server to play a game
     * @param playerId the unique id that identifies a player, allocated by the server
     * @param playerName the player name, chosen by the player, and used for display
     * @return a text message with the player number and if the connection succeed or failed
     */
    public StringMessage addPlayer(String playerId, String playerName) {
        if(gameStarted){
            return new StringMessage("Game already started","started");
        } else if (players.size() >= MAX_PLAYERS){
            return new StringMessage(playerName + " wants to connect but server is full", "error");
        } else {
        	Player newPlayer = new Player(playerName,playerId);
            players.put(playerId, newPlayer);
            return new StringMessage(Integer.toString(newPlayer.playerNumber), "ok");
        }
    }

    /**
     * Used for the clients to see the others players name and number
     * @param playerId unique player id
     * @return a simple text message with the display name and number of all the players
     */
    public StringMessage getPlayersData(String playerId) {
        if(players.containsKey(playerId)) {
            StringBuilder resp = new StringBuilder();
            for(Player p : players.values()){
                resp.append(p.name).append(":").append(p.playerNumber).append(",");
            }
            return new StringMessage(resp.toString(), "newPlayer");
        }
        else {
            return new StringMessage("Not authorized","error");
        }
    }

    /**
     * Used when one of the connected client wanted to start a game
     * @param playerId the player the triggered the game start
     * @return a GameMessage with game data if the game started, a simple StringMessage with error information otherwise
     */
    public Message launchGame(String playerId) {
        if(players.get(playerId) == null) {
            return new StringMessage("An unauthorized player tried to start the game.", "error");
        } else if(players.size() < MIN_PLAYERS){
            return new StringMessage(players.size() + " connected but " + MIN_PLAYERS + " players are required", "error");
        } else if(players.size() > MAX_PLAYERS){
            return new StringMessage(players.size() + " connected but only " + MAX_PLAYERS + " is supported", "error");
        } else {
            return initGame();
        }
    }

    /**
     * Search a Player by its game turn number
     * @param number the player's turn number we are interested in
     * @return null if no Player matches the number, the Player otherwise
     */
    public Player findPlayerByNumber(int number) {
        for(Player p : players.values()) {
            if(p.playerNumber == number) {
                return p;
            }
        }
        return null;
    }

    /**
     * Load game information about the continents and territories from a json configuration file
     * @return a GameMessage with game data if the game started, a simple StringMessage with error information otherwise
     */
	public Message initGame() {
        JSONParser jsonParser = new JSONParser();
        // Territories considered as continent's inland  and as neighbors should have the same reference when the object is the same
        // This is to allow operations working on reference to work properly. Ex: continents.contains(territory)
        HashMap<String, Territory> createdTerritories = new HashMap<>(); // To memorize and reuse already created objects
        try (FileReader reader = new FileReader(MAP_CONFIG_FILE))
        {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            JSONArray continents = (JSONArray) obj.get("continents");
            continents.forEach( continent -> {
                JSONObject continentObj = (JSONObject) continent;
                Continent c = new Continent((String) continentObj.get("id"));
                JSONArray territories = (JSONArray) continentObj.get("pays");
                territories.forEach(territory -> {
                    JSONObject territoryObj = (JSONObject) territory;
                    final Territory t = (createdTerritories.get(territoryObj.get("id").toString()) != null) ?
                            createdTerritories.get(territoryObj.get("id").toString()) :
                            new Territory(territoryObj.get("id").toString());
                    createdTerritories.put(t.id, t);
                    JSONArray neighbors = (JSONArray) territoryObj.get("voisins");
                    neighbors.forEach(neighbor -> {
                        JSONObject neighborObj = (JSONObject) neighbor;
                        Territory tneighbor;
                        if(createdTerritories.get(neighborObj.get("id").toString()) != null){
                            tneighbor = createdTerritories.get(neighborObj.get("id").toString());
                        }else{
                            tneighbor = new Territory((String) neighborObj.get("id"));
                            createdTerritories.put(tneighbor.id, tneighbor);
                        }
                        t.neighbors.add(tneighbor);
                    });
                    c.territories.add(t);
                });
                this.continents.add(c);
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArmyInitializer armyInitializer = new RandomInitializer();
        try {
            // Just need player's data and not the object reference
            armyInitializer.initArmy(continents, players.values().toArray(new Player[0]));
        } catch (ArmyInitializer.IncorretPlayerNumber incorretPlayerNumber) {
            incorretPlayerNumber.printStackTrace();
            return new StringMessage("Can not initialize the  Player number is incorrect. ", "error");
        }
        this.gameStarted=true;
        this.currentReinforce=this.reinforcementUnitAvailable(findPlayerByNumber(currentPlayerNumber).sessionId);
        return new GameMessage(this, "start");
    }


    /**
     * Internal method to compute the number of army unit available for a reinforcement operation
     * @param playerId unique player id
     * @return the number of unit available
     */
    public int reinforcementUnitAvailable(String playerId) {

        // Checks if a player still got territories (may have been eliminated), then it should not receive unit
        // TODO deal with eliminated players in an explicit way to efficiently identify and memorize them
    	for(Continent c : this.continents) {
    		for(Territory t : c.territories) {
    			if(t.owner.playerNumber == this.currentPlayerNumber) {
    			     // A minimum of army per turn is guaranteed whatever the number of territories you own
    				 return Math.max(getPlayerTerritories(playerId).size() / 3, MIN_REINFORCE_UNITS);
    			}
    		}
    	}
    	return 0;
    }

    /**
     * Force the current player to end his turn (if he no longer has any territory)
     * @return a Message with the game context and information about the success or failure of the operation
     */
    public Message finishTurn() {
        currentPlayerNumber = (currentPlayerNumber + 1) % players.size();
        currentActionNumber = 0;
        this.currentReinforce = this.reinforcementUnitAvailable(findPlayerByNumber(currentPlayerNumber).sessionId);
        String nextplayerName = findPlayerByNumber(currentPlayerNumber).name;
        gameMessage = "Time for " + nextplayerName + " to play!";
        return new GameMessage(this, "ok");
    }

    /**
     * For a player reinforcement operation
     * @param playerId unique player id
     * @param config text file to parse that contains the reinforcement operation information
     * @return a Message with the game context and information about the success or failure of the operation
     */
    public Message putReinforce(String playerId, String config) {
        Message message = verifyOperation(playerId, ACTIONS[0]);
        if(message != null) {
            return message;
        }
        Player currentPlayer = findPlayerByNumber(currentPlayerNumber);
        int armyAllocated = 0;
        int armyAvailable = reinforcementUnitAvailable(playerId);
        String[] countriesAndUnits = config.split(",");
        // TODO trim strings to support spaces like CountryA:5,   CountryB:2,  CountryC:7
        for(int i=0 ; i<countriesAndUnits.length ; i++) {
            String[] countryAndUnit = countriesAndUnits[i].split(":");
            if(countryAndUnit.length != 2) {
                gameMessage = "Wrong format in army allocation. Should be countryA:4,countryB:2...";
                return new GameMessage(this, "error");
            } else {
                Territory territory = findTerritoryById(countryAndUnit[0]);
                int armyForTerritory = Integer.valueOf(countryAndUnit[1]);
                if(territory == null) {
                    gameMessage = "Wrong territory name: " + countryAndUnit[0];
                    return new GameMessage(this, "error");
                }
                if(territory.owner != currentPlayer) {
                    gameMessage = "You do not own the territory: " + countryAndUnit[0];
                    return new GameMessage(this, "error");
                }
                if(armyAvailable < armyAllocated + armyForTerritory) {
                    gameMessage = "You do not have enough army. Some reinforcement can not be fulfil.";
                    return new GameMessage(this, "error");
                }
                territory.army += armyForTerritory;
                armyAllocated += armyForTerritory;
            }
        }

        gameMessage = "Reinforcement done with success";
        this.currentReinforce=0;
        currentActionNumber++;
        return new GameMessage(this, "ok");
    }

    /**
     * When a player wants to skip the attack operation. But attacks could have been done before
     * @param playerId unique player id
     * @return a Message with the game context and information about the success or failure of the operation
     */
    public Message skipAttack(String playerId) {
        Message message = verifyOperation(playerId, ACTIONS[1]);
        if(message != null) {
            return message;
        }
        currentActionNumber++;
        gameMessage = "Attack phase is over. Fortify time has come.";
        return new GameMessage(this, "ok");
    }

    /**
     * When a player wants to perform an attack operation
     * @param playerId unqiue player id
     * @param config text file to parse that contains the reinforcement operation information
     * @return a Message with the game context and information about the success or failure of the operation
     */
    public Message attack(String playerId, String config) {
        Message message = verifyOperation(playerId, ACTIONS[1]);
        if(message != null) {
            return message;
        }
        String[] parts = config.split(",");
        // TODO trim strings to support spaces like CountryA:5,   CountryB:2,  CountryC:7
        if(parts.length != 3) {
            gameMessage = "Wrong format. Attack should be like countryA,countryB,unitNumber";
            return new GameMessage(this, "error");
        }
        Territory attackTerritory = findTerritoryById(parts[0]);
        Territory defenseTerritory = findTerritoryById(parts[1]);
        int unitNumber = Integer.parseInt(parts[2]);
        if(attack(attackTerritory, defenseTerritory, unitNumber)){
            Player winnerPlayer = winner();
            if(winnerPlayer != null) {
                winnerPlayerName = winnerPlayer.name;
                gameMessage = "Player " + winnerPlayerName + " won the game!";
                this.gameIsOver=true;
                // TODO restart to handle a new game
                return new GameMessage(this, "ok");
            }
            gameMessage = "Attack executed successfully from " + attackTerritory.id + " to " + defenseTerritory.id + ".";
            return new GameMessage(this, "ok");
        } else {
            gameMessage = "Attack impossible. You should not send more than 3 unit, and leave at least one unit.";
            return new GameMessage(this, "error");
        }

    }

    /**
     * Internal method to check if the client call on the endpoint is legitimate
     * @param playerId played unique id
     * @param action a text to parse that contains information about the attack
     * @return null if the operation is accepted, a Message describing the error otherwise
     */
    public Message verifyOperation(String playerId, String action) {
        if(gameIsOver) {
            gameMessage = "Player " + winnerPlayerName + " won the game!";
            return new GameMessage(this, "error");
        }
        if( ! findPlayerByNumber(currentPlayerNumber).sessionId.equals(playerId)) {
            gameMessage = findPlayerByNumber(currentPlayerNumber).name + " has to play before!";
            return new GameMessage(this, "error");
        }
        if( ! ACTIONS[currentActionNumber].equals(action)) {
            gameMessage = ACTIONS[currentActionNumber] + " operation should be done first!";
            return new GameMessage(this, "error");
        }
        return null;
    }

    /**
     * Internal method to compute if a player can perform a specific attack
     * @param attackTerr the territory of the attacking player
     * @param defenseTerr the territory of the defensing player
     * @param unitInvolved the number of army unit the attacking player wants to use
     * @return true if the attack is authorized, false otherwise
     */
    public boolean attack(Territory attackTerr, Territory defenseTerr, int unitInvolved) {
        ArrayList<Integer> attackDices;
        ArrayList<Integer> defenseDices;
        if (attackTerr.neighbors.contains(defenseTerr) && 
                attackTerr.army > unitInvolved && 
                unitInvolved <= MAX_UNIT_PER_ATTACK && 
                !attackTerr.owner.equals(defenseTerr.owner)) {
            
            attackDices = new ArrayList<>();
            defenseDices = new ArrayList<>();

            for (int i = 0; i < unitInvolved; i++) {
                attackDices.add(rollTheDice());
            }

            defenseDices.add(rollTheDice());
            if (defenseTerr.army > 1 && unitInvolved > 1) {
                defenseDices.add(rollTheDice());
            }

            attackDices.sort(Collections.reverseOrder());
            defenseDices.sort(Collections.reverseOrder());

            for (int i = 0; i < defenseDices.size(); i++) {
                if (defenseDices.get(i) >= attackDices.get(i)) {
                    attackTerr.army--;
                } else {
                    defenseTerr.army--;
                }
            }

            if (defenseTerr.army <= 0) {
                defenseTerr.owner = attackTerr.owner;
                defenseTerr.army = attackTerr.army-1;
                attackTerr.army = 1;
              
            }

        } else {
            return false;
        }
        return true;
    }

    /**
     * To know about a player's territories ownership
     * @param playerId unique player id.
     * @return the list of Territory a player owns. That is where it has at least one unit on it
     */
    public ArrayList<Territory> getPlayerTerritories(String playerId) {
        ArrayList<Territory> territories = new ArrayList<>();
        for (Continent c : continents) {
            for (Territory t : c.territories) {
                if (t.owner.equals(players.get(playerId))) {
                    territories.add(t);
                }
            }
        }
        return territories;
    }

    /**
     * To know the winner Player if there is one
     * @return null if there is no winner yet, the winner Player otherwise
     */
    public Player winner() {
        Player p = null;
        for(Continent c : continents) {
            for(Territory t : c.territories) {
                if(p == null) {
                    p = t.owner;
                }else {
                    if(!p.equals(t.owner)) {
                        return null;
                    }
                }
            }
        }
        return p;
    }

    /**
     * Check if a move operation is correct when a player wants to do the fortification operation
     * @param idPlayer the unique player id
     * @param territoryA move from
     * @param territoryB move to
     * @param unit the number of army unit to move from A to B
     * @return true if the move is accepted, false otherwise
     */
    public boolean moveUnit(String idPlayer, Territory territoryA, Territory territoryB, int unit) {
        if (territoryA.owner == players.get(idPlayer) &&
                territoryB.owner == players.get(idPlayer) &&
                new BasicPathfinder().availablePath(territoryA, territoryB) &&
                unit < territoryA.army) {
            territoryA.army -= unit;
            territoryB.army += unit;
        } else {
            return false;
        }
        return true;
    }

    /**
     * Use when a player wants to do the fortification operation
     * @param playerId unique player id
     * @param config a text to parse that contains information about the fortification
     * @return a Message with the game context and information about the success or failure of the operation
     */
    public Message fortify(String playerId, String config) {
        Message message = verifyOperation(playerId, ACTIONS[2]);
        if(message != null) {
            return message;
        }
        String[] parts = config.split(",");
        // TODO trim strings to support spaces like CountryA:5,   CountryB:2,  CountryC:7
        if(parts.length != 3) {
            gameMessage = "Wrong format. Fortify should be like countryA,countryB,unitNumber";
            return new GameMessage(this, "error");
        }
        Territory terrA = findTerritoryById(parts[0]);
        Territory terrB = findTerritoryById(parts[1]);
        int unitNumber = Integer.parseInt(parts[2]);
        if(moveUnit(playerId, terrA, terrB, unitNumber)) {
            gameMessage = "Fortify from " + terrA.id + " to " + terrB.id + " done with success.";
            return new GameMessage(this, "ok");
        } else {
            gameMessage = "Fortify from " + terrA.id + " to " + terrB.id + " failed.";
            return new GameMessage(this, "error");
        }
    }

    /**
     * When a player is done with the fortification
     * @param playerId unique player id
     * @return a Message with the game context and information about the success or failure of the operation
     */
    public Message skipFortify(String playerId) {
    	 Message message = verifyOperation(playerId, ACTIONS[2]); // double check
         if(message != null) {
             return message;
         }
         currentPlayerNumber = (currentPlayerNumber + 1) % players.size();
         currentActionNumber = 0;
         this.currentReinforce=this.reinforcementUnitAvailable(findPlayerByNumber(currentPlayerNumber).sessionId);

         // If the player reinforcement is null, it means that it has been eliminated, thus we should end its turn
         // TODO deal with eliminated players in an explicit way to efficiently identify and memorize them
         if(this.currentReinforce == 0) {
        	 return this.finishTurn();
         }
         else {
        	 String nextplayerName = findPlayerByNumber(currentPlayerNumber).name;
        	 gameMessage = "Reinforcement phase is over for " + findPlayerByNumber(currentPlayerNumber).name + ".Time for " + nextplayerName + " to play!";
        	 return new GameMessage(this, "ok");       
         }
    }


    /**
     * Internal method that simulate a dice roll
     * @return an integer number bounded with the type of dice we are using
     */
    public int rollTheDice() {
        Random rd = new Random();
        return rd.nextInt(DICE_MAX_VAL - DICE_MIN_VAL + 1) + DICE_MIN_VAL;
    }

    /**
     * Search a Territory by id
     * @param id a string that uniquely identifies a Territory
     * @return null if no Territory matches the id, the Territory otherwise
     */
    public Territory findTerritoryById(String id) {
        for(Continent continent : continents) {
            Territory t = continent.findTerritoryById(id);
            if(t != null) {
                return t;
            }
        }
        return null;
    }
}
