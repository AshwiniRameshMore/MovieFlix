import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VideoService} from '../video-service/video.service';
import {FormGroup} from "@angular/forms";
import {UserService} from "../user-service/user.service";

@Component({
    selector: 'editForm',
    templateUrl: 'edit-title.component.html'
})

export class EditTitleComponent {

    radio_val: string= "";
    video: any = {};
    success: boolean = false;
    username: string = "";

    constructor(private route: ActivatedRoute, private videoService: VideoService, private router:Router,private userService:UserService) {

        if(userService.isAdmin()== "no"){
            this.router.navigate(['/login']);
        }
        else {
            this.username = userService.isAdmin();
            this.route.params.subscribe(
                params => {
                    videoService.getVideoById(params['videoId'])
                        .subscribe(
                            video => this.video = video,
                            error => console.log(error)
                        );
                });
        }
    }

    updateVideo(videoId:string,inputTitle:string,year:string,rated:string,inputReleased:string,inputRuntime:string,genre:string,inputDirector:string,
             inputWriter:string,inputActor:string,inputPlot:string,language:string,country:string,inputAward:string,image:string,inputMetascore:string,
             inputRating:string,inputVotes:string,inputId:string, rad_type:string){
        this.video = {
            "videoId": videoId,
            "title": inputTitle,
            "year": year,
            "rated": rated,
            "released": inputReleased,
            "runtime": inputRuntime,
            "genre": genre,
            "director": inputDirector,
            "writer": inputWriter,
            "actors": inputActor,
            "plot": inputPlot,
            "language": language,
            "country": country,
            "awards": inputAward,
            "poster": image,
            "metaScore": inputMetascore,
            "imdbRating": inputRating,
            "imdbVotes": inputVotes,
            "imdbId": inputId,
            "type": rad_type
        };
        this.videoService.updateVideo(this.video, inputTitle)
            .subscribe(
                video =>
                {
                    this.video = video;
                    this.success = true;
                },
                error => console.log(error)
            );
    }

    private clickRadio(element: HTMLInputElement): void {
        this.radio_val = element.value;
    }

    createRange(num:number){
        var items: number[] = [];
        for(var i = 1940; i <= num; i++){
            items.push(i);
        }
        return items;
    }
}