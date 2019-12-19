import { Continent } from './continent';
import { Country } from './country';
import { Player } from '../player';
import * as config from '../../config.json';
// Metadonnées pour une partie
export class GameContent {

    continents : Array<Continent>;
    joueurs : Array<Player>;
    gameIsOver : boolean;
    winnerPlayerName:string;
    gameStarted: boolean;
    currentPlayerNumber:number;
    currentPlayer:any;
    currentActionNumber:number;
    currentAction:any;
    currentReinforce:number;
    gameMessage:string;

    constructor()
    {
      this.continents = new Array<Continent>();    
      let allContinents =  new Array<Continent>();     
      config.continents.forEach(function (continent) {
        let continentId = continent.id;
        let countries = new Array<Country>();
        continent.territories.forEach(function(pays){               
          let adjs = new Array<string>();
          pays.voisins.forEach(function(voisin){
            adjs.push(voisin.id);
          });
          let country = new Country(pays.id,adjs);    
          country.nbArmee=0;      
          countries.push(country);                
        });
        allContinents.push(new Continent(continentId,countries));
      });
      this.continents=allContinents; 
      this.gameStarted=false;
    }    
    
    update(content:any){        
      let that = this;
      content.continents.forEach(function (continent) {
        let continentId = continent.id;
        var i = 0;
        while(that.continents[i].id != continentId && i<that.continents.length){
          i++
        }
        continent.territories.forEach(function(pays){   
          let territoryId = pays.id;            
          var j = 0;
          while(that.continents[i].countries[j].id != territoryId){
            j++;
          }
          that.continents[i].countries[j].namePlayer = pays.owner.name;
          that.continents[i].countries[j].nbArmee = pays.army;                     
        });       
      });

      if(content.gameIsOver != null){
        this.gameIsOver=content.gameIsOver;
      }
      if(content.winnerPlayerName != null){
        this.winnerPlayerName=content.winnerPlayerName;
      }
      if(content.gameStarted != null){
        this.gameStarted=content.gameStarted;
      }
      if(content.currentPlayerNumber != null){
        this.currentPlayerNumber=parseInt(content.currentPlayerNumber,10);
      }
      if(content.currentActionNumber != null){
        this.currentActionNumber=parseInt(content.currentActionNumber,10);
      }
      if(content.currentAction != null){
        this.currentAction=content.currentAction;
      }    

      this.currentReinforce = parseInt(content.currentReinforce,10);
     
      if(content.gameMessage != null){
        this.gameMessage = content.gameMessage;
      }
      else{
        this.gameMessage=null;
      }
    }

    findCountry(id:string):Country{     
      for(let continent of this.continents){
        for(let country of continent.countries){
          if(country.id == id){
           return country;
          }
        }
      }      
      return null;
    }

    getCurrentPlayerName():string{
      for(let player of this.joueurs){
        if(player.number == this.currentPlayerNumber){
          return player.name;
        }
      }
      return "pas trouvé";
    }
  }