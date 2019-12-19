package com.acmol.risk;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * WebServer endpoint for players. Handles client's queries, give them to the controller that responds with an answer.
 *
 * Please see the {@link com.acmol.risk.GameController} to see the game controller it is connected to.
 * Please see the {@link com.acmol.risk.Message} to see the answer type.
 *
 * @author LASHERME Loic, FILAUDEAU Eloi, NANTIER Matthias, BOURSIER Louis
 */
@Controller
public class ServerController {
	
	public GameController gameController = new GameController();

	@MessageMapping("/launch")
	@SendTo("/topic/game")
	public Message launch(@Header("simpSessionId") String playerId) throws Exception {
		return gameController.launchGame(playerId);
	}	
	
	//Message for getting an ID when connecting
	@MessageMapping("/connection/{playerName}")
	@SendTo("/topic/connectionId/{playerName}")
	public StringMessage onConnection(@Header("simpSessionId") String playerId, @DestinationVariable("playerName") String playerName) {
	    return gameController.addPlayer(playerId, playerName);
	}
	
	
	@MessageMapping("/actualizeplayers")
	@SendTo("/topic/game")
	public StringMessage onActualizePlayers(@Header("simpSessionId") String playerId) {
        return gameController.getPlayersData(playerId);
	}
	
    @MessageMapping("/putreinforce")
    @SendTo("/topic/game")
    public Message putreinforce(@Header("simpSessionId") String playerId, String config) throws Exception {
        return gameController.putReinforce(playerId, config);
    }
    @MessageMapping("/skipattack")
    @SendTo("/topic/game")
    public Message skipattack(@Header("simpSessionId") String playerId) throws Exception {
        return gameController.skipAttack(playerId);
    }
    @MessageMapping("/attack")
    @SendTo("/topic/game")
    public Message attack(@Header("simpSessionId") String playerId, String config) throws Exception {
        return gameController.attack(playerId, config);
    }

    @MessageMapping("/fortify")
    @SendTo("/topic/game")
    public Message fortify(@Header("simpSessionId") String playerId, String config) throws Exception {
        return gameController.fortify(playerId, config);
    }

    @MessageMapping("/skipfortify")
    @SendTo("/topic/game")
    public Message skipfortify(@Header("simpSessionId") String playerId) throws Exception {
        return gameController.skipFortify(playerId);
    }

}
