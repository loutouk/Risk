import {Country} from './country';

export class Continent
{
    id: string;  
    countries : Country[];

    constructor(id, countries)
    {
        this.id = id;       
        this.countries = countries;
    }
}
