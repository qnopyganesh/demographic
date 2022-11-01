import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { map } from "rxjs/operators";

import { environment } from "environments/environment";
import { User, Role } from "app/auth/models";
import { ToastrService } from "ngx-toastr";

@Injectable({ providedIn: "root" })
export class AuthenticationService {
  //public
  public currentUser: Observable<User>;

  //private
  private currentUserSubject: BehaviorSubject<User>;

  /**
   *
   * @param {HttpClient} _http
   * @param {ToastrService} _toastrService
   */
  constructor(
    private _http: HttpClient,
    private _toastrService: ToastrService
  ) {
    this.currentUserSubject = new BehaviorSubject<User>(
      JSON.parse(localStorage.getItem("currentUser"))
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  // getter: currentUserValue
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  /**
   *  Confirms if user is admin
   */
  get isAdmin() {
    return (
      this.currentUser && this.currentUserSubject.value.role === Role.Admin
    );
  }

  /**
   *  Confirms if user is client
   */
  get isClient() {
    return (
      this.currentUser && this.currentUserSubject.value.role === Role.Client
    );
  }

  /**
   * User login
   *
   * @param email
   * @param password
   * @returns user
   */
  login(email: string, password: string) {
    return this._http
      .post<any>(
        "http://localhost:9001/admin/login" +
          `?username=${email}&password=${password}`,
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

  register(email: string, password: string) {
    return this._http
      .post<any>(
        "http://localhost:9001/admin/signUp" +
          `?username=${email}&password=${password}`,
        { headers: new HttpHeaders({ "Content-Type": "application/json" }) }
      )
      .pipe(
        map((user) => {
          if (user) {
            setTimeout(() => {
              this._toastrService.success(
                "You have successfully Registered" +
                  " User to Vuexy. Now you can start to explore. Enjoy! 🎉",
                "Please Login",
                { toastClass: "toast ngx-toastr", closeButton: true }
              );
            }, 2500);
          }
          return user;
        })
      );
  }

  /**
   * User logout
   *
   */
  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem("currentUser");
    setTimeout(() => {
      this._toastrService.success(
        "You have successfully logged out",
        "Please Login To use the Service",
        { toastClass: "toast ngx-toastr", closeButton: true }
      );
    }, 2500);
    // notify
    this.currentUserSubject.next(null);
  }
}
