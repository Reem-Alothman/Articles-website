import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  items: MenuItem[] = [];

  constructor(){
  
  }

  ngOnInit(){
    this.items = [
      {label:"login", icon:"pi pi-fw pi-user", routerLink:"/"},
      {label:'register', icon:'pi pi-user-plus', routerLink:"/user"},
      {label:'articles', icon: 'pi pi-globe', routerLink:"/article"},
      {label:'logout', icon:'pi pi-power-off', routerLink:"/logout"}
      
    ];
  }
}
