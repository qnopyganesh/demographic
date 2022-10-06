import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
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
  @ViewChild("baraccuracychart", { static: true }) baraccuracychart: ChartComponent;
  public type:ApexChart;
  public chartOptions: Partial<ChartOptions>;
  public namefile:Map<string,any>;
  public key:string = "Caverphone";
  public ready:boolean = false;
  public selectedAlgorithm:string;
  @Input() source:string;
  @Input() title:string;
  public algorithms = [
    { value: "Caverphone1" },
    { value: "Caverphone2" },
    { value: "Caverphone" },
    { value: "DaitchMokotoffSoundex" },
    { value: "ColognePhonetic" },
    { value: "DoubleMetaphone" },
    {value : "DaitchMokotoffSoundex"},
    { value: "MatchRatingApproachEncoder" },
    { value: "Metaphone" },
    { value: "Metaphone3" },
    { value: "Nysiis" },
    { value: "RefinedSoundex" },
    { value: "Soundex" },
  ];


  public x = [];
  public y = [];
  series:ApexAxisChartSeries;
  constructor(private fileservice:FileReadingService,private httpclient:HttpClient) {
    this.type = {
      height: 350,
      type: "bar",
      zoom: {
        enabled: true
      }
    };
    this.series = [
      {
        name: this.key,
        data: [1,2,3,40]
      }
    ],
    this.initializechart();
  }
  initializechart(){
    this.baraccuracychart = new ChartComponent();

    this.chartOptions = {
      series: this.series,
      chart: this.type,
      dataLabels: {
        enabled: false
      },
      stroke: {
        curve: "straight"
      },
      title: {
        text: this.title,
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
    if(this.ready){
      this.reset1();
    }
    
  }

  reset1(){
    this.chartOptions.series[0].data = this.namefile.get(this.key)[1];
    this.chartOptions.series[0].name = this.key;
    this.chartOptions.xaxis.categories = this.namefile.get(this.key)[0];
  }
  ngOnInit(): void {
    let namefile = new Map();
    this.httpclient.get(this.source,{responseType:'text'})
    .subscribe(
      data => {
        let csvToRowArray = data.split("\n");
        for (let index = 1; index < csvToRowArray.length - 1; index++) {
          let row = csvToRowArray[index].split(",");
          if(!namefile.has(row[0])){
            namefile.set(row[0],[[Number(row[1]).toFixed(3)],[Number(row[2].replace('\r','')).toFixed(3)]]);
          }
          else{
            namefile.get(row[0])[0].push(Number(row[1]).toFixed(3));
            namefile.get(row[0])[1].push(Number(row[2].replace('\r','')).toFixed(3));
          }
        }
        this.namefile = namefile;
        this.ready = true;
        this.series = [];
        namefile.forEach((value,key)=>{
          this.series.push({
            name: key,
            data: value[1]
          });
        })
        this.initializechart();
      },
    error => {
    }
    );
  }
  reset(event){
    console.log((document.getElementById("inlineRadio1") as HTMLInputElement).checked);
    this.initializechart();
  }
  changeCharttoBar(st){
    this.type = {
      height: 350,
      type: "bar",
      zoom: {
        enabled: true
      }
    }
    this.initializechart();
  }
  changeCharttoLine(st){
    this.type = {
      height: 350,
      type: "line",
      zoom: {
        enabled: true
      }
    }
    this.initializechart();
  }
}
