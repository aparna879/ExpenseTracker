
import { Router, ActivatedRoute } from '@angular/router';
import { SessionStorageService } from 'angular-web-storage';
import { MatDialog } from '@angular/material/dialog';
import { Component } from '@angular/core';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  name: any
  isAuthorized = false;
  title = 'ReimburseIT';
  ifNotJunior = false;
  ifNotAdmin = false;
  login = true;


  constructor(public dialog: MatDialog, public session: SessionStorageService,
    public router: Router, private route: ActivatedRoute) { }

  ngOnInit() {

    if ((!this.session.get("key") && !this.session.get("2"))) {
      this.login = window.location.pathname.toString() == '/login' ? false : true;
      this.router.navigate(['/login']);

    }
    else if (this.session.get("key").empId == 0) {
      this.login = window.location.pathname.toString() == '/login' ? false : true;
      this.router.navigate(['/login']);
    }
    else {
      const data = this.session.get("key").empDesignation;

      this.name = this.session.get("key").empFirstName;
      this.ifNotJunior = this.session.get("key").empDesignation == 'Junior' || this.session.get("key").empDesignation == 'admin' ? false : true;
      this.isAuthorized = this.session.get("key").empDesignation == 'admin' ? true : false;
      this.ifNotAdmin = this.session.get("key").empDesignation != 'admin' ? true : false;
      this.login = window.location.pathname.toString() == '/login' ? false : true;

      console.log(data);
    }
  }

  caller($event: string) {
    // console.log(arg$);
    console.log("In test (app component)");
    this.name = this.session.get("key").empFirstName;
    this.ifNotJunior = this.session.get("key").empDesignation != 'Junior' ? true : false;
    this.isAuthorized = this.session.get("key").empDesignation == 'Admin' ? true : false;
    this.ifNotAdmin = this.session.get("key").empDesignation != 'Admin' ? true : false;
    console.log(window.location.pathname.toString());
    this.login = window.location.pathname.toString() == '/login' ? false : true;
  }



  logOut() {
    this.session.remove("key");
    this.session.remove("2");
    this.router.navigate(["/login"]);
  }
}
