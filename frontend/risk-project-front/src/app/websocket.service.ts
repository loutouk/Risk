/*
  Service utilisé pour communiquer avec le serveur via websocket
*/
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {environment} from '../environments/environment';


declare var require: any;
const Stomp = require("stompjs/lib/stomp.js").Stomp;

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private stompClient;

  private gameSubscription;

  constructor() { }

  // Connexion au websocket du serveur
  public connect(): void{
    this.stompClient = Stomp.client(environment.websocketRegisterUrl);
    this.stompClient.connect({},function(frame){
      // Callback for connection
    });
  }


  public startGameSubscription(): Observable<any>  {
    let that = this;
    let obs =
    Observable.create(function(observer){
      that.stompClient.subscribe("/game/",function(data){
        observer.next(data);
      });
    });

    this.gameSubscription = obs;
    return obs;
  }


  public readySubscription(playerName): Observable<any> {
    let that = this;
    let obs =
    Observable.create(function(observer){
      that.stompClient.subscribe("/ready/"+playerName,function(data){
        observer.next(data);
      });
    });    
    return obs;
  }

  // Recupération de l'observateur sur le canal websocket de la partie, permet de savoir si un message arrive sur le canal
  public getGameSubscription(): Observable<any>
  {
    return this.gameSubscription;
  }  
}
