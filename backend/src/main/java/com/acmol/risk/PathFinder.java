package com.acmol.risk;

/**
 * Interface for verifying paths for the fortify action
 *
 * Please see the {@link com.acmol.risk.BasicPathfinder} class for the basic rule
 * Please see the {@link com.acmol.risk.AlternatePathfinder} class for the alternate rule
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public interface PathFinder {
    /**
     * Check if there is a valid connexion regarding a player's territories
     * @param territoryA from A
     * @param territoryB to B
     * @return true if there exist a valid connexion between the given territories, false otherwise
     */
    boolean availablePath(Territory territoryA, Territory territoryB);
}
