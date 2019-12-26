/*
  Service utilisé pour communiquer avec le serveur via websocket
*/
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {environment} from '../environments/environment';
import { Player } from './classes/player';

declare var require: any;
const Stomp = require("stompjs/lib/stomp.js").Stomp;
var SockJs = require("sockjs-client");

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  //Stomp Client pour gérer les requêtes avec le serveur
  private stompClient;

  //Observable de la partie en cours
  private gameSubscription;

  //Stock l'instance du joueur connecté
  private player : Player;


  constructor() { }

  // Connexion au websocket du serveur
  public connect(): void{
    let socket = new SockJs(environment.websocketRegisterUrl);
    this.stompClient = Stomp.over(socket);
    let that = this;
    this.stompClient.connect({}, frame => {
    });   
  }

  // Création de l'observable sur le topic /topic/game du serveur
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

  // Création de l'observable sur le topic /game/connectionId du serveur (permet de récupérer son ID)
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

  // Envoie au serveur d'un message pour passer l'étape "attaque" du tour du joueur
  skipAttack(){
    this.stompClient.send('/app/skipattack');
  }

  // Envoie au serveur d'un message pour passer l'étape "déplacement" du tour du joueur
  skipFortify(){
    this.stompClient.send('/app/skipfortify');
  }

  // Envoie au serveur d'une requête de renfort sur une liste de territoires passée en paramètre
  putReinforce(stringReinforce){
    this.stompClient.send('/app/putreinforce',{},stringReinforce);
  }

  // Envoie au serveur d'une demande de connexion (pour permettre d'enregistrer le joueur côté serveur et de renvoyer sur /topic/connectionId/{{playerName}} l'id du joueur)
  sendConnection(playerName){
    this.stompClient.send('/app/connection/'+playerName);
  }

  // Envoie au serveur d'une requête d'attaque sur un territoire ennemi
  attack(stringAttack){
    this.stompClient.send('/app/attack',{},stringAttack);
  }

  // Envoie au serveur d'une requête de déplacement d'armée entre deux territoires adjacents possédés
  fortify(stringFortify){
    this.stompClient.send('/app/fortify',{},stringFortify);
  }

  // Envoie au serveur un message pour lancer la partie (quand assez de joueurs ont rejoins)
  startGame(){
    this.stompClient.send('/app/launch');
  }
 
  // Envoie au serveur un message pour demander l'actualisation de la liste des joueurs à tous les joueurs connectés
  actualizePlayers(){
    this.stompClient.send('/app/actualizeplayers');
  }

  //Getters & Setters

  public getGameSubscription(): Observable<any>
  {
    return this.gameSubscription;
  }  

  public getPlayer():Player{
    return this.player;
  }

  public setPlayer(player:Player){
    this.player=player;
  }  
}
