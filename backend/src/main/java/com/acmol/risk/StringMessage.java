package com.acmol.risk;

/**
 * Message subtype for giving the client a simple text response.
 *
 * Please see the {@link com.acmol.risk.GameMessage} class for supertype
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public class StringMessage extends Message {

    public StringMessage(String content, String id) {
        super(content, id);
    }

}
