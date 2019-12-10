/*
  Service utilisé pour communiquer avec le serveur via websocket
*/
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { fromEvent } from 'rxjs';
import {environment} from '../environments/environment';
import { Game } from './classes/game';
import { Player } from './classes/player';

declare var require: any;
const Stomp = require("stompjs/lib/stomp.js").Stomp;

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private stompClient;

  private creationSubscription;

  private gameSubscription;

  constructor() { }

  // Connexion au websocket du serveur
  public connect(): void{
    this.stompClient = Stomp.client(environment.websocketRegisterUrl);
    this.stompClient.connect({},function(frame){
      // Callback for connection
    });
  }

  // Inscription au canal websocket de la partie passée en paramètre
  public startGameSubscription(game) {
    let that = this;
    let obs =
    Observable.create(function(observer){
      that.stompClient.subscribe("/game/"+game.id,function(data){
        observer.next(data);
      });
    });

    this.gameSubscription = obs;
  }

  // Recupération de l'observateur sur le canal websocket de la partie, permet de savoir si un message arrive sur le canal
  public getGameSubscription(): Observable<any>
  {
    return this.gameSubscription;
  }
  
  // Appel au serveur pour créer une partie
  public createGame(player: Player,game: Game): Observable<any>{
    let that = this;
    let obs =
    Observable.create(function(observer){
      this.creationSubscription =
      that.stompClient.subscribe('/com/createGame/'+game.key+"/"+player.name+"/"+player.key,
      function(data){
        observer.next(data);
      });
    });

    return obs;
  }

}
