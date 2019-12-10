/*
  Service utilisé pour conserver les données de l'application entre les pages
*/
import { Injectable } from '@angular/core';
import { Player } from './classes/player';
import { Game } from './classes/game';


@Injectable({
  providedIn: 'root'
})
export class DatastoreService {

  // Métadonnées des joueurs
  private players: Array<Player>;


  private player : Player;

  // Métadonnées de la partie
  private game: Game;


  constructor() {
    this.players = Player[6];
   }

  public setGame(game_data)
  {
    this.game = game_data;
  }

  public getGame(): Game
  {
    return this.game;
  }

  public addPlayer(player_data)
  {
    if(this.players.length < 6 && !this.players.find(player_data)){
      this.players.push(player_data);
    }
  }

  public setPlayer(player_data){
    this.player = player_data;
  }

  public setPlayers(player_data){
    this.players = player_data;
  }

  public getPlayer(): Player
  {
    return this.player;
  }

  public getPlayers(): Array<Player>
  {
    return this.players;
  }

  public getNbPlayers(): number
  {
    return this.players.length;
  }
}
