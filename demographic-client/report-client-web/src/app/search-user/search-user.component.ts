import { Component, OnInit } from "@angular/core";
import { EncodeService } from "app/service/encode.service";

@Component({
  selector: "app-search-user",
  templateUrl: "./search-user.component.html",
  styleUrls: ["./search-user.component.scss"],
})
export class SearchUserComponent implements OnInit {
  constructor(private encodeService: EncodeService) {}

  public userLocationZoom = 15;
  public userLocationCenter: google.maps.LatLngLiteral;
  submitted = false;
  firstName: String = "";
  lastName: String = "";
  emailId: string = "";
  dob: string = "";
  phonenumber: string = "";
  address: string = "";
  showMore: boolean = true;
  showMoreUserDetails;

  users = [];

  user: any;
  ngOnInit(): void {
    navigator.geolocation.getCurrentPosition((position) => {
      this.userLocationCenter = {
        lat: 19.2045299,
        lng: 72.8519955,
      };
    });
  }

  getUserDetails() {
    this.submitted = true;
    console.log(this.firstName);
    if (this.firstName == "" || this.lastName == "") {
      return;
    }
    this.encodeService
      .fetchUserDetails(
        this.firstName,
        this.lastName,
        this.address,
        this.dob,
        this.phonenumber,
        this.emailId
      )
      .subscribe((data:[]) => {
        if(data.length == 0){
          return ;
        } 
        this.users = data;
        this.showMoreUserDetails = [];
        for(let i = 0 ; i < this.users.length; i++){
          this.showMoreUserDetails.push(false)
        }
        // this.user = data;
        // this.userLocationCenter = {
        //   lat: this.user.latitude,
        //   lng: this.user.longitude,
        // };
        // let nameEncoded: [string] = JSON.parse(this.user.firstnameEncoded);
        // let snameEncoded: [string] = JSON.parse(this.user.lastnameEncoded);
        // console.log(data);
        // console.log(nameEncoded);
        // let size = nameEncoded.length;
        // for (let i = 0; i < size; i++) {
        //   let temp = nameEncoded[i].split(":");
        //   let temp1 = snameEncoded[i].split(":");
        //   this.rows.push([temp[0], temp[1], temp1[1]]);
        // }
        console.log(data);
      });
  }

  showOptionalFields() {
    this.showMore = !this.showMore;
  }

  showMoreDetails(index){
    console.log(index);
    this.showMoreUserDetails[index] = !this.showMoreUserDetails[index];
    if(this.showMoreUserDetails[index] == true){
      for(let i = 0 ; i < this.users.length; i++){
        if(i != index){
          this.showMoreUserDetails[i] = false;
        }
      }
      this.userLocationCenter = {
        lat : this.users[index].latitude,
        lng : this.users[index].longitude
      }
    }
  }

  hideOptionalFields() {
    this.showMore = !this.showMore;
  }

  displaydate() {
    console.log(this.dob);
  }
}
