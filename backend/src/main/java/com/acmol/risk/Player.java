package com.acmol.risk;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * Class that handles the Player object attributes.
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public class Player {

    public String name;
    @JsonIgnore
    public String sessionId;
    public int playerNumber;
    public ArrayList<Card> cards;
    @JsonIgnore
    public static int playerNumberCount = 0;

    public Player(String name, String sessionId) {
        this.playerNumber = Player.playerNumberCount++;
        this.name = name;
        this.sessionId = sessionId;
        this.cards = new ArrayList<>();
    }
}
