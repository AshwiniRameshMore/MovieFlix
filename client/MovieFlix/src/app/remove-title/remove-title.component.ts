import {Component} from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';
import {UserService} from '../user-service/user.service';
import {VideoService} from '../video-service/video.service';

@Component({
    templateUrl: 'remove-title.component.html'
})
export class RemoveTitleComponent {
    videoList: any = [];
    video: any =[];
    username:string = "";

    constructor(private route: ActivatedRoute, private videoService: VideoService, private router:Router,private userService:UserService) {
        if(userService.isAdmin()== "no"){
            this.router.navigate(['/login']);
        }
        else {
            this.username = userService.isAdmin();
            this.showAll();
        }
    }

    showAll() {
        this.videoService.getVideos()
            .subscribe(
                videos => this.videoList = videos,
                error => console.log(error)
            );
    }

    delete(title:string){
       if(confirm("Are you sure you want to delete the title - "+title+" ?") == true) {
           this.videoService.deleteTitle(title)
               .subscribe(
                   videos => this.videoList = videos,
                   error => console.log(error)
               );
       }
    }

    edit(title:string){
        this.videoService.getVideoByTitle(title)
            .subscribe(
                video => {this.video = video;
                this.router.navigate(['/add_title'],video)},
                error => console.log(error)
            );
    }
}