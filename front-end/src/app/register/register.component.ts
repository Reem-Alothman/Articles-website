import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  username: String = '';
  password: String = '';
  mobileNumber: number = 1;
  email: String = '';


  constructor(private userService: UserService){}

  ngOnInit(): void {
      
  }

  submitRegister(): void{
    
    const creds = {
      username: this.username,
      email: this.email,
      password: this.password,
      mobileNumber: this.mobileNumber
    }


    this.userService.register(creds).subscribe(res => {
      console.log(`user ${creds.username} added succefully`)
    });

  }
}
