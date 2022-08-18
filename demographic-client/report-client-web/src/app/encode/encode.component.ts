import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-encode",
  templateUrl: "./encode.component.html",
  styleUrls: ["./encode.component.scss"],
})
export class EncodeComponent implements OnInit {
  constructor() {}
  cars = [
    { id: 1, name: "Volvo" },
    { id: 2, name: "Saab" },
    { id: 3, name: "Opel" },
    { id: 4, name: "Audi" },
  ];
  selectedCar: number;

  ngOnInit(): void {}
}
