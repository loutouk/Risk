/*
  Classe contenant les informations communes au lobby en cours
*/
import {Player} from '../player';

export class LobbyContent {

  enoughPlayerToPlay : boolean;
  allPlayersReady : boolean;
  players : Array<Player>;
  playersReady : Array<Player>;

  constructor(data: any)
  {
    this.players = data.connectedPlayers;
    this.playersReady = data.connectedPlayersReady;
    if(this.players.length > 1){
        this.enoughPlayerToPlay = true;
    }
    if(this.playersReady.length == this.players.length){
        this.allPlayersReady = true;
    }
  }  
}
