import { Component, OnInit } from '@angular/core';
import { EncodeService } from 'app/service/encode.service';
import { NameIndex } from './models/NameIndex';

@Component({
  selector: 'app-search-screen',
  templateUrl: './search-screen.component.html',
  styleUrls: ['./search-screen.component.scss']
})
export class SearchScreenComponent implements OnInit {
  algorithms =[]
  selectedAlgorithm:string = "Caverphone2";
  selectedlastNameAlgorithm:string;
  fnamearr:Array<NameIndex>;
  snamearr:Array<NameIndex>
  fnameValue:string = "";
  snameValue:string = "";
  sname:Array<String> = [];
  sencoded:Array<String> = [];
  fname:Array<String> = [];
  fencoded:Array<String> = [];
  fcounter:Array<Number> = [];
  scounter:Array<Number> = [];
  fnameEmpty:string;
  snameEmpty:string;

  constructor(
    private encodeService:EncodeService
  ) {
    this.fnamearr = []
    this.snamearr = []
    this.algorithms = [];
   }
  ngOnInit(): void {
    this.encodeService.NameIndexFetchAlgos().subscribe(
      (data:Array<Algorithm>)=>{
        this.algorithms = [];
        data.forEach((ele)=>{
          this.algorithms.push({value:ele.name});
        })
      }
    )
  }

  freset(){
    this.fname = [];
    this.fcounter = [];
    this.fname = [];
    this.fencoded = [];
  }

  sreset(){
    this.sname = [];
    this.scounter = [];
    this.sname = [];
    this.sencoded = [];
  }

  reset(){
    this.freset();
    this.sreset();
  }


  fetchfnameresult(){
    this.freset();
    this.encodeService.
    NameIndexSearchByFName(this.selectedAlgorithm,this.fnameValue)
    .subscribe((data:Array<NameIndex>) =>{
      if (data.length == 0){
        this.fnameEmpty = "Empty";
      }
      else{
      this.fnameEmpty = "";
      }
      let curIndex = 0;
      console.log(data);
      data.forEach(ele=>{
        let nameArray:Array<string> = JSON.parse(ele.name_json);
        let encodeArray:Array<string> = JSON.parse(ele.encode_json);
        this.fcounter.push(curIndex);
        curIndex += 1;
        this.fname.push(ele.name);
        this.fencoded.push(ele.encode);
        for(let i = 0 ; i < nameArray.length ; i++){
            this.fname.push(nameArray[i]);
            this.fencoded.push(encodeArray[i]);
            this.fcounter.push(curIndex);
            curIndex += 1;
        }
      })
    });

  }

  fetchsnameresult(){
    this.sreset();
    this.encodeService.
    NameIndexSearchBySName(this.selectedlastNameAlgorithm,this.snameValue)
    .subscribe((data:Array<NameIndex>) =>{
      if (data.length == 0){
        this.snameEmpty = "Empty";
      }
      else{
        this.snameEmpty="";
      }
      let curIndex = 0;
      data.forEach(ele=>{
        this.scounter.push(curIndex);
        curIndex += 1;
        this.sname.push(ele.name);
        this.sencoded.push(ele.encode);
        let nameArray:Array<string> = JSON.parse(ele.name_json);
        let encodeArray:Array<string> = JSON.parse(ele.encode_json);
        for(let i = 0 ; i < nameArray.length ; i++){
            this.sname.push(nameArray[i]);
            this.sencoded.push(encodeArray[i]);
            this.scounter.push(curIndex);
            curIndex += 1;
        }
      })

    });
  }
}
