import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

class Model{
  id: string = 'ZZZ';
  content: string = 'No Content';
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Demo';
  greeting: Model = new Model;
  constructor(private http: HttpClient){
  console.log('constructor run');
    http.get<Model>('resource').subscribe(data => this.greeting = data)
  }
}
