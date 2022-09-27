import { Component, OnInit } from '@angular/core';
import { FileReadingService } from 'app/service/filereading.service';

@Component({
  selector: 'app-bar-chart-view',
  templateUrl: './bar-chart-view.component.html',
  styleUrls: ['./bar-chart-view.component.scss']
})
export class BarChartViewComponent implements OnInit {

  constructor(private ser:FileReadingService) { }
  ngOnInit(): void {
    console.log(this.ser.readnamefile().get("Caverphone"));
  }

}
