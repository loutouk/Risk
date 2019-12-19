package com.acmol.risk;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * Class that handles the Territory object attributes.
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public class Territory {

    public int army;
    public String id;
    public Player owner;

    @JsonIgnore
    public ArrayList<Territory> neighbors;

    public Territory(String id) {
        this.id = id;
        this.army = 0;
        this.owner = null;
        this.neighbors = new ArrayList<Territory>();
    }
}
