import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Role, User } from "app/auth/models";
import { BehaviorSubject } from "rxjs";
import { map, tap } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class EncodeService {
  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(
      JSON.parse(localStorage.getItem("currentUser"))
    );
  }
  truestring: string = "true";
  falsestring: string = "false";
  url: String = "http://localhost:9001/encode/get";
  newUrl: String = "http://localhost:9001/NameIndex/get/";
  searchUserUrl: String = "http://localhost:9001/NameIndex/get/userdetails/";
  httpOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/json" }),
  };
  private currentUserSubject: BehaviorSubject<User>;

  encode(algorithm: String, name: String) {
    if (algorithm == "Phonetic Based Algorithm") {
      algorithm = "Metaphone3";
    }
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

  NameIndexFetchAlgos() {
    return this.http.get<any>(this.newUrl + `algo`, this.httpOptions);
  }

  fetchUserDetails(
    firstName: String,
    lastName: String,
    address: string,
    dob: string,
    phonenumber: string,
    emailId: string
  ) {
    if(address == ""){address = "None"}
    if(dob == ""){dob = "None"}
    if(phonenumber == ""){phonenumber = "None"}
    if(emailId == ""){emailId = "None"}
    console.log(emailId)
    return this.http.get<any>(
      this.searchUserUrl +
        `${firstName}/${lastName}/${emailId}/${phonenumber}/${dob}`,
      this.httpOptions
    );
  }


}
