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

 

  constructor() {  }  

   public getGameContent(): GameContent
  {
    return this.gameContent;
  }

  public setGameContent(gameContentData)
  {
    this.gameContent = gameContentData;
  }
}
