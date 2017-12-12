import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import 'rxjs/Rx';
import { BurndownChart } from "./burndown-chart";

@Injectable()
export class BurndownChartService {


    private chartURL = 'burndown/data';
    
    url: string;
    urlEndpoint: string;
    creds: String;
    updatedUser: string;
  
    headers = new Headers({
        "Content-Type": "application/json",
        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('currentToken')).token
    });
  
    options = new RequestOptions({ headers: this.headers });
  
    constructor(private http: Http){}

    getChart(body: any){ 
        return this.http.post(this.chartURL, body,this.options)
        .map(response => <BurndownChart[]> response.json())
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
      }
}