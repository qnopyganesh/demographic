import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FileReadingService } from 'app/service/filereading.service';
import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexDataLabels,
  ApexTitleSubtitle,
  ApexStroke,
  ApexGrid
} from "ng-apexcharts";

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  dataLabels: ApexDataLabels;
  grid: ApexGrid;
  stroke: ApexStroke;
  title: ApexTitleSubtitle;
};
@Component({
  selector: 'app-line-chart-view',
  templateUrl: './line-chart-view.component.html',
  styleUrls: ['./line-chart-view.component.scss']
})
export class LineChartViewComponent implements OnInit{
  public chart: ChartComponent;
  public chartOptions: Partial<ChartOptions>;
  public namefile:Map<string,any>;
  public key:string = "Caverphone";
  public ready:boolean = false;
  public selectedAlgorithm:string;
  public algorithms = [];


  public x = [];
  public y = [];
  constructor(private fileservice:FileReadingService,private httpclient:HttpClient) {
    this.chart = new ChartComponent();
    this.chartOptions = {
      series: [
        {
          name: "Desktops",
          data: [1,2,3,40]
        }
      ],
      chart: {
        height: 350,
        type: "bar",
        zoom: {
          enabled: false
        }
      },
      dataLabels: {
        enabled: false
      },
      stroke: {
        curve: "straight"
      },
      title: {
        text: "Product Trends by Month",
        align: "left"
      },
      grid: {
        row: {
          colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
          opacity: 0.5
        }
      },
      xaxis: {
        categories: [1,2,4,5]
      }
    };
  }
  ngOnInit(): void {
    let namefile = new Map();
    this.httpclient.get('assets/data/accuercy_name.csv',{responseType:'text'})
    .subscribe(
      data => {
        let csvToRowArray = data.split("\n");
        for (let index = 1; index < csvToRowArray.length - 1; index++) {
          let row = csvToRowArray[index].split(",");
          if(!namefile.has(row[0])){
            namefile.set(row[0],[[Number(row[1])],[Number(row[2].replace('\r',''))]]);
          }
          else{
            namefile.get(row[0])[0].push(Number(row[1]));
            namefile.get(row[0])[1].push(Number(row[2].replace('\r','')));
          }
        }
        this.namefile = namefile;
        console.log(this.namefile);
        this.ready = true;
        this.chartOptions.series[0].data = this.namefile.get(this.key)[1];
        this.chartOptions.xaxis.categories = this.namefile.get(this.key)[0];
        this.chart.updateOptions(this.chartOptions);
        this.algorithms = Array.from(namefile.keys());
        console.log(this.algorithms);
      },
    error => {
      console.log(error);
    }
    );
  }
  reset(){
    if(this.namefile.has(this.key)){
      this.chartOptions.series[0].data = this.namefile.get(this.key)[1];
      this.chartOptions.xaxis.categories = this.namefile.get(this.key)[0];
    }
  }
}
