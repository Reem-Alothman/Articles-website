import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  username: String = 'admin';
  password: String = 'adminPassword';


  constructor(private userService: UserService){}

  ngOnInit(): void {
      
  }

  submitLogin(): void{
    
    const cred = {
      username: this.username,
      password: this.password

    }

    this.userService.login(cred).subscribe(res => {
      //console.log(res.headers.get('Authorization'));
      localStorage.setItem('Authorization', res.headers.get('Authorization'));
      console.log(`user ${cred.username} logged in succefully`)
      window.location.reload();
    });

  }
}
