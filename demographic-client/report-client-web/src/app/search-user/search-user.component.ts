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

  firstName: String = "";
  lastName: String = "";
  emailId:string = "";
  dob:string = "";
  phonenumber:string = "";
  address:string = "";
  showMore:boolean=true;

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
    this.encodeService
      .fetchUserDetails(this.firstName, this.lastName,this.address,this.dob,this.phonenumber,this.emailId)
      .subscribe((data) => {
        this.user = data;
        this.userLocationCenter = {
          lat: this.user.latitude,
          lng: this.user.longitude,
        };
        console.log(data);
      });
  }

  showOptionalFields(){
    this.showMore=!this.showMore;
  }
  hideOptionalFields(){
    this.showMore=!this.showMore;
  }

  displaydate(){
    console.log(this.dob);
  }
}

