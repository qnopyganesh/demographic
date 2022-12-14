import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { RouterModule, Routes } from "@angular/router";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule } from "@angular/common/http";
import { FileUploadModule } from "ng2-file-upload";
import "hammerjs";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { TranslateModule } from "@ngx-translate/core";
import { ToastrModule, ToastrService } from "ngx-toastr"; // For auth after login toast
import { RecaptchaModule } from 'angular-google-recaptcha';
import { CoreModule } from "@core/core.module";
import { CoreCommonModule } from "@core/common.module";
import { CoreSidebarModule, CoreThemeCustomizerModule } from "@core/components";

import { coreConfig } from "app/app-config";

import { AppComponent } from "app/app.component";
import { LayoutModule } from "app/layout/layout.module";
import { SampleModule } from "app/main/sample/sample.module";
import { EncodeComponent } from "./encode/encode.component";
import { NgSelectModule } from "@ng-select/ng-select";
import { FormsModule } from "@angular/forms";
import { SearchScreenComponent } from "./search-screen/search-screen.component";
import { UploadScreenComponent } from "./upload-screen/upload-screen.component";
import { UploadComponent } from "./upload/upload.component";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { SearchUserComponent } from "./search-user/search-user.component";
import { GoogleMapsModule } from "@angular/google-maps";
import { VisualDataComponent } from "./visual-data/visual-data.component";
import { LineChartViewComponent } from "./line-chart-view/line-chart-view.component";
import { NgApexchartsModule } from "ng-apexcharts";
import { AuthenticationModule } from "./authentication/authentication.module";
import { AuthGuard } from "./auth/helpers/auth.guards";

const appRoutes: Routes = [
  {
    path: "pages",
    loadChildren: () =>
      import("./main/pages/pages.module").then((m) => m.PagesModule),
  },
  {
    path: "",
    redirectTo: "/encode",
    pathMatch: "full",
  },
  {
    path: "encode",
    component: EncodeComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "search",
    component: SearchScreenComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "uploadscreen",
    component: UploadScreenComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "upload",
    component: UploadComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "searchUser",
    component: SearchUserComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "visualdata",
    component: VisualDataComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "**",
    redirectTo: "/pages/miscellaneous/error", //Error 404 - Page not found
  },
];

@NgModule({
  declarations: [
    AppComponent,
    EncodeComponent,
    SearchScreenComponent,
    UploadScreenComponent,
    UploadComponent,
    SearchUserComponent,
    VisualDataComponent,
    LineChartViewComponent,
  ],
  imports: [
    FileUploadModule,
    AuthenticationModule,
    BrowserModule,
    NgApexchartsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RecaptchaModule.forRoot({
      siteKey: '6LeKnB8jAAAAAMFanHdPjoosm0a0tCuDy2bqACY3',
  }),
    RouterModule.forRoot(appRoutes, {
      scrollPositionRestoration: "enabled", // Add options right here
      relativeLinkResolution: "legacy",
    }),
    TranslateModule.forRoot(),

    //NgBootstrap
    NgbModule,
    ToastrModule.forRoot(),

    // Core modules
    CoreModule.forRoot(coreConfig),
    CoreCommonModule,
    CoreSidebarModule,
    CoreThemeCustomizerModule,

    // App modules
    LayoutModule,
    SampleModule,
    NgSelectModule,
    FormsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    GoogleMapsModule,
  ],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA],
})
export class AppModule {}
