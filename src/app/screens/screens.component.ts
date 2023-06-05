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
  selectedField!: Field | null | undefined;
  fieldForm !: FormGroup;

  constructor(private screenSetupsService: ScreenSetupsService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.createScreensForm()
    this.createFieldsForm()
    this.initializeScreenSetupsData();
  }

  initializeScreenSetupsData() {
    this.screenSetupsService.getScreensSetups().subscribe(res => {
      this.screens = res
    })
  }

  createScreensForm(): void {
    this.classesForm = this.formBuilder.group({
      id: [''],
      screenName: [''],
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
        screenName: this.selectedScreen.screenName,
        shortDescription: this.selectedScreen.shortDescription,
        description: this.selectedScreen.description,
        module: this.selectedScreen.module == 1 ? "GIS" : this.selectedScreen.module == 2 ? "BANCA" : "S"
      })

      // reset the selected field
      this.selectedField = undefined;
    }
  }

  saveOrUpdateClass() {
    this.screenSetupsService.saveScreen(this.classesForm.value).subscribe(res => this.screens.push(res));
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

  clearBtnClicked() {
    this.classesForm.patchValue({
      id: [''],
      screenName: [''],
      shortDescription: [''],
      description: [''],
      module: ['']
    })
  }

  private createFieldsForm() {
    this.fieldForm = this.formBuilder.group({
      id: [''],
      defaultValue: [''],
      name: [''],
      min: [''],
      isMandatory: ['N'],
      label: [''],
      max: [''],
      placeholder: [''],
      toolTip: [''],
      type: ['text'],
      screenId: ['']
    })
  }

  clearFieldsEntry() : void {
    this.fieldForm.patchValue({
      id: [''],
      defaultValue: [''],
      name: [''],
      min: [''],
      isMandatory: ['N'],
      label: [''],
      max: [''],
      placeholder: [''],
      toolTip: [''],
      type: ['text'],
      screenId: ['']
    })
  }
  saveOrUpdateField() {

    if (this.selectedScreen && this.selectedScreen?.id !== undefined )
    {
      let fields: Field[] = [this.fieldForm.value];
      this.screenSetupsService.addFieldToScreen(this.selectedScreen.id, fields).subscribe(res => {
            this.initializeScreenSetupsData();
            this.clearFieldsEntry()
          });
    }
  }
}
