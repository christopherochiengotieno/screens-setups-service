import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ScreensComponent} from "./screens/screens.component";
import {PreviewComponent} from "./components/preview/preview.component";

const routes: Routes = [
  {path: '', component: ScreensComponent},
  {path: 'preview', component: PreviewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
