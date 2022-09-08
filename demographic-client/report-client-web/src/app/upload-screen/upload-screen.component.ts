import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-upload-screen',
  templateUrl: './upload-screen.component.html',
  styleUrls: ['./upload-screen.component.scss']
})
export class UploadScreenComponent {
//use toastr for success message



  filrUploadUrl="http://localhost:9001/NameIndex/upload"
  constructor(private _http:HttpClient , private toastr: ToastrService) { }
    

  file: any;


  selectfile(event) {
    console.log("event clicked")
    this.file = event.target.files[0];
  }
  toastrSuccess() {
    this.toastr.success('Your File Uploaded Successfully', 'File Uploaded!', {
      toastClass: 'toast ngx-toastr',
      closeButton: true
    });
  }

  

  toastrError() {
    this.toastr.error('Some Internal Error!  Please Try Again Later', 'Error!', {
      toastClass: 'toast ngx-toastr',
      closeButton: true
    });
  }


  uploadfile(){
    console.log(this.file);
    console.log("upload")
    let formData=new FormData();
    formData.append("file",this.file);
    this._http.post(this.filrUploadUrl,formData).subscribe(
      (data:any)=>{
          this.toastrSuccess();
      },
      (error)=>{
          this.toastrError();
      }

    )

  }

}
