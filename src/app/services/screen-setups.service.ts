import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Field, Screen} from "../interfaces/commons";

@Injectable({
  providedIn: 'root'
})
export class ScreenSetupsService {

  screen_api = "http://localhost:9000/api/v1/screens";
  field_api: string = "http://localhost:9000/api/v1/fields";

  constructor(private http: HttpClient) {
  }

  getScreensSetups(): Observable<Screen[]> {
    return this.http.get<any>(this.screen_api);
  }

  saveScreen(newScreen: Screen):Observable<Screen> {
    return this.http.post<Screen>(this.screen_api,newScreen,{headers: {responseType: 'application/json'}});
  }

  addFieldToScreen(screenId: number, fields: Field[]): Observable<any> {
    return this.http.post(this.field_api, fields, {headers: {responseType: 'application/json', 'Access-Control-Allow-Origin': '*'}, params: {screenId: screenId}} )
  }
}
