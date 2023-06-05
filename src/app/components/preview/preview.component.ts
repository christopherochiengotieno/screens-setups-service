import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ScreenSetupsService} from "../../services/screen-setups.service";
import {Field, Screen} from "../../interfaces/commons";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'preview',
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.scss']
})
export class PreviewComponent implements OnInit{

  title: string = "preview works"
  screen!: Screen;
  mandatoryFrontendScreens: Field[] = [];
  optionalFrontendScreens: Field[] = [];
  allFrontendScreens: Screen[] = [];
  showOptionalFields: boolean = false;

  classesForm: FormGroup = new FormGroup<any>({});

  constructor(private route: ActivatedRoute, private screenSetupsService: ScreenSetupsService) {}
  ngOnInit(): void {
    this.route.queryParams.subscribe(param => {
      if(param.hasOwnProperty('screenId')){
        console.log(param['screenId'])
        this.getScreenDetails(param['screenId'])
      } else {

      }
    })
  }

  private getScreenDetails(screenId: number) {
    this.screenSetupsService.getScreenById(screenId).subscribe(res => {

      this.screen = res
      let fields = res.fields;

      let controls = {};
      fields.forEach(field => {
        controls= {...controls, [field.name]: new FormControl('')}
      })

      this.classesForm = new FormGroup<any>(controls) // instantiate the reactive form controls

      this.mandatoryFrontendScreens = fields.filter(field => field.isMandatory === 'Y');
      this.optionalFrontendScreens = fields.filter(field => field.isMandatory === 'N');
    });
  }



  /**
   *
   */
  setShowMoreFields() {
    this.showOptionalFields = !this.showOptionalFields;
  }

  /**
   *
   */
  onClassesFormSubmitted() {
    if (!this.classesForm.valid)
      console.error("Invalid form")
    console.log(this.classesForm.value);
  }

}
