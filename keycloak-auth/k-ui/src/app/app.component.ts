import { Component } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private app: AppService, private http: HttpClient, private router: Router){
    console.log('constructor run');
    this.app.authenticate(null, () => {});
  }

  logout(){
    this.http.post('logout', {}).pipe(finalize(() => {
          console.log('logout run');
            this.app.authenticated = false;
            this.router.navigateByUrl('/login');
          }))
    .subscribe()
  }
}
