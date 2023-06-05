import {Component, OnDestroy, OnInit} from '@angular/core';
import {ScreenSetupsService} from "../services/screen-setups.service";
import {Field, Screen} from "../interfaces/commons";
import {Subject} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";


@Component({
  selector: 'app-screens',
  templateUrl: './screens.component.html',
  styleUrls: ['./screens.component.scss']
})
export class ScreensComponent implements OnInit {

  screens: Screen[] = [];
  selectedScreen !: Screen;
  classesForm!: FormGroup;
  showViewOrAddSection : "ADD" | "VIEW" = "VIEW";
  selectedButton: string = 'selectedButton';
  selectedField!: Field;

  constructor(private screenSetupsService: ScreenSetupsService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.createForm()
    this.initializeScreenSetupsData();
  }

  initializeScreenSetupsData() {
    this.screenSetupsService.getScreensSetups().subscribe(res => {
      this.screens = res
    })
  }

  createForm(): void {
    this.classesForm = this.formBuilder.group({
      id: [''],
      name: [''],
      shortDescription: [''],
      description: [''],
      module: ['']
    });
  }

  selectScreen(event: any): void {
    const selectedScreens: Screen[] = this.screens.filter(screen => screen.id == event.target.value);
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

  saveOrUpdateClass() {
    console.log(this.classesForm.value)
  }

  toggleBetweenAddOrViewClassesSection(option: "ADD" | "VIEW") {
    this.showViewOrAddSection = option;
  }

  onFieldSelected(event: any) {
    console.log(this.selectedField)
  }

  onScreenSelected(event: any) {
    console.log()
  }
}
