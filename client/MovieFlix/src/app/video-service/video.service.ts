import {Injectable} from '@angular/core';
import {Http, RequestOptions, Headers} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

@Injectable()
export class VideoService {
  constructor(private http: Http){}

  createVideo(video: any): Observable<any[]> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post('http://localhost:8080/movieflix/api/videos', video, options)
        .map(response => response.json())
        .catch(error => Observable.throw(error.statusText));
  }

  getVideos(): Observable<any[]> {
    return this.http.get('http://localhost:8080/movieflix/api/videos')
                    .map(response => response.json())
                    .catch(error => Observable.throw(error.statusText));
  }

  getMovies(): Observable<any[]> {
        return this.http.get('http://localhost:8080/movieflix/api/videos/type=movie/year=0/genre=null/sort=year')
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
  }

  getSeries(): Observable<any[]> {
        return this.http.get('http://localhost:8080/movieflix/api/videos/type=series/year=0/genre=null/sort=year')
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
  }

  getTopMovies(): Observable<any[]> {
        return this.http.get('http://localhost:8080/movieflix/api/videos/top=movie')
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
  }

  getTopSeries(): Observable<any[]> {
        return this.http.get('http://localhost:8080/movieflix/api/videos/top=series')
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
  }

  getVideoById(videoId: string): Observable<any[]> {
    return this.http.get(`http://localhost:8080/movieflix/api/videos/id/${videoId}`)
        .map(response => response.json())
        .catch(error => Observable.throw(error.statusText));
  }

  getCommentsById(videoId: string): Observable<any[]> {
    return this.http.get(`http://localhost:8080/movieflix/api/comments/${videoId}`)
        .map(response => response.json())
        .catch(error => Observable.throw(error.statusText));
  }

  getRatingById(videoId: string): Observable<any[]> {
    return this.http.get(`http://localhost:8080/movieflix/api/ratings/${videoId}`)
        .map(response => response.json())
        .catch(error => Observable.throw(error.statusText));
  }

  getSearchTitle(title: string): Observable<any[]> {
        return this.http.get(`http://localhost:8080/movieflix/api/videos/search=${title}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
  }

  getByFilter(type: string, year: string, genre: string, sort: string) : Observable<any[]> {
        return this.http.get(`http://localhost:8080/movieflix/api/videos/type=${type}/year=${year}/genre=${genre}/sort=${sort}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
  }

  deleteTitle(title: string) {
      return this.http.delete(`http://localhost:8080/movieflix/api/videos/${title}`)
          .map(response => response.json())
          .catch(error => Observable.throw(error.statusText));
  }

  getVideoByTitle(title: string) : Observable<any[]> {
      return this.http.get(`http://localhost:8080/movieflix/api/videos/${title}`)
          .map(response => response.json())
          .catch(error => Observable.throw(error.statusText));
  }

  updateVideo(video: any, title:string) : Observable<any[]> {
      let headers = new Headers({'Content-Type': 'application/json'});
      let options = new RequestOptions({headers: headers});
      return this.http.put(`http://localhost:8080/movieflix/api/videos/${title}`, video, options)
          .map(response => response.json())
          .catch(error => Observable.throw(error.statusText));
  }

  addComment(videoId: string,comment:string): Observable<any[]> {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });
      return this.http.post(`http://localhost:8080/movieflix/api/comments/${videoId}/comment=${comment}`, options)
          .map(response => response.json())
          .catch(error => Observable.throw(error.statusText));
  }

  rate(videoId: string, userId: string, star: string) : Observable<any[]> {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });
      return this.http.post(`http://localhost:8080/movieflix/api/ratings/${videoId}/${userId}/rating=${star}`, options)
          .map(response => response.json())
          .catch(error => Observable.throw(error.statusText));
  }
}

