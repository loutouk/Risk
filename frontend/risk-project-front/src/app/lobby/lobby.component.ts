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
    id: null ,
    number:null 
  };
  
  connectionSubscription: any;   

  // Variables utiles pour la génération de notifications
  warning: boolean;
  warningMessage: string;   
    

  constructor(
    private router: Router,
    private websocket: WebsocketService,
    private datastore: DatastoreService

  ) { }

  ngOnInit() {
    this.websocket.connect();
  }
  ngOnDestroy()
  {
    //this.websocket.getConnectionSubscription().unsubscribe();
  }

  onClickReady(){ 
    // Appelle le serveur et lui donne le pseudo du joueur et attend la réponse    
      /*
      this.websocket.connectionSubscription(this.player.name).subscribe((msg)=>{
        this.handleResponse(msg);       
      });    */
      let that = this;
      
      if(this.player.name){       
        this.websocket.startConnectionSubscription(this.player.name).subscribe((msg)=>{
          let data = JSON.parse(msg.body);
          if(data.id == 'ok'){
            that.player.number = parseInt(data.content,10);           
            that.websocket.setPlayer(this.player);
            that.router.navigateByUrl("/game");
          }
          else if(data.id=='started'){
            that.enableWarning("Game is already started !");
          }
        });
        this.websocket.sendConnection(this.player.name);
      }
  }

  // Active une notification avec le message contenu dans le paramètre msg
  enableWarning(msg: string)
  {
    this.warning = true;
    this.warningMessage = msg;
    let that = this;
    setTimeout(function(){that.disableWarning();},7000);
  }
   
  disableWarning()
  {
    this.warning = false;
    this.warningMessage = "";
  }  
   
}
