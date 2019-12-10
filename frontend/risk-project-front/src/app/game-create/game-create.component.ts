/*
  Composant pour représenter la création d'une partie
*/
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Observable, of} from 'rxjs';
import { Player } from '../classes/player';
import { Game } from '../classes/game';
import {WebsocketService} from '../websocket.service';
import {DatastoreService} from '../datastore.service';
import {v4} from 'uuid';


@Component({
  selector: 'app-game-create',
  templateUrl: './game-create.component.html',
  styleUrls: ['./game-create.component.css']
})
export class GameCreateComponent implements OnInit {

  static uuidv4 = require('uuid/v4');

  player: Player = {
    name: '',
    id: null,
    key: ''
  };

  game: Game = {
    id: null,
    key: v4()
  }

  constructor(
    private router: Router,
    private websocket: WebsocketService,
    private datastore: DatastoreService

  ) { }

  ngOnInit() {
    console.log("key : "+this.game.key);
  }

  // Gestion de la réponse du serveur, les métadonnées de la partie sont transmises au service datastore
  handleCreationResponse(msg){
    let data = JSON.parse(msg.body); 
    //this.game.id = data.gameId;
    //this.player.id = data.playerId;  
    //this.datastore.setGame(this.game); 
    //this.datastore.addPlayer(this.player);
    //this.websocket.startGameSubscription(data.gameId);
    this.router.navigate(["/waitingRoom"]);
  }

  onClickCreate(){
    if(this.player.name && this.player.key)
    {
      /*
      // Appelle le serveur et attends la réponse
      this.websocket.createGame(this.player,this.game).subscribe((msg)=>{
        // Le serveur renvoie les informations sur la partie
        this.handleCreationResponse(msg);
      });
      */
    }
  }

}
