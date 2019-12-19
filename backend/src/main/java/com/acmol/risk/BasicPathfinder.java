package com.acmol.risk;

/**
 * Verify paths for the fortify action considering the standard rule
 * Standard Rule: Move any number of army pieces from a single territory into an adjacent territory occupied by you
 *
 * Please see the {@link com.acmol.risk.PathFinder} interface
 * Please see the {@link com.acmol.risk.AlternatePathfinder} class for the alternate rule
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public class BasicPathfinder implements PathFinder {
    /**
     * Check if there is a valid connexion regarding a player's territories according to the standard rule
     * @param territoryA from A
     * @param territoryB to B
     * @return true if there exist a valid connexion between the given territories, false otherwise
     */
    @Override
    public boolean availablePath(Territory territoryA, Territory territoryB) {
        return territoryA.neighbors.contains(territoryB);
    }
}
