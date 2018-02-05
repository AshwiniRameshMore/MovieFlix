import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VideoService} from '../video-service/video.service';
import {UserService} from "../user-service/user.service";

@Component({
    templateUrl: 'video-detail.component.html'
})
export class VideoDetailComponent implements OnInit{

    videoList: any = [];
    video: any = {};
    commentList: any = [];
    rating: any = {};
    comment: string = "";
    userId:any =[];
    user: any = {};
    username:string = "";
    showUser:boolean = false;
    showAdmin:boolean= false;

    constructor(private route: ActivatedRoute, private videoService: VideoService, private userService: UserService,private router:Router) {
        var cookieValue = this.userService.getCookie();

        if(cookieValue === undefined)
            this.router.navigate(['/login']);
        else {
        var myRes = atob(cookieValue).split('??');
        if(atob(myRes[0]) == "admin")
            this.showAdmin = true;
        else
            this.showUser = true;

        this.userService.getUser(atob(myRes[0]),atob(myRes[1]))
            .subscribe(
                user =>
                {
                    this.user = user;
                    if(this.user == true)
                       this.username = atob(myRes[0]);
                    else
                       this.router.navigate(['/login']);
                },
                error => console.log(error)
            );
        }
    }
    ngOnInit(): void {
        this.route.params.subscribe(
            params => {
                this.videoService.getVideoById(params['videoId'])
                    .subscribe(
                        video => this.video = video,
                        error => console.log(error)
                    );

                this.videoService.getCommentsById(params['videoId'])
                    .subscribe(
                        comments => this.commentList = comments,
                        error => console.log(error)
                    );

                this.videoService.getRatingById(params['videoId'])
                    .subscribe(
                        rating => this.rating = rating,
                        error => console.log(error)
                    );
            });
    }

    addComment(comment_str:string,videoId:string){
     this.videoService.addComment(videoId,comment_str)
            .subscribe(
               error => console.log(error)
            );

    }
    rate(star:string,videoId:string){
        this.videoService.rate(videoId,star)
            .subscribe(
                error => console.log(error)
            );
    }
}