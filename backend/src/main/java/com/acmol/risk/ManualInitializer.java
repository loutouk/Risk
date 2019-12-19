package com.acmol.risk;

import java.util.ArrayList;

/**
 * Class for allocating the army at the beginning of a game. The allocation is done turn by turn by the players
 *
 * Please see the {@link com.acmol.risk.ArmyInitializer} interface
 * Please see the {@link com.acmol.risk.RandomInitializer} class for the random case
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public class ManualInitializer implements ArmyInitializer {

    /**
     * Allocate the army for the initialization. The allocation is done turn by turn by the players
     * @param continents all the game's Continent
     * @param players all the game's Player
     * @throws IncorretPlayerNumber if there is too much players according to the STARTING_ARMY_PER_PLAYER_NB array
     */
    @Override
    @Deprecated
    public void initArmy(ArrayList<Continent> continents, Player[] players) throws IncorretPlayerNumber {
        // TODO delete the class or implement it
    }
}
