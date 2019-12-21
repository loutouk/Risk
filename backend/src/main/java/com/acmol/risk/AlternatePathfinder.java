package com.acmol.risk;

import java.util.ArrayList;

/**
 * Verify paths for the fortify action considering the alternate rule
 * Alternate Rule: You can move pieces anywhere, as long as the starting point and destination can be reached by going through a string of adjacent territories under your control
 *
 * Please see the {@link com.acmol.risk.PathFinder} interface
 * Please see the {@link com.acmol.risk.BasicPathfinder} class for the standard rule
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public class AlternatePathfinder implements PathFinder {

    /**
     * Check if there is a valid connexion regarding a player's territories according to the alternate rule
     * @param territoryA from A
     * @param territoryB to B
     * @return true if there exist a valid connexion between the given territories, false otherwise
     */
    @Override
    public boolean availablePath(Territory territoryA, Territory territoryB) {
        if(territoryA == null || territoryB == null) {
            return false;
        }
        return recursivePathFinding(territoryA, territoryB, new ArrayList<>());
    }

    private boolean recursivePathFinding(Territory territoryA, Territory territoryB, ArrayList<Territory> exploredTerritories) {
        if (territoryA.equals(territoryB)) {
            return true;
        }
        exploredTerritories.add(territoryA);
        for (Territory t : territoryA.neighbors) {
            if (!exploredTerritories.contains(t)) {
                return recursivePathFinding(t, territoryB, exploredTerritories);
            }
        }
        return false; // useless because of the recursive call done above
    }
}
