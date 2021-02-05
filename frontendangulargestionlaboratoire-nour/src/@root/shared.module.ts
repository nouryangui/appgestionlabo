import {NgModule} from '@angular/core';
import {MaterialModule} from './material/material.module';
import {HttpClientModule} from '@angular/common/http';
import {ConfirmDialogModule} from './confirm-dialog/confirm-dialog.module';



@NgModule({
  imports: [
    MaterialModule,
    HttpClientModule,
    ConfirmDialogModule,

  ],
  exports: [
    MaterialModule,
    HttpClientModule,
    ConfirmDialogModule,

  ]
})
export class SharedModule {
}
