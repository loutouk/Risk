package com.acmol.risk;

/**
 * Message subtype for giving the client a response with the game board information that interests the client.
 *
 * Please see the {@link com.acmol.risk.GameMessage} class for supertype
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public class GameMessage extends Message {

    public GameMessage(GameController content, String id) {
        super(content, id);
    }

}
