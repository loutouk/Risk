
import { Component, OnInit } from '@angular/core';
import {WebsocketService} from '../websocket.service';
import {DatastoreService} from '../datastore.service';
import { Router } from '@angular/router';
import {Player} from '../classes/player';
import {Country} from '../classes/model/country';
import {Continent} from '../classes/model/continent';
import {GameContent} from '../classes/model/gamecontent';
import * as $ from 'jquery';
import * as raph from 'raphael';
import * as config from '../config.json';



@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

    gameContent : GameContent;

    countriesObj = new Array<Country>();

    myTurn : boolean;
    
  
    constructor(
        private router: Router,
        private websocket: WebsocketService,
        private datastore: DatastoreService
    ) { }

    ngOnInit() {
        //this.gameContent = this.datastore.getGameContent();
        this.initSVG();
        this.loadConf();
        //this.websocket.getGameSubscription().subscribe(content => this.handleGameContent(JSON.parse(content.body)));
    }

    ngOnDestroy()  {
    }

    // Gère l'arrivée de nouvelles données sur la partie en provenance du serveur
    handleGameContent(gameContent){
        this.gameContent = new GameContent(gameContent);
        this.datastore.setGameContent(this.gameContent);
        //this.feedGameContent();
    }

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

    loadConf(){        
        let continents = new Array<Continent>();  
        let allCountries = new Array<Country>();    
        config.continents.forEach(function (continent) {
            let continentId = continent.id;
            let countries = new Array<Country>();
            continent.pays.forEach(function(pays){               
                let adjs = new Array<String>();
                pays.voisins.forEach(function(voisin){
                   adjs.push(voisin.id);
                });
                let country = new Country(pays.id,adjs);
                allCountries.push(country);
                countries.push(country);                
            });
            let continentObj = new Continent(continentId,countries);
            continents.push(continentObj);
        });
        this.datastore.setGameContent(new GameContent(continents));
        this.gameContent=this.datastore.getGameContent();
        this.countriesObj = allCountries;
    }

    initSVG(){
        this.svg = document.getElementById('mapsvg');  

        var labelSelect1 = this.newElement( 'text', 'id=labelSelect1 font-size=30 stroke=none fill=black text-insert=middle x=100 y=700' );
        var textSelect = document.createTextNode( 'Selection 1 : ' );
        labelSelect1.appendChild( textSelect );
        this.svg.appendChild( labelSelect1 );

        var labelSelection2 = this.newElement( 'text', 'id=labelSelection2 font-size=30 stroke=none fill=black text-insert=right x=100 y=750' );
        var textLabelSel2 = document.createTextNode( 'Selection 2 : ' );
        labelSelection2.appendChild( textLabelSel2 );
        this.svg.appendChild(labelSelection2);


        var selection1 = this.newElement( 'text', 'id=labelSelection1 font-size=30 stroke=none fill=black text-insert=right x=400 y=700' );
        this.textSel1 = document.createTextNode( 'Over a country' );
        selection1.appendChild( this.textSel1 );
        this.svg.appendChild( selection1 );

        var selection2 = this.newElement( 'text', 'id=labelSelection1 font-size=30 stroke=none fill=black text-insert=right x=400 y=750' );
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
            
        for (var i = 0; i < this.countries.length; i++)
        {                        
            this.countries[i].addEventListener('mouseover',this.overCountryListener, false);            
        }            
        this.highlightCountry.addEventListener('click', this.clickCountryListener, false);

    }

    resetMouseEvents(){
        for (var i = 0; i < this.countries.length; i++)
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
    newElement( type, attrs )
    {
        var result = document.createElementNS( "http://www.w3.org/2000/svg", type );
        if( result )
        {
            var attr = attrs.split( ' ' );
            for( var i = 0; i < attr.length; i++ )
            {
                var value = attr[i].split( '=' );
                result.setAttribute( value[0], value[1] );
            }
        }
        return result;
    }
    clickCountry(evt)
    {        
        if(this.selectedCountry1==null){
            this.selectedCountry1=this.hoveredCountry;
            this.textSel1.textContent=this.selectedCountry1.getAttribute('id');
            this.resetMouseEvents();
            this.selectedCountry1.addEventListener('click',this.clickCountryListener,false);
            this.highlightVoisins.forEach(voisin => {
                voisin.removeEventListener('mouseover',this.overVoisinsListener,false);
                voisin.addEventListener('mouseover',this.overCountryListener,false);
                voisin.addEventListener('click',this.clickCountryListener,false);
            });

        }else if(evt.target.getAttribute('id')=='highlightCountry'){
            this.selectedCountry1 = null;
            this.selectedCountry2 = null;
            this.textSel1.textContent='Over a country';
            this.textSel2.textContent='';
            this.resetMouseEvents();
            this.resetVoisins();
            this.highlightCountry.setAttribute('d','');
            this.initMouseEvents();
        }else{
            var id1 = this.textSel1.textContent;
            var vid = evt.target.getAttribute('id');
            var id2 = vid.substring(1);  

            //DO SOMETHING

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
            var i = 0;
            while(this.countriesObj[i].id != id){
                i++;
            }            
            for(var j = 0 ; j <this.countriesObj[i].adjCountries.length;j++){
                var voisin = document.createElementNS( "http://www.w3.org/2000/svg", 'path');
                if(voisin){
                    voisin.setAttribute('class','voisin');
                    voisin.setAttribute('id','v'+document.getElementById(this.countriesObj[i].adjCountries[j]).getAttribute('id'))
                    voisin.setAttribute('d',document.getElementById(this.countriesObj[i].adjCountries[j]).getAttribute('d'));
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
