import { Continent } from './continent';

// Metadonnées pour une partie
export class GameContent {

    continents : Continent[];
  
    constructor(continents:Continent[])
    {      
      this.continents=continents;
    }
  
  }