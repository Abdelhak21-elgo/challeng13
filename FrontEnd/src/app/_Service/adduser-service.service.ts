import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../_Model/User.model';

@Injectable({
  providedIn: 'root'
})
export class AdduserServiceService {

  apipath = "http://localhost:9090";
  
  constructor(private httpclient : HttpClient) { }

  public adduser(user : FormData){
    return this.httpclient.post<User>(this.apipath+"/addUser", user);
  }

  public getAllUsers(){ 
    return this.httpclient.get<User[]>(this.apipath+"/getAllUsers");
  }

  public getQrCode(){
    return this.httpclient.get(this.apipath+'/QrCode', { responseType: 'blob' });
  }

  public generatepdf(user : FormData){
    return this.httpclient.post<User>(this.apipath+'/generate-pdf', user);
  }
}
