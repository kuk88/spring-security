import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient } from '@angular/common/http';

@Component({
  templateUrl: './home.component.html'
})
export class HomeComponent {

  title = 'Demo';
  greeting: Model = new Model;

  constructor(private app: AppService, private http: HttpClient) {
    http.get<Model>('resource').subscribe(data => this.greeting = data);
  }

  authenticated() { return this.app.authenticated; }

}

class Model{
  id: string = 'No id';
  content: string = 'No Content';
}
