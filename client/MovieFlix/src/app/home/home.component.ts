import {Component} from '@angular/core';
import {UserService} from '../user-service/user.service';

@Component({
    templateUrl: 'home.component.html'
})
export class HomeComponent {

    userList: any = [];

    constructor(private userService: UserService) {

        userService.getUsers()
            .subscribe(
                users => this.userList = users,
                error => console.log(error)
            );
    }
}