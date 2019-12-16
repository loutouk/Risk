export class Country
{
    id: string;    
    idPlayer : number;
    nbArmee : number;
    adjCountries : string[];

    constructor(id, adjCountries)
    {
        this.id = id;       
        this.adjCountries = adjCountries;
    }

    setIdPlayer(idPlayer){
        this.idPlayer=idPlayer;
    }
    setNbArmee(nbArmee){
        this.nbArmee=nbArmee;
    }

}
