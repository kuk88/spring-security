import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient } from '@angular/common/http';

@Component({
  templateUrl: './home.component.html'
})
export class HomeComponent {

  title = 'Demo';
  greeting: Greeting = new Greeting;

  constructor(private app: AppService, private http: HttpClient) {
    http.get<Greeting>('http://localhost:9000').subscribe(data => this.greeting = data);
  }

  authenticated() { return this.app.authenticated; }

}

class Greeting{
  id: string = 'No id';
  content: string = 'No Content';
}
