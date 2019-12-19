package com.acmol.risk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Class for allocating the army at the beginning of a game. The allocation is done randomly all in once by the server
 *
 * Please see the {@link com.acmol.risk.ArmyInitializer} interface
 * Please see the {@link com.acmol.risk.ManualInitializer} class for the manual case
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public class RandomInitializer implements ArmyInitializer {

    /**
     * Allocate the army for the initialization. The allocation is done randomly all in once by the server
     * @param continents all the game's Continent
     * @param players all the game's Player
     * @throws IncorretPlayerNumber if there is too much players according to the STARTING_ARMY_PER_PLAYER_NB array
     */
    @Override
    public void initArmy(ArrayList<Continent> continents, Player[] players) throws IncorretPlayerNumber {

        if(players.length > ArmyInitializer.MAX_PLAYER || players.length < ArmyInitializer.MIN_PLAYER) {
            throw new IncorretPlayerNumber(
                    "The number of player is " + players.length +
                    " but it should be between " + ArmyInitializer.MIN_PLAYER +
                    " and " + ArmyInitializer.MAX_PLAYER);
        }

        int currentPlayer = 0; // index on the player we are allocating resoures
        int availableArmy = RandomInitializer.STARTING_ARMY_PER_PLAYER_NB[players.length];
        HashMap<Player, ArrayList<Territory>> territoryOwner = new HashMap<>();
        HashMap<Player, Integer> armyAllocation = new HashMap<>();
        for(Player player : players) {
            territoryOwner.put(player, new ArrayList<>());
            armyAllocation.put(player, 0);
        }

        // Start by allocating each territory to a player, one territory per player at a time
        for(Continent continent : continents) {
            for(Territory territory : continent.territories) {
                ArrayList modified = territoryOwner.get(players[currentPlayer]);
                modified.add(territory);
                territoryOwner.put(players[currentPlayer], modified);
                territory.owner = players[currentPlayer];
                armyAllocation.put(players[currentPlayer], armyAllocation.get(players[currentPlayer]) + 1);
                territory.army = 1;
                currentPlayer = (currentPlayer + 1) % players.length; // next player
            }
        }

        // Then allocate remaining armies on the player's territories
        for(Player player : players) {
            boolean allocationDone = false;
            while(armyAllocation.get(players[currentPlayer]) < availableArmy && ! allocationDone) {
                for (Territory territory : territoryOwner.get(player)) {
                    if(armyAllocation.get(player) >= availableArmy) {
                       allocationDone = true;
                    } else {
                        armyAllocation.put(players[currentPlayer], armyAllocation.get(players[currentPlayer]) + 1);
                        territory.army = territory.army + 1;
                    }
                    if(new Random().nextInt(10) > 5) {
                        continue; // randomly changes territory to allocate army randomly
                    }
                }
            }
            currentPlayer = (currentPlayer + 1) % players.length; // next player
        }

    }
}
