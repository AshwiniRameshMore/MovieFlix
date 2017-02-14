import {Component} from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {UserService} from '../user-service/user.service';

@Component({
    templateUrl: 'login.component.html'
})
export class LoginComponent {

    user: any = {};
    invalid: boolean = false;

    constructor(private route: ActivatedRoute, private userService: UserService, private router:Router) {
    }

    login(email:string,password:string){
        var key = btoa(btoa(email) + '??' + btoa(password));
        document.cookie = "sessionID="+key+';';

        this.userService.getUser(email,password)
            .subscribe(
                user =>
                {
                    this.user = user;
                    if(email=="admin" && password=="admin")
                        this.router.navigate(['/add_title']);
                    else if(this.user == true)
                        this.router.navigate(['/video_list']);
                    else
                        this.invalid = true;
                },
                error => console.log(error)
            );
    }
}