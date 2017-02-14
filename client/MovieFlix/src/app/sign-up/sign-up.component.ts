import {Component} from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {UserService} from '../user-service/user.service';

@Component({
    templateUrl: 'sign-up.component.html'
})
export class SignupComponent {

    user: any = {};

    constructor(private route: ActivatedRoute, private userService: UserService, private router:Router) {
    }

    onSignup(first_name:string,last_name:string,email:string,password:string,confirm:string){
        if(confirm == password) {
            this.user = {
                "username": email,
                "password": password,
                "role": "user",
                "firstname": first_name,
                "lastname": last_name
            };
            this.userService.createUser(this.user)
                .subscribe(
                    user => {this.user = user;
                    this.router.navigate(['/login'])},
                    error => console.log(error)
                );
        }
    }
}