import { Component, OnInit } from "@angular/core";
import { EncodeService } from "app/service/encode.service";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: "app-encode",
  templateUrl: "./encode.component.html",
  styleUrls: ["./encode.component.scss"],
})
export class EncodeComponent implements OnInit {
  ngOnInit(): void {}

  constructor(
    private toastr: ToastrService,
    private encodeService: EncodeService
  ) {}

  selectedAlgorithm: string = "Caverphone1";
  encodedText: string = "";
  name: String = "";
  algorithms = [
    { value: "Caverphone1" },
    { value: "Caverphone2" },
    { value: "Soundex" },
    { value: "DaitchMokotoffSoundex" },
    { value: "ColognePhonetic" },
    { value: "DoubleMetaphone" },
    { value: "MatchRatingApproachEncoder" },
    { value: "Phonetic Based Algorithm"},
    { value: "Nysiis" },
    { value: "RefinedSoundex" },
  ];

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
    this.encodeService
      .encode(this.selectedAlgorithm, this.name)
      .subscribe((data) => {
        console.log(data);
        this.encodedText = data;
      });
  }
}
