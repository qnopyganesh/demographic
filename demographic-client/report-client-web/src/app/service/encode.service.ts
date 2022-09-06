import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { tap } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class EncodeService {
  constructor(private http: HttpClient) {}
  truestring:string = 'true';
  falsestring:string = 'false';
  url: String = "http://localhost:9001/encode/get";
  newUrl:String = "http://localhost:9001/NameIndex/get/";
  httpOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/json" }),
  };

  encode(algorithm: String, name: String) {
    return this.http.post<any>(
      this.url + `?algorithm=${algorithm}&name=${name}`,
      this.httpOptions
    );
  }

  NameIndexSearchByFName(algorithm: String, name: String) {
    return this.http.get<any>(
      this.newUrl + `${algorithm}/${name}/${this.falsestring}`,
      this.httpOptions
    );
  }
  NameIndexSearchBySName(algorithm: String, name: String) {
    return this.http.get<any>(
      this.newUrl + `${algorithm}/${name}/${this.truestring}`,
      this.httpOptions
    );
  }

  NameIndexFetchAlgos(){
    return this.http.get<any>(
      this.newUrl + `algo`,
      this.httpOptions
    );
  }
}
