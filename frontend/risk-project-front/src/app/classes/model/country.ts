export class Country
{
    id: string;    
    namePlayer : string;
    nbArmee : number;
    adjCountries : string[];

    constructor(id, adjCountries)
    {
        this.id = id;       
        this.adjCountries = adjCountries;
    }

    setIdPlayer(namePlayer){
        this.namePlayer=namePlayer;
    }
    setNbArmee(nbArmee){
        this.nbArmee=nbArmee;
    }

}
