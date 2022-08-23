import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { tap } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class EncodeService {
  constructor(private http: HttpClient) {}

  url: String = "http://localhost:9001/encode/get";
  httpOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/text" }),
    responseType: "text" as "text",
  };

  encode(algorithm: String, name: String) {
    return this.http.post<string>(
      this.url + `?algorithm=${algorithm}&name=${name}`,
      this.httpOptions
    );
  }
}
