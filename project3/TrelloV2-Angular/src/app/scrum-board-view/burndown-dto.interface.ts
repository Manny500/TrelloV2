import { Card } from "./card-display.interface";
import { Lane } from "./lane-display.interface";

export class BurndownDto{  
    bId: number;
    
    cards: Card[];

    lanes: Lane[];
    
    constructor(){
      
    }
} 