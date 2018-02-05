import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../user-service/user.service';
import {VideoService} from '../video-service/video.service';

@Component({
    templateUrl: 'video-list.component.html'
})
export class VideoListComponent {
    videoList: any = [];
    user: any = {};
    username:string = "";

    constructor(private videoService: VideoService, private userService: UserService,private router:Router) {
       var cookieValue = this.userService.getCookie();
       if(cookieValue === undefined) {
              this.router.navigate(['/login']);
       }
       else {
            var myRes = atob(cookieValue).split('??');
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

    createRange(num:number){
        var items: number[] = [];
        for(var i = 1940; i <= num; i++){
            items.push(i);
        }
        return items;
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

    onSearch(searchtext:string){
     this.videoService.getSearchTitle(searchtext)
         .subscribe(
             videos => this.videoList = videos,
             error => console.log(error)
         );
    }

    onFilter(type:string,year:string,genre:string,sort:string){
     this.videoService.getByFilter(type,year,genre,sort)
        .subscribe(
            videos => this.videoList = videos,
            error => console.log(error)
        );
     }

}