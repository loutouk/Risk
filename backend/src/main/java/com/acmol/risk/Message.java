package com.acmol.risk;

/**
 * Message supertype for the client response.
 *
 * Please see the {@link com.acmol.risk.GameMessage} class for subtype
 * Please see the {@link com.acmol.risk.StringMessage} class for subtype
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
public abstract class Message {

	public Object content;
	public String id;

	public Message(Object content, String id) {
		this.content = content;
		this.id = id;
	}

}
