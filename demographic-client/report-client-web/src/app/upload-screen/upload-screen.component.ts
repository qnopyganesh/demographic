import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upload-screen',
  templateUrl: './upload-screen.component.html',
  styleUrls: ['./upload-screen.component.scss']
})
export class UploadScreenComponent implements OnInit {


  filrUploadUrl="http://localhost:9001/NameIndex/upload"
  constructor(private _http:HttpClient) {
    
   }

  file: any;
  flag:boolean=true;

  ngOnInit(): void {}

  selectfile(event) {
    console.log("event clicked")
    this.file = event.target.files[0];
  }

  uploadfile(){
    console.log("upload")
    let formData=new FormData();
    formData.append("file",this.file);
    this.flag=false;
    this._http.post(this.filrUploadUrl,formData).subscribe(
      (data:any)=>{
          alert(data.message)
          
          this.flag=true;
      },
      (error)=>{
          this.flag=true;
          alert("some error occured")
      }

    )

  }

}
