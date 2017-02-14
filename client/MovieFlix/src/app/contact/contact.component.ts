import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../user-service/user.service';

@Component({
  templateUrl: 'contact.component.html'
})
export class ContactComponent {

  user: any = {};

  constructor(private route: ActivatedRoute, private userService: UserService) {
    this.route.params.subscribe(
        params => {
          userService.getUserById(params['id'])
              .subscribe(
                  user => this.user = user,
                  error => console.log(error)
              );
        });
  }
}

