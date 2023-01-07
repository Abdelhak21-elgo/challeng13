import { Component, OnInit } from '@angular/core';
import { AdduserServiceService } from '../_Service/adduser-service.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  qrCodeUrl: string = "ajouter un utulisateur";

  constructor(private userservice : AdduserServiceService) {
    this.userservice.getQrCode().subscribe(qrCodeBlob => {
      const reader = new FileReader();
      reader.addEventListener('load', () => {
        this.qrCodeUrl = reader.result as string;
      }, false);
      reader.readAsDataURL(qrCodeBlob);
    });
  }
  ngOnInit(): void {
    
  }

}
