import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Field, Form} from "../interfaces/commons";

// let base_url = "http://10.176.18.211:4000";
let base_url = "http://localhost:4000";

@Injectable({
  providedIn: 'root'
})
export class ScreenSetupsService {

  screen_api = base_url + "/api/v1/forms";
  field_api: string = base_url + "/api/v1/fields";
  private token: string = 'eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJmeWJxWGFpd0xwWlI0R0VEUDRRaUI2ajMxeERvNkVLak11T182cUJmNjFnIn0.eyJleHAiOjE2ODYzMTY3NzEsImlhdCI6MTY4NjIzMDM3MSwianRpIjoiZjRlNjM1MzEtNzRlZi00YjI2LWI3ZjItMWM0ODY2NDUzZDE0IiwiaXNzIjoiaHR0cDovLzEwLjE3Ni4xOC4yMTE6ODE4MS9yZWFsbXMvdHVybnF1ZXN0LXZlcnNpb24tc2l4IiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjQ4MjAwYWJhLTA2MzYtNDc5MC05NDc1LTA4MzNhZjQ1MTYzOSIsInR5cCI6IkJlYXJlciIsImF6cCI6InR1cm5xdWVzdC12ZXJzaW9uLTYtYXBpcyIsInNlc3Npb25fc3RhdGUiOiI3OGFjMzhiNy04NjY2LTQ5MDUtOGFiZC0xN2VjMjVlNWE2NDgiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtdHVybnF1ZXN0LWdpcyIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiI3OGFjMzhiNy04NjY2LTQ5MDUtOGFiZC0xN2VjMjVlNWE2NDgiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6IkNocmlzdG9waGVyIE90aWVubyBDaHJpc3RvcGhlciBPdGllbm8iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJjaHJpc3RvcGhlci5vdGllbm9AdHVybmtleWFmcmljYS5jb20iLCJnaXZlbl9uYW1lIjoiQ2hyaXN0b3BoZXIgT3RpZW5vIiwiZmFtaWx5X25hbWUiOiJDaHJpc3RvcGhlciBPdGllbm8iLCJlbWFpbCI6ImNocmlzdG9waGVyLm90aWVub0B0dXJua2V5YWZyaWNhLmNvbSIsInVzZXJuYW1lIjoiY2hyaXN0b3BoZXIub3RpZW5vQHR1cm5rZXlhZnJpY2EuY29tIn0.oyOf8cH6sjhj9sHoeNX5GY92ddLOJ2mwpH5r6tTKZIP9qqgZ-VDFS7Ya2DRcLc29IGHfRLN-zR_T-lmxZiC4MHazAwCdOr2ScOoktUqFlGy8N-Ec9LkLzwuKElFD7VKJwTz0bqfe9DiC5QE8KWd-bqKxF6BIApLdJ79vPi8UFI3R9Zbp2NjGFV7F_7z9ClT-V4C_QxAWQTnMKB76upI2Yzmd1WbHzu5AwxmOlhgFvlxgt8kKqMPYdYAka5o52ujn9uu2IK5dHNcs-2OmAyXQqr0MHxJ1Vx6vy6Rr1rt-G5YPqneXXLdvH7r5dhwmGlgw9ykhe1fQyNx2Cb12A7xA1w';

  constructor(private http: HttpClient) {
  }

  getScreensSetups(): Observable<Form[]> {
    return this.http.get<any>(this.screen_api);
  }

  saveScreen(newScreen: Form): Observable<Form> {
    let options = {
      headers: {
        responseType: 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Authorization': 'Bearer ' + this.token
      },
    };
    return this.http.post<Form>(this.screen_api, newScreen, options);
  }

  addFieldToScreen(screenId: number, fields: Field[]): Observable<any> {
    let options = {
      headers: {
        responseType: 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Authorization': 'Bearer ' + this.token
      },
      params: {screenId: screenId}
    };
    return this.http.post(this.field_api, fields, options)
  }

  deleteFieldById(id?: number) {
    let options = {
      headers: {
        responseType: 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Authorization': 'Bearer ' + this.token
      }
    };
    return this.http.delete(this.field_api + "/" + id,options);
  }

  getScreenById(screenId: number): Observable<Form> {
    return this.http.get<Form>(this.screen_api + "/" + screenId);
  }

  updateField(fields: Field[]) {
    let options = {
      headers: {
        responseType: 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Authorization': 'Bearer ' + this.token
      },
    };
    return this.http.put(this.field_api, fields, options)
  }
}
