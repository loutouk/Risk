/*
  Service utilisé pour conserver les données de l'application entre les pages
*/
import { Injectable } from '@angular/core';
import { Player } from './classes/player';
import { GameContent } from './classes/model/gamecontent';



@Injectable({
  providedIn: 'root'
})
export class DatastoreService {

  private gameContent : GameContent;

  private player : Player;
  

  constructor() {  }  

   public getGameContent(): GameContent
  {
    return this.gameContent;
  }

  public setGameContent(gameContentData)
  {
    this.gameContent = gameContentData;
  }

  public setPlayer(player_data){
    this.player = player_data;
  }

  public getPlayer(): Player
  {
    return this.player;
  }
}
