import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { EncodeService } from "app/service/encode.service";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: "app-encode",
  templateUrl: "./encode.component.html",
  styleUrls: ["./encode.component.scss"],
})
export class EncodeComponent implements OnInit {
  public encodeForm: FormGroup;
  public submitted = false;
  encodedText: string = "";
  algorithms = [
    { value: "Caverphone1" },
    { value: "Caverphone2" },
    { value: "Soundex" },
    { value: "DaitchMokotoffSoundex" },
    { value: "ColognePhonetic" },
    { value: "DoubleMetaphone" },
    { value: "MatchRatingApproachEncoder" },
    { value: "Phonetic Based Algorithm" },
    { value: "Nysiis" },
    { value: "RefinedSoundex" },
  ];
  ngOnInit(): void {
    this.encodeForm = this._formBuilder.group({
      name: ["", [Validators.required]],
      selectedAlgorithm: ["Caverphone1", [Validators.required]],
    });
  }

  constructor(
    private toastr: ToastrService,
    private encodeService: EncodeService,
    private _formBuilder: FormBuilder
  ) {}

  get f() {
    return this.encodeForm.controls;
  }
  copyCode(inputTextValue) {
    const selectBox = document.createElement("textarea");
    selectBox.style.position = "fixed";
    selectBox.value = inputTextValue;
    document.body.appendChild(selectBox);
    selectBox.focus();
    selectBox.select();
    document.execCommand("copy");
    document.body.removeChild(selectBox);
    this.showSuccess();
  }

  showSuccess() {
    this.toastr.success(null, "Copied to Clipboard");
  }

  encode() {
    this.submitted = true;
    if (this.encodeForm.invalid) {
      return;
    }
    this.encodeService
      .encode(this.f.selectedAlgorithm.value, this.f.name.value)
      .subscribe((data) => {
        this.encodedText = data;
      });
  }
}
