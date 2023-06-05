import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Screen} from "../interfaces/commons";

@Injectable({
  providedIn: 'root'
})
export class ScreenSetupsService {

  constructor(private http: HttpClient) {
  }

  getScreensSetups(): Observable<any> {
    return this.http.get<any>("http://localhost:9000/api/v1/screens");
  }
}
