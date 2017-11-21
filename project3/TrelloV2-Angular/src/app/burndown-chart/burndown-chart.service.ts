import { Http } from "@angular/http";
import { Injectable } from "@angular/core";

import 'rxjs/add/operator/toPromise';
import 'rxjs/Rx';
import { BurndownChart } from "./burndown-chart";


@Injectable()
export class BurndownChartService {

    private chartURL = 'burndown/data';
    
    constructor(private http: Http) { }

    getChart(body: any){
        return this.http.post(this.chartURL, body)
        .map(response => <BurndownChart[]> response.json())
        .do(data => console.log(data))
        .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
      }
}