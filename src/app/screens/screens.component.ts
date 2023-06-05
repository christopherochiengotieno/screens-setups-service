import {Component, OnDestroy, OnInit} from '@angular/core';
import {ScreenSetupsService} from "../services/screen-setups.service";
import {Screen} from "../interfaces/commons";
import {Subject} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";


@Component({
  selector: 'app-screens',
  templateUrl: './screens.component.html',
  styleUrls: ['./screens.component.scss']
})
export class ScreensComponent implements OnInit, OnDestroy {

  // rateForm:FormGroup;
  screens: Screen[] = [];
  destroyed = new Subject();
  selectedScreen !: Screen;
  classesForm = this.formBuilder.group({
    id: [''],
    name: [''],
    shortDescription: [''],
    description: [''],
    module: ['']
  });

  constructor(private screenSetupsService: ScreenSetupsService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {


    this.initializeScreenSetupsData();
  }

  initializeScreenSetupsData(): Screen[] {
    this.screenSetupsService.getScreensSetups().subscribe(data => this.screens = data);
    return this.screens;
  }

  selectScreen(event: any): void {
    console.log(event.target.value)
    const selectedScreens = this.screens.filter(screen => screen.id == event.target.value);
    if (selectedScreens.length > 0) {
      this.selectedScreen = selectedScreens[0];
      this.classesForm.patchValue( {
        id: this.selectedScreen.id + "",
        name: this.selectedScreen.screenName,
        shortDescription: this.selectedScreen.shortDescription,
        description: this.selectedScreen.description,
        module: this.selectedScreen.module + ""
      })
    }

  }

  ngOnDestroy(): void {
    this.destroyed.next(undefined);
    this.destroyed.complete();
  }

  saveOrUpdateClass() {
    console.log(this.classesForm.value)
  }
}
