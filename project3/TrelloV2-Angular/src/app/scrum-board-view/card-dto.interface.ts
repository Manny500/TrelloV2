export class CardDto{  
    cId: number;
    cVerify: number;
    cWorth: number;
    bId: number;
    bTotal: number;
    lId: number;
    cTitle: string;
    cDescription: string;
    
    constructor(){
        this.cId = 0,
        this.cVerify = 0;
        this.cWorth = 0;
        this.bId = 0;
        this.bTotal = 0;
        this.lId = 0;
        this.cTitle = "";
        this.cDescription = "";
    }
} 