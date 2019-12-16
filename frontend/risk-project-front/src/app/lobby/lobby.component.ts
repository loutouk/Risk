// Composant représentant l'accueil de l'application, route par défaut
import { Component, OnInit } from '@angular/core';
import {WebsocketService} from '../websocket.service';
import {DatastoreService} from '../datastore.service';
import { Router } from '@angular/router';
import {Player} from '../classes/player';
import {GameContent} from '../classes/model/gamecontent';


@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
  styleUrls: ['./lobby.component.css']
})
export class LobbyComponent implements OnInit {
  player: Player = {
    name: '',
    id: null   
  };
  
  readySubscription: any;   

  constructor(
    private router: Router,
    private websocket: WebsocketService,
    private datastore: DatastoreService

  ) { }

  ngOnInit() {
  }
  ngOnDestroy()
  {
    this.readySubscription.unsubscribe();
  }

  onClickReady(){ 
      this.websocket.connect();       
      // Appelle le serveur et lui donne le pseudo du joueur et attend la réponse
      this.readySubscription = this.websocket.readySubscription(this.player.name).subscribe(content => {
        // Le serveur renvoi au client un id qui lui est propre
        let data = JSON.parse(content.body);
        this.player.id = data.playerId;
        this.datastore.setPlayer(this.player);
        // Le client subscribe au channel /game/
        this.websocket.startGameSubscription().subscribe(content => {
          // Le serveur envoie manuellement une réponse quand tous les joueurs souhaités ont rejoint
          // Et retourne une nouvelle partie initialisée
          let gameContent = JSON.parse(content.body);
          this.datastore.setGameContent(new GameContent(gameContent));
          this.router.navigate(["game"]);
        });        
      });  
  }

}
