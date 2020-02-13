import { Router } from '@angular/router';
import { SessionStorageService } from 'angular-web-storage';
import { MAT_DIALOG_DATA } from '@angular/material';
import { Component, Inject } from '@angular/core';


@Component({
  selector: 'app-expense-tracker',
  templateUrl: './expense-tracker.component.html',
  styleUrls: ['./expense-tracker.component.scss']
})
export class ExpenseTrackerComponent  {
  status: number;
  chip = [
    {
      isTrue:false,
      colorChange: 'primary'
    },
    {
      isTrue:false,
      colorChange: 'primary'
    },
    {
      isTrue:false,
      colorChange: 'primary'
    },
    {
      isTrue:false,
      colorChange: 'primary'
    },
    {
      isTrue:false,
      colorChange: 'primary'
    }
  ];

  constructor(@Inject(MAT_DIALOG_DATA) data, public session: SessionStorageService, public router: Router) {
    this.status = data.status;
  }  

  ngOnInit() {
    if ((!this.session.get("key") && !this.session.get("2")) ) {            
      this.router.navigate(['/login']);    
    }
    else if(this.session.get("key").empId == 0) {      
      this.router.navigate(['/login']);
    }
    else {      
      if(this.status <= 5) {

        for(let i = 0; i < this.status; i++) {        
            this.chip[i].isTrue = true;
        }    
      }
      else {        

        for(let i = 0; i <= this.status % 5; i++) {                
          this.chip[i].isTrue = true;  

          if(i == this.status % 5)
            this.chip[i].colorChange = 'warn';
        }           
      }
    } 
  }    

}