import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { tap } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class FileReadingService{
  constructor(private httpclient:HttpClient){}

  readnamefile():Map<string,[[]]>{
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
    },
    error => {
      console.log(error);
    }
    );
    return namefile;
  }

  readsurnamefile(){
    let namefile = new Map();
    this.httpclient.get('assets/data/accuercy_surname.csv',{responseType:'text'})
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
        return namefile;
    },
    error => {
      console.log(error);
    }
    );
  }
}