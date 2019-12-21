package com.acmol.risk;

import java.util.ArrayList;

/**
 * Interface for allocating the army at the beginning of a game.
 *
 * Please see the {@link com.acmol.risk.RandomInitializer} class for the random case
 * Please see the {@link com.acmol.risk.ManualInitializer} class for the manual case
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public interface ArmyInitializer {
    // Maps the number of player to the number of army unit they start with
    int[] STARTING_ARMY_PER_PLAYER_NB = {0,0,35,30,25,20};
    int MIN_PLAYER = 3;
    int MAX_PLAYER = STARTING_ARMY_PER_PLAYER_NB.length;

    /**
     *  Allocate the army for the initialization
     * @param continents all the game's Continent
     * @param players all the game's Player
     * @throws IncorretPlayerNumber if there are too many or too few players for STARTING_ARMY_PER_PLAYER_NB to handle
     */
    void initArmy(ArrayList<Continent> continents, Player[] players) throws IncorretPlayerNumber;

    class IncorretPlayerNumber extends Exception {
        public IncorretPlayerNumber(String message)
        {
            super(message);
        }
    }
}
