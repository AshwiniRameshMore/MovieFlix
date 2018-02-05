import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {VideoService} from '../video-service/video.service';
import {UserService} from "../user-service/user.service";

@Component({
  templateUrl: 'add-title.component.html'
})

export class AddTitleComponent {
     video: any = {};
     success: boolean = false;
     username:string = "";
     radio_val:string = "";

  constructor(private route: ActivatedRoute, private videoService: VideoService,private router:Router,private userService:UserService) {
      if(userService.isAdmin()== "no"){
          this.router.navigate(['/login']);
      }
      else {
          this.username = userService.isAdmin();
      }
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

   addVideo(inputTitle:string,year:string,rated:string,inputReleased:string,inputRuntime:string,genre:string,inputDirector:string,
    inputWriter:string,inputActor:string,inputPlot:string,language:string,country:string,inputAward:string,image:string,inputMetascore:string,
    inputRating:string,inputVotes:string,inputId:string){
        this.video = {
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
            "type": this.radio_val
        };

        this.videoService.createVideo(this.video)
            .subscribe(
                video =>
                {
                    this.video = video;
                    this.success = true;
                 },
                error => console.log(error)
            );
    }
}