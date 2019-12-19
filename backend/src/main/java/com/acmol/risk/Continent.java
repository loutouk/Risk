package com.acmol.risk;

import java.util.ArrayList;

/**
 * A Continent that contains its Territory list.
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public class Continent {

    public ArrayList<Territory> territories;
    public String id;

    public Continent(String id) {
        this.id = id;
        this.territories = new ArrayList<>();
    }

    /**
     * Search a Territory by id
     * @param id a string that uniquely identifies a Territory
     * @return null if no Territory matches the id, the Territory otherwise
     */
    public Territory findTerritoryById(String id) {
        for(Territory t : territories) {
            if(t.id.equals(id)) {
                return t;
            }
        }
        return null;
    }
}
