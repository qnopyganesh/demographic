import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { FileItem, FileUploader } from 'ng2-file-upload';
@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent implements OnInit {
  public contentHeader: object;
  public hasAnotherDropZoneOver: boolean = false;
  public hasBaseDropZoneOver: boolean = false;
  public uploader: FileUploader = new FileUploader({
    url: "http://localhost:9001/NameIndex/upload",
    isHTML5: true,
  });


  filrUploadUrl = "http://localhost:9001/NameIndex/upload"
  constructor(private _http: HttpClient, private toastr: ToastrService) { }


  file: any;


  selectfile(event) {
    this.uploader.queue.pop();
    this.file = event.target.files[0];
    this.uploader.queue.push(this.file);
  }

  toastrSuccess() {
    this.toastr.success('Your File Uploaded Successfully', 'File Uploaded!', {
      toastClass: 'toast ngx-toastr',
      closeButton: true
    });
  }

  toastrUploadNotify() {
    this.toastr.warning('Your File is under processing', 'File Processing!', {
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
  uploadAllItems(){
    for(let item of this.uploader.queue){
      this.uploadfile(item);
    }
  }

  updateUpdaterQueue(item){
    let ls = [];
    ls = this.uploader.queue;
    for (let i = 0; i < ls.length; i++) {
      if(ls[i] == item){
        ls.splice(i,1);
      }
    }
    this.uploader.queue = ls;
  }

  clearQueue(){
    this.uploader.queue = [];
  }

  uploadfile(item) {
    console.log(item);
    console.log("upload")
    let formData = new FormData();
    formData.append("file", item);
    this._http.post(this.filrUploadUrl, formData).subscribe(
      (data: any) => {
        this.toastrSuccess();
        this.updateUpdaterQueue(item);
      },
      (error) => {
        this.toastrError();
        this.updateUpdaterQueue(item);
      }

    )

  }
  // Public Methods
  // -----------------------------------------------------------------------------------------------------
  fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }

  fileOverAnother(e: any): void {
    this.hasAnotherDropZoneOver = e;
  }



  // Lifecycle Hooks
  // -----------------------------------------------------------------------------------------------------

  /**
   * On init
   */
  ngOnInit(): void {
    // content header
    this.contentHeader = {
      headerTitle: 'File Uploader',
      actionButton: true,
      breadcrumb: {
        type: '',
        links: [
          {
            name: 'Home',
            isLink: true,
            link: '/'
          },
          {
            name: 'Extensions',
            isLink: true,
            link: '/'
          },
          {
            name: 'File Uploader',
            isLink: false
          }
        ]
      }
    };
  }

}
