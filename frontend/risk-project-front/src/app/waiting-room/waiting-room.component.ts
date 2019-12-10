import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Observable, of} from 'rxjs';
import {WebsocketService} from '../websocket.service';
import {DatastoreService} from '../datastore.service';
import {Game} from '../classes/game';
import {Player} from '../classes/player';
import {environment} from '../../environments/environment';
import { LobbyContent } from '../classes/model/lobbycontent';

@Component({
    selector: 'app-waiting-room',
    templateUrl: './waiting-room.component.html',
    styleUrls: ['./waiting-room.component.css']
  })
  export class WaitingRoomComponent implements OnInit {

    serverUrl : string;

    lobbyContent : LobbyContent;

    starting : boolean;

    //subscription: any;  

    constructor(
        private router: Router,
        private websocket: WebsocketService,
        private datastore: DatastoreService
      )
      { }
    
      ngOnInit() {
           this.serverUrl = environment.serverUrl;
           this.lobbyContent.allPlayersReady=false;
           this.lobbyContent.enoughPlayerToPlay=false;          
           //this.subscription = this.websocket.getWaitingSubscription().subscribe(content => this.handleWaitingSub(JSON.parse(content.body)));
      }
    
      ngOnDestroy()
      {
        //this.subscription.unsubscribe();
      }

      handleWaitingSub(data){           
            this.lobbyContent= new LobbyContent(data);       
            if(this.lobbyContent.allPlayersReady && this.lobbyContent.enoughPlayerToPlay){
                this.starting = true;
                this.datastore.setPlayers(this.lobbyContent.players);
                setTimeout(() => 
                {
                    this.router.navigate(["/game"]);
                },
                5000);                
            }
      }
    }