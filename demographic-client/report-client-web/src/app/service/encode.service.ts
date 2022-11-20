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
    address: string = "None",
    dob: string = "None",
    phonenumber: string = "None",
    emailId: string = "None"
  ) {
    return this.http.get<any>(
      this.searchUserUrl +
        `${firstName}/${lastName}/${emailId}/${phonenumber}/${dob}`,
      this.httpOptions
    );
  }

  signup(username, password, contact, address, firstname, lastname, dob) {
    return this.http
      .post<any>(
        "http://localhost:9001/admin/signUp" +
          `?username=${username}&password=${password}&contact=${contact}&address=${address}&firstname=${firstname}&lastname=${lastname}&dob=${dob}`,
        { headers: new HttpHeaders({ "Content-Type": "application/json" }) }
      )
      .pipe(
        map((user) => {
          let newuser = new User();
          // login successful if there's a jwt token in the response
          if (user) {
            console.log(user.username);
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            let newuserobj = new User();
            newuserobj.username = user.username;
            newuserobj.role = Role.Admin;
            newuserobj.token = "adadadadad";
            newuserobj.password = user.password;
            newuser = newuserobj;
            localStorage.setItem("currentUser", JSON.stringify(newuserobj));

            // Display welcome toast!
            // notify
            this.currentUserSubject.next(newuserobj);
          }

          return newuser;
        })
      );
  }
}
