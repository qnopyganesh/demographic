import { Component, OnInit } from '@angular/core';
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
  selector: 'app-visual-data',
  templateUrl: './visual-data.component.html',
  styleUrls: ['./visual-data.component.scss']
})
export class VisualDataComponent implements OnInit {
  opt1 = null
  opt2 = null
  constructor() { }

  ngOnInit(): void {
    this.opt1 = true;
    this.opt2 = false;
  }



  

}
