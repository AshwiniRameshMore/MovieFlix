import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VideoService} from '../video-service/video.service';
import {userInfo} from "os";
import {UserService} from "../user-service/user.service";

@Component({
    templateUrl: 'video-detail.component.html'
})
export class VideoDetailComponent implements OnInit{


    videoList: any = [];
    video: any = {};
    commentList: any = [];
    rating: any= {};
    comment: string ="";
    userId:any =[];
    user: any = {};
    username:string ="";
showUser:boolean=false;
showAdmin:boolean=false;

    constructor(private route: ActivatedRoute, private videoService: VideoService, private userService: UserService,private router:Router) {
    var cookieValue = this.userService.getCookie();
    if(cookieValue === undefined) {
    this.router.navigate(['/login']);
} else {
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
                {
                    this.username = atob(myRes[0]);
                    this.showAll();
                }
                else {
                    this.router.navigate(['/login']);
                }
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

    showAll(){
        this.videoService.getVideos()
            .subscribe(
                videos => this.videoList = videos,
                error => console.log(error)
            );
    }


    showMovies(){
        this.videoService.getMovies()
            .subscribe(
                videos => this.videoList = videos,
                error => console.log(error)
            );
    }
    showSeries(){
        this.videoService.getSeries()
            .subscribe(
                videos => this.videoList = videos,
                error => console.log(error)
            );
    }


    showTopMovies(){
        this.videoService.getTopMovies()
            .subscribe(
                videos => this.videoList = videos,
                error => console.log(error)
            );
    }
    showTopSeries(){
        this.videoService.getTopSeries()
            .subscribe(
                videos => this.videoList = videos,
                error => console.log(error)
            );
    }

    addComment(comment_str:string,videoId:string){
     this.videoService.addComment(videoId,comment_str)
            .subscribe(
               // comment => this.comment = comment,
                error => console.log(error)
            );

    }
    rate(star:string,videoId:string){
        this.userId = "680ff4c3-b6fb-478e-91a5-82d2a2e546aa";
        this.videoService.rate(videoId,this.userId,star)
            .subscribe(
              //  comment => {},//this.comment = comment,
                error => console.log(error)
            );
    }
}