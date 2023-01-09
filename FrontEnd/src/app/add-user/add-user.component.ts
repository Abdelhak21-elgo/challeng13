import { Component, OnInit } from '@angular/core';
import { AdduserServiceService } from '../_Service/adduser-service.service';
import { User } from '../_Model/User.model';
import { FileHandler } from "../_Model/FileHandle.model";
import { NgForm } from '@angular/forms';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  user: User = {
    userId: 0,
    nome: "",
    prenome: "",
    nomeArabe: "",
    prenomeArab: "",
    cin: "",
    profession: "",
    dateNaissance: "",
    typeCarte: "",
    userImage: {} as FileHandler

  }

  constructor(private userService: AdduserServiceService,
    private sanitizer: DomSanitizer, private activatedRoute: ActivatedRoute,
    private http : HttpClient) { }

  ngOnInit(): void {

  }

  onSubmit(userformm: NgForm) {
    const userff = this.preparuser(this.user);
    this.userService.generatepdf(userff) .subscribe(
      (response) => {
        // this.user.userImage = {} as any;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
    alert("pdf Generated Succefully");
  }

  addUser(userform: NgForm) {
    const userf = this.preparuser(this.user);
    this.userService.adduser(userf).subscribe(
      (response: User) => {
        // userform.reset();
        // this.user.userImage = {} as any;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  preparuser(user: User): FormData {
    const formData = new FormData();

    formData.append(
      'user',
      new Blob([JSON.stringify(user)], { type: 'application/json' })
    );

    formData.append(
      'imagefile',
      user.userImage.file,
      user.userImage.file.name
    );
    return formData;
  }

  onFileSelected(event: any) {
    if (event.target.files) {
      const fileUp = event?.target.files[0];
      const fileHandel: FileHandler = {
        file: fileUp,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(fileUp))
      }
      this.user.userImage = fileHandel;
    }
  }

  // removeimage() {
  //   this.user.userImage;
  // }
}
