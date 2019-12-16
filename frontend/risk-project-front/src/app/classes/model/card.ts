import {Country} from './country';

export abstract class Card
{
    id: number;
    type: string;    
    country : Country;

    constructor(id, type, country)
    {
        this.id = id;
        this.type = type;      
        this.country = country;
    }
}
