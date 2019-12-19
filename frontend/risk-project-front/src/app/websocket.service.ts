/*
  Service utilisé pour communiquer avec le serveur via websocket
*/
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {environment} from '../environments/environment';
import {DatastoreService} from './datastore.service';
import { Player } from './classes/player';

declare var require: any;
const Stomp = require("stompjs/lib/stomp.js").Stomp;
var SockJs = require("sockjs-client");

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private stompClient;

  private gameSubscription;

  private connectionSubscription;

  private player : Player;


  constructor( private datastore: DatastoreService) { }

  // Connexion au websocket du serveur
  public connect(): void{
    let socket = new SockJs(environment.websocketRegisterUrl);
    this.stompClient = Stomp.over(socket); // = "ws://localhost:8080/connection"
    let that = this;
    this.stompClient.connect({}, frame => {
    });   
  }

  public getPlayer():Player{
    return this.player;
  }
  public setPlayer(player:Player){
    this.player=player;
  }
  
  startGameSubscription():Observable<any>{
    let that = this;
    let obs =
    Observable.create(function(observer){
      that.stompClient.subscribe("/topic/game",function(data){
        observer.next(data);
      });
    });    
    this.gameSubscription=obs;
    return obs;
  }
 
  startConnectionSubscription(playerName): Observable<any>{
    let that = this;
    let obs =
    Observable.create(function(observer){
      this.connectionSubscription =
      that.stompClient.subscribe('/topic/connectionId/'+playerName,
      function(data){       
        observer.next(data);
      });
    });   
    return obs;
  }
  skipAttack(){
    this.stompClient.send('/app/skipattack');
  }

  skipFortify(){
    this.stompClient.send('/app/skipfortify');
  }

  putReinforce(stringReinforce){
    this.stompClient.send('/app/putreinforce',{},stringReinforce);
  }
  sendConnection(playerName){
    this.stompClient.send('/app/connection/'+playerName);
  }

  attack(stringAttack){
    this.stompClient.send('/app/attack',{},stringAttack);
  }

  fortify(stringFortify){
    this.stompClient.send('/app/fortify',{},stringFortify);
  }
  startGame(){
    this.stompClient.send('/app/launch');
  }
 
  actualizePlayers(){
    this.stompClient.send('/app/actualizeplayers');
  }

  // Recupération de l'observateur sur le canal websocket de la partie, permet de savoir si un message arrive sur le canal
  public getGameSubscription(): Observable<any>
  {
    return this.gameSubscription;
  }  

  public getConnectionSubscription():any
  {
    return this.connectionSubscription;
  }
  /*that.readySubscription(name).subscribe(function(content){
        // Le serveur renvoi au client un id qui lui est propre
        //(affiche le message sur la page)
        console.log("what");
        console.log(content);
        console.log("what");
        let jsonContent = JSON.parse(content.body);        
        this.player.id = jsonContent.id;
        console.log("caca : "+this.player.id);
        this.datastore.setPlayer(this.player);
        // Le client subscribe au channel /game/
        this.websocket.startGameSubscription().subscribe(content => {
          // Le serveur envoie manuellement une réponse quand tous les joueurs souhaités ont rejoint
          // Et retourne une nouvelle partie initialisée
          let gameContent = JSON.parse(content.body);
          this.datastore.setGameContent(new GameContent().update(gameContent));
          this.router.navigate(["game"]);
        });       
        
        
      });*/
}
