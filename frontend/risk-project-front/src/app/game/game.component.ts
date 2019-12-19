
import { Component, OnInit } from '@angular/core';
import {WebsocketService} from '../websocket.service';
import {DatastoreService} from '../datastore.service';
import { Router } from '@angular/router';
import {Player} from '../classes/player';
import {Country} from '../classes/model/country';
import {Continent} from '../classes/model/continent';
import {GameContent} from '../classes/model/gamecontent';





@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

    //Contenu du jeu (assignation pays->joueurs, nbArmee, tour ...)
    gameContent : GameContent;
    
    colorMap : Map<string,string>;    
    
    //Stock la liste des renforts à rajouter
    selectionReinforce : Map<string,number>;

    //Stock la phase sous forme de string pour l'affichage
    gamePhase:string;

    //Vrai si c'est au client courant de jouer
    isMyTurn:boolean;

    //Variable qui stock si le bouton "start" est disponible
    buttonStart:boolean;

    //Stock le nombre de renfort encore disponible à poser
    nbRenfortDisponible:any;

    //Stock le choix d'armées à employer en cas d'attaque/défense
    nbArmeeEmployees : any;

    //Stock le maximum d'armées que le joueur peut employer pour l'attaque courante
    maxNbEmployee:number;

    //Variables pour la gestion de la map
    svg : any;  
    map : any;   
    textSel1 : any;
    textSel2 : any;    
    selectedCountry1 : HTMLElement;
    selectedCountry2 : HTMLElement;
    highlightCountry : HTMLElement;
    highlightVoisins: Array<Element>;
    countries : HTMLCollectionOf<Element>;
    hoveredCountry : HTMLElement;
    hoveredVoisin : HTMLElement;
    clickCountryListener : EventListener;
    overCountryListener : EventListener;
    overVoisinsListener : EventListener;

      // Variables utiles pour la génération de notifications
    warning: boolean;
    warningMessage: string;   


    //TODO Rajouter formulaire avec nb armée à engager et un bouton valider pour envoyer (apparait uniquement quand select2 != null)
    constructor(
        private router: Router,
        private websocket: WebsocketService,
        private datastore: DatastoreService
    ) { }

    ngOnInit() {
        this.initSVG();
        this.gameContent = new GameContent();
        this.buttonStart=false;
        this.websocket.startGameSubscription().subscribe(content =>{
            console.log(content);
            let data = JSON.parse(content.body);
            if(data.id == "newPlayer"){
                let tab = data.content.split(',');
                let players = tab.slice(0,tab.length-1);
                if(players.length>=3) {
                    this.buttonStart=true;
                }
                this.gameContent.joueurs = new Array<Player>();
                for(var i = 0 ; i < players.length ; i++){
                    let playerData = players[i].split(':');
                    let player = new Player(playerData[0]);
                    player.number= parseInt(playerData[1],10)         
                    this.gameContent.joueurs.push(player);
                }   
                this.datastore.setGameContent(this.gameContent);  
                this.feedGameContent();   
            }
            else {               
                this.handleGameContent(JSON.parse(content.body).content);
                if(this.buttonStart && this.gameContent.gameStarted){
                    this.buttonStart=false;
                }
                switch(this.gameContent.currentActionNumber){
                    case 0:
                        this.gamePhase="renfort";
                        break;
                    case 1:
                        this.gamePhase="attaque";
                        break;
                    case 2:
                        this.gamePhase="déplacement";
                        break;
                    default:                      
                        break;
                }
                this.isMyTurn=(this.gameContent.currentPlayerNumber == this.websocket.getPlayer().number);
                if(this.gameContent.gameIsOver){
                    this.resetMouseEvents();
                }
            }
            /*else{
                this.enableWarning("invalide request on actualize received");
            }*/
        });  
      
        this.datastore.setGameContent(this.gameContent);        
        this.feedGameContent();
        this.websocket.actualizePlayers();        
    }

    ngOnDestroy()  {
    }

    // Gère l'arrivée de nouvelles données sur la partie en provenance du serveur
    handleGameContent(newContent){    
        this.gameContent.update(newContent);
        this.datastore.setGameContent(this.gameContent);
        this.isMyTurn =(this.gameContent.currentPlayerNumber == this.websocket.getPlayer().number);
        this.feedGameContent();
        if(this.gameContent.gameMessage != null){
            this.enableWarning(this.gameContent.gameMessage);
        }
    }

    feedGameContent(){   
        if(!this.gameContent.gameStarted){
            if(this.gameContent.joueurs != null){           
                this.colorMap = new Map<string,string>();
                //Autre set de couleurs pour tests   
                var colors:string[];
                colors = ['rgb(148,130,173)','rgb(198,138,49)','rgb(156,195,90)','rgb(214,81,99)','rgb(35,75,195','rgb(222,121,82)'];
                var colors2:string[];
                colors2=['#CEA252','#BDDB8C','#E78A94','#CEBADE','#EFCB73','#E79273'];
                var i=0;
                for(let player of this.gameContent.joueurs){                  
                    this.colorMap.set(player.name,colors2[i]);     
                    document.getElementById('player-name-h2_'+(i+1)).innerHTML=player.name;              
                    i++;
                }                         
            }   
        }    
        for(let continent of this.gameContent.continents){
            for(let pays of continent.countries){
                if(pays.nbArmee != 0){
                    document.getElementById("armee"+pays.id).textContent = String(pays.nbArmee);
                }
                if(pays.namePlayer!=null&& pays.namePlayer!=''){
                    document.getElementById(pays.id).setAttribute("fill",this.colorMap.get(pays.namePlayer));
                }
                else{
                    document.getElementById(pays.id).setAttribute("fill","grey");
                }
            }
        }
    }

    onClickStart(){
        this.websocket.startGame();
    }

    onClickValidate(){
        //attack
        if(this.gameContent.currentActionNumber==1){
            if(this.nbArmeeEmployees>0 && this.nbArmeeEmployees < 4){
                let stringAttack = this.textSel1.textContent+','+this.textSel2.textContent+','+this.nbArmeeEmployees;
                this.websocket.attack(stringAttack);
            }
            else{
                this.enableWarning("Nombre d'armées employées invalide :"+this.nbArmeeEmployees);
            }
        }
        //fortify
        else{
            if(this.nbArmeeEmployees>0){
   
                let stringFortify = this.textSel1.textContent+','+this.textSel2.textContent+','+this.nbArmeeEmployees;
                this.websocket.fortify(stringFortify);               
            }
        }

        this.onClickCancel();
    }

    onClickPass(){
        if(this.isMyTurn){
            switch(this.gameContent.currentActionNumber){
                case 1:
                    this.websocket.skipAttack();
                    break;
                case 2:
                    this.websocket.skipFortify();
                    break;
                default:
                    break;
            }
        }
    }

    onClickCancel(){
        this.textSel1.textContent="";
        this.textSel2.textContent="";
        this.selectedCountry1=null;
        this.selectedCountry2=null;
        this.resetMouseEvents();
        this.resetVoisins();
        this.highlightCountry.setAttribute('d','');
        this.initMouseEvents();
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

    initSVG(){
        this.svg = document.getElementById('mapsvg');  

        var labelSelect1 = this.newElement( 'text', 'id=labelSelect1 font-size=30 stroke=none fill=black text-insert=middle x=100 y=700',true );
        var textSelect = document.createTextNode( 'Selection 1 : ' );
        labelSelect1.appendChild( textSelect );
        this.svg.appendChild( labelSelect1 );

        var labelSelection2 = this.newElement( 'text', 'id=labelSelection2 font-size=30 stroke=none fill=black text-insert=right x=100 y=750',true );
        var textLabelSel2 = document.createTextNode( 'Selection 2 : ' );
        labelSelection2.appendChild( textLabelSel2 );
        this.svg.appendChild(labelSelection2);


        var selection1 = this.newElement( 'text', 'id=labelSelection1 font-size=30 stroke=none fill=black text-insert=right x=400 y=700',true );
        this.textSel1 = document.createTextNode( 'Over a country' );
        selection1.appendChild( this.textSel1 );
        this.svg.appendChild( selection1 );

        var selection2 = this.newElement( 'text', 'id=labelSelection1 font-size=30 stroke=none fill=black text-insert=right x=400 y=750',true );
        this.textSel2 = document.createTextNode( '' );
        selection2.appendChild( this.textSel2 );
        this.svg.appendChild( selection2 );        

        this.highlightCountry = document.getElementById( "highlightCountry" );        
        this.countries = document.getElementsByClassName('country');
        this.highlightVoisins = new Array<Element>();
        this.clickCountryListener = ((evt:any)=>{ this.clickCountry(evt);});
        this.overCountryListener = ((evt:any)=>{ this.mouseoverCountry(evt);});
        this.overVoisinsListener = ((evt:any)=>{evt.target.setAttribute('d',"");});

        this.initMouseEvents();     
    }

    initMouseEvents(){            
        for (let i = 0; i < this.countries.length; i++)
        {                        
            this.countries[i].addEventListener('mouseover',this.overCountryListener, false);            
        }            
        this.highlightCountry.addEventListener('click', this.clickCountryListener, false);
    }

    resetMouseEvents(){
        for (let i = 0; i < this.countries.length; i++)
        {                   
            this.countries[i].removeEventListener('mouseover',this.overCountryListener, false); 
        }
    }

    resetVoisins(){
        var gVoisins = document.getElementById('gVoisins');
        var nodelist = Array.from(document.getElementsByClassName('voisin'));
        nodelist.forEach(e=>{
            gVoisins.removeChild(e);
        });
    }

    // generic function to create an xml element
    // format for attr is very strict
    // attrs is a string of attribute=value pairs separated by single spaces, 
    // no quotes inside the string, no spaces in attributes
    // eg. newElement( 'circle', 'cx=20 cy=20 r=15 visibility=hidden' );
    //
    newElement( type, attrs, bool:boolean)
    {
        let nameSpace = null;
        if(bool){
            nameSpace = "http://www.w3.org/2000/svg"
        }
        var result = document.createElementNS(nameSpace , type );
        if( result )
        {
            var attr = attrs.split( ' ' );
            for( let i = 0; i < attr.length; i++ )
            {
                var value = attr[i].split( '=' );
                result.setAttribute( value[0], value[1] );
            }
        }
        return result;
    }

    clickCountry(evt)
    {   
        if(this.gameContent.gameStarted){
            if(this.isMyTurn){       
                switch(this.gameContent.currentActionNumber){
                    //Reinforcement
                    case 0:
                        this.selectedCountry1=this.hoveredCountry;
                        if(this.selectionReinforce==null){
                            this.selectionReinforce = new Map<string,number>();
                        }
                        let id = this.selectedCountry1.getAttribute('id');
                        let country =  this.gameContent.findCountry(id);
                        if(country.namePlayer  == this.gameContent.getCurrentPlayerName()){                            
                            let nbReinforceForThisCountry = 1;
                            if(this.selectionReinforce.has(id)){
                                nbReinforceForThisCountry+=this.selectionReinforce.get(id);    
                                this.selectionReinforce.delete(id);                        
                            }
                            this.selectionReinforce.set(id,nbReinforceForThisCountry);
                            this.gameContent.findCountry(id).nbArmee++;
                            this.gameContent.currentReinforce--;
                            if(this.gameContent.currentReinforce==0){
                                var stringReinforce="";
                                this.selectionReinforce.forEach((value, key) => 
                                    stringReinforce+=','+key+':'+value);
                                stringReinforce = stringReinforce.substring(1);
                                console.log("stringReinforce : "+stringReinforce);
                                this.websocket.putReinforce(stringReinforce);
                                this.selectionReinforce=null;
                            }
                        }
                        else{
                            this.enableWarning("Vous ne possédez pas ce pays !");
                        }
                        this.selectedCountry1 = null;
                        this.selectedCountry2 = null;
                        this.textSel1.textContent='Over a country';
                        this.textSel2.textContent='';
                        this.resetMouseEvents();
                        this.resetVoisins();
                        this.highlightCountry.setAttribute('d','');
                        this.initMouseEvents();
                        this.feedGameContent();
                        break;
                    //Attack
                    case 1:
                    case 2:
                        if(this.selectedCountry1==null){
                            this.selectedCountry1=this.hoveredCountry;
                            let id1 = this.selectedCountry1.getAttribute('id');
                            if(this.gameContent.findCountry(id1).namePlayer == this.gameContent.getCurrentPlayerName()){
                                this.textSel1.textContent=this.selectedCountry1.getAttribute('id');
                                this.resetMouseEvents();
                                this.selectedCountry1.addEventListener('click',this.clickCountryListener,false);
                                this.highlightVoisins.forEach(voisin => {
                                    voisin.removeEventListener('mouseover',this.overVoisinsListener,false);
                                    voisin.addEventListener('mouseover',this.overCountryListener,false);
                                    voisin.addEventListener('click',this.clickCountryListener,false);
                                });
                            }
                            else{
                                this.enableWarning("Vous ne possédez pas ce pays !");
                                this.selectedCountry1 = null;
                                this.selectedCountry2 = null;
                                this.textSel1.textContent='Over a country';
                                this.textSel2.textContent='';
                                this.resetMouseEvents();
                                this.resetVoisins();
                                this.highlightCountry.setAttribute('d','');
                                this.initMouseEvents();
                            }
        
                        }
                        //reset souris events si on reclique sur le même territoire
                        else if(evt.target.getAttribute('id')=='highlightCountry'){
                            this.selectedCountry1 = null;
                            this.selectedCountry2 = null;
                            this.textSel1.textContent='Over a country';
                            this.textSel2.textContent='';
                            this.resetMouseEvents();
                            this.resetVoisins();
                            this.highlightCountry.setAttribute('d','');
                            this.initMouseEvents();
                        }
                        else{                
                            this.selectedCountry2 = this.hoveredVoisin;
                            let id2 = this.selectedCountry2.getAttribute('id').substring(1);
                            this.resetMouseEvents();
                            this.resetVoisins();
                            this.highlightCountry.setAttribute('d','');    
                            //attack
                            if(this.gameContent.currentActionNumber==1){
                                if(this.gameContent.findCountry(id2).namePlayer != this.gameContent.getCurrentPlayerName()){
                                    this.textSel2.textContent=id2;    
                                    this.maxNbEmployee = Math.min(this.gameContent.findCountry(this.textSel1.textContent).nbArmee-1,3);          
                                }
                                else{
                                    this.enableWarning("Ce territoire vous appartient déjà !");   
                                    this.selectedCountry1 = null;
                                    this.selectedCountry2 = null;
                                    this.textSel1.textContent='Over a country';
                                    this.textSel2.textContent='';     
                                    this.initMouseEvents();    
                                }      
                            }
                            //fortify
                            else if(this.gameContent.currentActionNumber==2){
                                if(this.gameContent.findCountry(id2).namePlayer == this.gameContent.getCurrentPlayerName()){
                                    this.textSel2.textContent=id2;    
                                    this.maxNbEmployee = this.gameContent.findCountry(this.textSel1.textContent).nbArmee-1;          
                                }
                                else{
                                    this.enableWarning("Ce territoire ne vous appartient pas !");   
                                    this.selectedCountry1 = null;
                                    this.selectedCountry2 = null;
                                    this.textSel1.textContent='Over a country';
                                    this.textSel2.textContent='';     
                                    this.initMouseEvents();    
                                }  
                            }  
                            else{ //Cas impossible (pour tests)
                                this.enableWarning("This shouldn't happen");   
                                this.selectedCountry1 = null;
                                this.selectedCountry2 = null;
                                this.textSel1.textContent='Over a country';
                                this.textSel2.textContent='';     
                                this.initMouseEvents();     
                            }       
                        } 
                        break;                   
                    default:
                        //Cas impossible (pour tests)
                        this.enableWarning("This shouldn't happen");   
                        this.selectedCountry1 = null;
                        this.selectedCountry2 = null;
                        this.textSel1.textContent='Over a country';
                        this.textSel2.textContent='';     
                        this.initMouseEvents();     
                        break;
                }
              
            }
            else{
                this.enableWarning("Ce n'est pas votre tour !");
            }   
        }
    }

    mouseoverCountry( evt )      
    {        
        if(this.selectedCountry1==null){     
            this.hoveredCountry = evt.target;
            var id = this.hoveredCountry.getAttribute('id');
            var outline = this.hoveredCountry.getAttribute( 'd' );
            this.highlightCountry.setAttribute( 'd', outline );
            this.textSel1.textContent = id;

            var gVoisins = document.getElementById('gVoisins');
            var nodelist = Array.from(document.getElementsByClassName('voisin'));
            nodelist.forEach(e=>{
                gVoisins.removeChild(e);
            });
            this.highlightVoisins = new Array<Element>();
            let country = this.gameContent.findCountry(id);      
            for(let j = 0 ; j <country.adjCountries.length;j++){
                var voisin = document.createElementNS( "http://www.w3.org/2000/svg", 'path');
                if(voisin){
                    voisin.setAttribute('class','voisin');
                    voisin.setAttribute('id','v'+document.getElementById(country.adjCountries[j]).getAttribute('id'))
                    voisin.setAttribute('d',document.getElementById(country.adjCountries[j]).getAttribute('d'));
                    voisin.addEventListener('mouseover',this.overVoisinsListener,false);
                }
                voisin = gVoisins.appendChild(voisin);
                this.highlightVoisins.push(voisin);
            }
        }
        else{
            if(this.hoveredVoisin==null){
                this.hoveredVoisin=evt.target;
                this.hoveredVoisin.setAttribute('fill','white');
            }else{
                this.hoveredVoisin.removeAttribute('fill');
                this.hoveredVoisin=evt.target;
                this.hoveredVoisin.setAttribute('fill','white');
            }
            this.textSel2.textContent = this.hoveredVoisin.getAttribute('id').substring(1);
        }
    }
  
}
