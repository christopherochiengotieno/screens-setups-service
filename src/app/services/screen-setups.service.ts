import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Screen} from "../interfaces/commons";

@Injectable({
  providedIn: 'root'
})
export class ScreenSetupsService {

  screen_api = "http://localhost:9000/api/v1/screens";
  private options: any;

  constructor(private http: HttpClient) {
  }

  getScreensSetups(): Observable<Screen[]> {
    return this.http.get<any>(this.screen_api);
  }

  saveScreen(newScreen: Screen):Observable<Screen> {
    /*this.options = {
      resp
    }*/
    let options = {headers: {
        responseType: 'application/json'
      }};
    return this.http.post<Screen>(this.screen_api,newScreen,options);
  }
}
