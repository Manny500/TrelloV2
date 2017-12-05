import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { ChartsModule } from 'ng2-charts';
import { Http } from "@angular/http";

import { BurndownChartService } from './burndown-chart.service';
import { BaseChartDirective } from 'ng2-charts/ng2-charts';
import { BurndownChart } from './burndown-chart';

import 'rxjs/add/operator/toPromise';
import 'rxjs/Rx';
import { DatePipe } from '@angular/common';
import { max } from 'rxjs/operators/max';

@Component({
  selector: 'app-burndown-chart',
  templateUrl: './burndown-chart.component.html',
  styleUrls: ['./burndown-chart.component.css']
})
export class BurndownChartComponent implements OnInit {

  @ViewChild(BaseChartDirective) chart: BaseChartDirective;

  myData: BurndownChart[];
  sortedItems: BurndownChart[];
  labels: any[] = [];
  total: any[] = [];

  lineChartData: Array<any> = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Burndown Line' },
  ];

  lineChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

  lineChartOptions: any = {
    responsive: true
  };

  ineChartColors: Array<any> = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // dark grey
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    },
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];

  lineChartLegend: boolean = true;

  lineChartType: string = 'line';

  constructor(private chartService: BurndownChartService, private datePipe: DatePipe) { }

  ngOnInit() {

    this.diplayChart();
    
  }

  getLatestChart(listOfCharts: BurndownChart[]){
    var currentLatest;
    var latest = 0;
    var latestChart;
    listOfCharts.forEach(item => {
      currentLatest = item.chartId;
      if(currentLatest > latest){
        latest = currentLatest;
        latestChart = item;
      }
    })
    return latestChart;
  }

  diplayChart(): void {

    var currentDate = this.datePipe.transform(new Date(), 'dd-MMM-yy');

    var boardId = localStorage.getItem("currentBoardId");

    const body = {boardId: boardId};

    this.chartService.getChart(body).subscribe(result => {
            
            this.myData = result;
          
            this.myData.forEach(item => {
              item.chartDate = this.datePipe.transform(new Date(item.chartDate), 'dd-MMM-yy');
              
            });
            
            var latestChartData = this.getLatestChart(this.myData);
            

            //filter out all non-unique dates;
            this.myData = this.myData.filter(item => item.chartDate !== currentDate)
            this.myData.push(latestChartData) // add the chart that was updated last for the current day
            

            this.sortedItems = this.myData.sort((a: BurndownChart, b: BurndownChart) =>
              // new Date(a.chartDate).getDate() - new Date(b.chartDate).getDate()
              a.chartId - b.chartId
            );
      
            this.sortedItems.forEach(item => {
              this.total.push(item.chartSum);
            });
      
            this.sortedItems.forEach(item => {
              
              this.labels.push(item.chartDate);
            });
      
            this.lineChartData = [
              { data: this.total, label: 'Burndown Line' },
            ];
      
            this.lineChartLabels = this.labels;
      
            this.chart.chart.config.data.labels = this.lineChartLabels;
      
          });
  }

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }

}