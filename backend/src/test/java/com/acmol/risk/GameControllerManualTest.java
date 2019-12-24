package com.acmol.risk;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class GameControllerManualTest {

    private GameController gameController;
    private Continent america;
    private Continent africa;
    private Continent asia;
    private Territory russia;
    private Territory china;
    private Territory mexico;
    private Territory canada;
    private Territory unitedStates;
    private Player playerA;
    private Player playerB;
    private Player playerC;

    @Before
    public void initTestCase() {
        gameController = new GameController();

        this.playerA = new Player("a", "a");
        this.playerB = new Player("b", "b");
        this.playerC = new Player("c", "c");

        gameController.players = new HashMap<>();
        gameController.players.put("a", playerA);
        gameController.players.put("b", playerB);
        gameController.players.put("c", playerC);

        ArrayList<Continent> continents = new ArrayList<>();
        this.america = new Continent("america");
        continents.add(america);
        this.africa = new Continent("africa");
        continents.add(africa);
        this.asia = new Continent("asia");
        continents.add(asia);

        ArrayList<Territory> americaTerritories = new ArrayList<>();
        this.canada = new Territory("canada");
        americaTerritories.add(canada);
        this.unitedStates = new Territory("unitedStates");
        americaTerritories.add(unitedStates);
        this.mexico = new Territory("mexico");
        americaTerritories.add(mexico);
        Territory brasil = new Territory("brasil");
        americaTerritories.add(brasil);
        america.territories = americaTerritories;

        ArrayList<Territory> africaTerritories = new ArrayList<>();
        Territory madagascar = new Territory("madagascar");
        africaTerritories.add(madagascar);
        Territory nigeria = new Territory("nigeria");
        africaTerritories.add(nigeria);
        Territory egypt = new Territory("egypt");
        africaTerritories.add(egypt);
        Territory somalia = new Territory("somalia");
        africaTerritories.add(somalia);
        africa.territories = africaTerritories;

        ArrayList<Territory> asiaTerritories = new ArrayList<>();
        Territory india = new Territory("india");
        asiaTerritories.add(india);
        this.china = new Territory("china");
        asiaTerritories.add(china);
        this.russia = new Territory("russia");
        asiaTerritories.add(russia);
        Territory yemen = new Territory("yemen");
        asiaTerritories.add(yemen);
        asia.territories = asiaTerritories;

        canada.neighbors.add(unitedStates);
        unitedStates.neighbors.add(canada);

        canada.neighbors.add(russia);
        russia.neighbors.add(canada);

        unitedStates.neighbors.add(mexico);
        mexico.neighbors.add(unitedStates);

        mexico.neighbors.add(brasil);
        brasil.neighbors.add(mexico);

        brasil.neighbors.add(nigeria);
        nigeria.neighbors.add(brasil);

        yemen.neighbors.add(nigeria);
        nigeria.neighbors.add(yemen);

        yemen.neighbors.add(egypt);
        egypt.neighbors.add(yemen);

        yemen.neighbors.add(madagascar);
        madagascar.neighbors.add(yemen);

        yemen.neighbors.add(india);
        india.neighbors.add(yemen);

        china.neighbors.add(russia);
        russia.neighbors.add(china);

        china.neighbors.add(india);
        india.neighbors.add(china);


        gameController.continents = continents;
    }

    @Test
    public void isEliminatedShouldReturnTrue01() {
        for(Territory t : america.territories) { t.owner = playerA; }
        for(Territory t : africa.territories) { t.owner = playerA; }
        for(Territory t : asia.territories) { t.owner = playerB; }
        assertEquals(true, gameController.isEliminated(gameController.players.get("c")));
    }

    @Test
    public void isEliminatedShouldReturnFalse01() {
        for(Territory t : america.territories) { t.owner = playerA; }
        for(Territory t : asia.territories) { t.owner = playerB; }
        assertEquals(false, gameController.isEliminated(gameController.players.get("a")));
        assertEquals(false, gameController.isEliminated(gameController.players.get("b")));
    }

    @Test
    public void findTerritoryByIdShouldReturnNull() {
        assertEquals(null, gameController.findTerritoryById("doesNotExist"));
    }

    @Test
    public void findTerritoryByIdShouldReturnTerritory() {
        assertEquals(russia, gameController.findTerritoryById("russia"));
    }

    @Test
    public void rollTheDiceShouldReturnBoundedValues() {
        for(int i=0 ; i<100 ; i++) {
            int diceValue = gameController.rollTheDice();
            assertTrue("Dice value is too high",diceValue <= gameController.DICE_MAX_VAL);
            assertTrue("Dice value is too low",diceValue >= gameController.DICE_MIN_VAL);
        }
    }

    @Test
    public void moveUnitShouldReturnFalse01() {
        // does not possess territory to move from and they are neighbors
        russia.owner = playerB;
        russia.army = 10;
        china.owner = playerA;
        assertEquals(false, gameController.moveUnit("a", russia, china, 5));
    }

    @Test
    public void moveUnitShouldReturnFalse02() {
        // does not possess territory to move to and they are neighbors
        russia.owner = playerA;
        russia.army = 10;
        china.owner = playerB;
        assertEquals(false, gameController.moveUnit("a", russia, china, 5));
    }

    @Test
    public void moveUnitShouldReturnFalse03() {
        // leaves fewer than 1 unit on the territory to move from and they are neighbors
        russia.owner = playerA;
        russia.army = 10;
        china.owner = playerA;
        assertEquals(false, gameController.moveUnit("a", russia, china, 10));
    }

    @Test
    public void moveUnitShouldReturnFalse04() {
        // fulfill every conditions except there is no direct path between territories (not neighbors)
        russia.owner = playerA;
        russia.army = 10;
        mexico.owner = playerA;
        assertEquals(false, gameController.moveUnit("a", russia, mexico, 5));
    }


    @Test
    public void moveUnitShouldReturnTrue01() {
        russia.owner = playerA;
        russia.army = 10;
        china.owner = playerA;
        assertEquals(false, gameController.moveUnit("a", russia, mexico, 5));
    }

    @Test
    public void winnerShouldReturnNull01() {
        // one continent remains
        for(Territory t : america.territories) { t.owner = playerA; }
        for(Territory t : africa.territories) { t.owner = playerA; }
        for(Territory t : asia.territories) { t.owner = playerB; }
        assertEquals(null, gameController.winner());
    }

    @Test
    public void winnerShouldReturnNull02() {
        // one territory remains
        for(Territory t : america.territories) { t.owner = playerA; }
        for(Territory t : africa.territories) { t.owner = playerA; }
        for(Territory t : asia.territories) { t.owner = playerA; }
        mexico.owner = playerB;
        assertEquals(null, gameController.winner());
    }

    @Test
    public void winnerShouldReturnWinnerPlayer() {
        for(Territory t : america.territories) { t.owner = playerC; }
        for(Territory t : africa.territories) { t.owner = playerC; }
        for(Territory t : asia.territories) { t.owner = playerC; }
        assertEquals(playerC, gameController.winner());
    }

    @Test
    public void getPlayerTerritories() {
        for(Territory t : america.territories) { t.owner = playerC; }
        for(Territory t : africa.territories) { t.owner = playerC; }
        for(Territory t : asia.territories) { t.owner = playerB; }
        assertEquals(true, gameController.getPlayerTerritories("c").containsAll(america.territories));
        assertEquals(true, gameController.getPlayerTerritories("c").containsAll(africa.territories));
        assertEquals(false, gameController.getPlayerTerritories("c").containsAll(asia.territories));
    }

    @Test
    public void reinforcementUnitAvailable01() {
        // No territory
        for(Territory t : america.territories) { t.owner = playerC; }
        for(Territory t : africa.territories) { t.owner = playerC; }
        for(Territory t : asia.territories) { t.owner = playerC; }
        assertEquals(gameController.MIN_REINFORCE_UNITS, gameController.reinforcementUnitAvailable("b"));
    }

    @Test
    public void reinforcementUnitAvailable02() {
        // A lot of territories
        for(Territory t : america.territories) { t.owner = playerA; }
        for(Territory t : africa.territories) { t.owner = playerA; }
        for(Territory t : asia.territories) { t.owner = playerA; }
        int totalTerritoryNumber = america.territories.size() + africa.territories.size() + asia.territories.size();
        assertEquals(totalTerritoryNumber / 3, gameController.reinforcementUnitAvailable("a"));
    }
}
