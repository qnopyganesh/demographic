import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";

import { takeUntil, first } from "rxjs/operators";
import { Subject } from "rxjs";

import { CoreConfigService } from "@core/services/config.service";
import { Router } from "@angular/router";
import { AuthenticationService } from "app/auth/service";

@Component({
  selector: "app-auth-register-v2",
  templateUrl: "./auth-register-v2.component.html",
  styleUrls: ["./auth-register-v2.component.scss"],
  encapsulation: ViewEncapsulation.None,
})
export class AuthRegisterV2Component implements OnInit {
  // Public
  public coreConfig: any;
  public passwordTextType: boolean;
  public registerForm: FormGroup;
  public submitted = false;
  public loading = false;
  public returnUrl: string;
  public error = "";

  // Private
  private _unsubscribeAll: Subject<any>;

  /**
   * Constructor
   *
   * @param {CoreConfigService} _coreConfigService
   * @param {FormBuilder} _formBuilder
   */
  constructor(
    private _coreConfigService: CoreConfigService,
    private _formBuilder: FormBuilder,
    private _router: Router,
    private _authenticationService: AuthenticationService
  ) {
    this._unsubscribeAll = new Subject();

    // Configure the layout
    this._coreConfigService.config = {
      layout: {
        navbar: {
          hidden: true,
        },
        menu: {
          hidden: true,
        },
        footer: {
          hidden: true,
        },
        customizer: false,
        enableLocalStorage: false,
      },
    };
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.registerForm.controls;
  }

  /**
   * Toggle password
   */
  togglePasswordTextType() {
    this.passwordTextType = !this.passwordTextType;
  }

  myRecaptcha:boolean;
 
  onScriptLoad() {
      console.log('Google reCAPTCHA loaded and is ready for use!')
  }

  onScriptError() {
      console.log('Something went long when loading the Google reCAPTCHA')
  }

  /**
   * On Submit
   */



  onSubmit() {
    this.submitted = true;
    this.loading = true;
    

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this._authenticationService.signup(this.f.emailId.value,this.f.password.value,this.f.contactnumber.value,this.f.address.value,this.f.firstname.value,this.f.lastname.value,this.f.dateofbirth.value).
    pipe(first())
    .subscribe(
      (data) =>{
        this._router.navigate(["/"]);
      },
      (error) => {
        this.error = error;
        this.loading = false;
      }
    );

  }

  // Lifecycle Hooks
  // -----------------------------------------------------------------------------------------------------

  /**
   * On init
   */
  ngOnInit(): void {
    this.registerForm = this._formBuilder.group({
      lastname: ['', [Validators.required]],
      firstname:['',[Validators.required]],
      password: ['', Validators.required],
      dateofbirth:['',[Validators.required]],
      address:['',[Validators.required]],
      emailId:['',[Validators.required]],
      contactnumber:['',[Validators.required]]
    });

    // Subscribe to config changes
    this._coreConfigService.config
      .pipe(takeUntil(this._unsubscribeAll))
      .subscribe((config) => {
        this.coreConfig = config;
      });
  }

  /**
   * On destroy
   */
  ngOnDestroy(): void {
    // Unsubscribe from all subscriptions
    this._unsubscribeAll.next();
    this._unsubscribeAll.complete();
  }
}
