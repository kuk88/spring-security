import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  templateUrl: './home.component.html'
})
export class HomeComponent {

  title = 'Demo';
  greeting: Greeting = new Greeting;

  constructor(private app: AppService, private http: HttpClient) {
    http.get<Token>('token').subscribe(data => {
        const token: string = data.token;
        console.log(token);
        http.get<Greeting>('http://localhost:9000',
                        { headers: new HttpHeaders().set('X-Auth-Token', token) })
                        .subscribe(data => this.greeting = data);
    });

  }

  authenticated() { return this.app.authenticated; }

}

class Greeting{
  id: string = 'No id';
  content: string = 'No Content';
}

class Token{
  token: string = ''
}
