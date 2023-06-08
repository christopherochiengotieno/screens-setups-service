import {Component, OnDestroy, OnInit} from '@angular/core';
import {ScreenSetupsService} from "../services/screen-setups.service";
import {Field, Form, SelectOption} from "../interfaces/commons";
import {Subject} from "rxjs";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ConfirmationService, ConfirmEventType, MessageService} from "primeng/api";


@Component({
  selector: 'app-screens',
  templateUrl: './screens.component.html',
  styleUrls: ['./screens.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class ScreensComponent implements OnInit {

  screens: Form[] = [];
  selectedScreen !: Form;
  classesForm!: FormGroup;
  showViewOrAddSection: "ADD" | "VIEW" = "VIEW";
  selectedButton: string = 'selectedButton';
  selectedField!: Field | null | undefined;
  fieldForm !: FormGroup;
  showFieldAdvancedSetup: boolean = false;
  addSelectOptions: boolean = false;
  listSelectOptions: boolean = true;
  chosenSelectOption!: SelectOption;
  newSelectOptions: SelectOption[] = [];
  selectOptionForm!: FormGroup;

  constructor(private screenSetupsService: ScreenSetupsService,
              private formBuilder: FormBuilder,
              private confirmationService: ConfirmationService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.createScreensForm()
    this.createFieldsForm()
    this.createSelectOptionForm()
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
    const selectedScreens: Form[] = this.screens.filter(screen => screen.id == event.target.value);
    if (selectedScreens.length > 0) {
      this.selectedScreen = selectedScreens[0];
      this.classesForm.patchValue({
        id: this.selectedScreen.id + "",
        screenName: this.selectedScreen.name,
        shortDescription: this.selectedScreen.shortDescription,
        description: this.selectedScreen.description,
        module: this.selectedScreen.module == 1 ? "GIS" : this.selectedScreen.module == 2 ? "BANCA" : "S"
      })

      // reset the selected field
      // this.selectedField = undefined;
    }
  }

  saveOrUpdateScreen() {
    this.screenSetupsService.saveScreen(this.classesForm.value).subscribe(res => {
      this.screens.push(res);
      this.clearScreenEntry();
      this.showViewOrAddSection = "VIEW";
    });
  }

  private clearScreenEntry() {
    this.classesForm.patchValue({
      id: [''],
      screenName: [''],
      shortDescription: [''],
      description: [''],
      module: ['']
    })
  }

  toggleBetweenAddOrViewClassesSection(option: "ADD" | "VIEW") {
    this.showViewOrAddSection = option;
  }

  onFieldSelected(event: any) {
    console.log(this.selectedField)
    this.fieldForm.patchValue({
      id: this.selectedField?.id,
      defaultValue: this.selectedField?.defaultValue,
      name: this.selectedField?.name,
      min: this.selectedField?.min,
      isMandatory: this.selectedField?.isMandatory,
      label: this.selectedField?.label,
      max: this.selectedField?.max,
      placeholder: this.selectedField?.placeholder,
      toolTip: this.selectedField?.toolTip,
      type: this.selectedField?.type,
      formId: this.selectedField?.formId,
      isHidden: this.selectedField?.isHidden,
      isEnabled: this.selectedField?.isEnabled,
      isReadOnly: this.selectedField?.isReadOnly
    })
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
      formId: [''],
      isEnabled: ['Y'],
      isHidden: ['N'],
      isReadOnly: ['N']
    })
  }

  clearFieldsEntry(): void {
    this.fieldForm.patchValue({
      id: '',
      defaultValue: '',
      name: '',
      min: '',
      isMandatory: 'N',
      label: '',
      max: '',
      placeholder: '',
      toolTip: '',
      type: 'text',
      formId: '',
      isEnabled: 'Y',
      isHidden: 'N',
      isReadOnly: 'N'
    })
    this.newSelectOptions = []
  }

  saveOrUpdateField() {

    if (this.selectedScreen && this.selectedScreen?.id !== undefined) {
      let field: Field = this.fieldForm.value;
      if (field.type === "select"){
        field.selectOptions = this.newSelectOptions
      }
      let fields: Field[] = [field];

      if (this.selectedField?.id) {
        // update
        console.log("Updating field")
        this.screenSetupsService.updateField(fields).subscribe(res => {
          this.initializeScreenSetupsData();
          this.clearFieldsEntry()
        });
      } else {
        console.log("Saving new field")
        this.screenSetupsService.addFieldToScreen(this.selectedScreen.id, fields).subscribe(res => {
          this.initializeScreenSetupsData();
          this.clearFieldsEntry()
          let newScreenDetails: Form[] = this.screens.filter(screen => screen.id == this.selectedScreen.id);
          if (newScreenDetails.length > 0) {
            this.selectedScreen = newScreenDetails[0]
          }
        });
      }

    }
  }

  deleteFieldById(field?: Field) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete ' + this.selectedField?.label + ' from ' + this.selectedScreen.name + '?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.screenSetupsService.deleteFieldById(field?.id).subscribe(res => {
          this.initializeScreenSetupsData();
          this.messageService.add({severity: 'info', summary: 'Confirmed', detail: 'Field successfully deleted'});
        });

      },
      reject: (type: any) => {
        switch (type) {
          case ConfirmEventType.REJECT:
            this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'You have rejected'});
            break;
          case ConfirmEventType.CANCEL:
            this.messageService.add({severity: 'warn', summary: 'Cancelled', detail: 'You have cancelled'});
            break;
        }
      }
    })
  }

  toggleShowFieldAdvancedSetup() {
    this.showFieldAdvancedSetup = !this.showFieldAdvancedSetup;
  }

  toggleAddSelectOptions() {
    if (this.selectedField) {
      this.selectedField.selectOptions.forEach(option => {
        this.newSelectOptions.push(option);
      })
    }
    this.addSelectOptions = !this.addSelectOptions;
  }

  toggleListSelectOptions() {
    if (this.selectedField) {
      this.selectedField.selectOptions.forEach(option => {
        this.newSelectOptions.push(option);
      })
    }
    this.listSelectOptions = !this.listSelectOptions;
  }

  onSelectOptionChosen(event: any) {
    console.log(event.target.value)
  }

  addNewSelectOption() {
    console.log(this.selectOptionForm.value)
    this.newSelectOptions.push({
      isSelected: this.selectOptionForm.value.selected, isEnabled: this.selectOptionForm.value.enabled,
      isHidden: this.selectOptionForm.value.hidden, text: this.selectOptionForm.value.text, value: this.selectOptionForm.value.value
    })

    this.listSelectOptions = true;
    console.log(this.newSelectOptions)
  }

  private createSelectOptionForm() {
    this.selectOptionForm = this.formBuilder.group({
      id: [''],
      text: [''],
      value: [''],
      hidden: ['N'],
      selected: ['N'],
      enabled: ['Y']
    })
  }
}
