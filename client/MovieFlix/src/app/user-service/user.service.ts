import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

@Injectable()
export class UserService {
    constructor(private http: Http) {
    }

    getUsers(): Observable<any[]> {
        return this.http.get('http://mocker.egen.io/users')
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    getUserById(id: string): Observable<any[]> {
        return this.http.get(`http://mocker.egen.io/users/${id}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    createUser(user: any): Observable<any[]> {
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        return this.http.post('http://localhost:8080/movieflix/api/users', user, options)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    updateUser(id: string, user: any): Observable<any[]> {
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        return this.http.post(`http://mocker.egen.io/users/${id}`, user, options)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    getUser(email: string,password:string): Observable<any[]> {
        return this.http.get(`http://localhost:8080/movieflix/api/users/${email}/${password}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    isAdmin():string{
        var cookieValue = this.getCookie();
        if(cookieValue === undefined) {
            return "no";
        }
        else {
            var myRes = atob(cookieValue).split('??');
            if(atob(myRes[0]) == 'admin' && atob(myRes[1]) == 'admin')
                return atob(myRes[0]);
        }
        return "no";
    }

    getCookie():string{
        var x = document.cookie.split(';');
        var i = 0;
        var cookieValue = "";
        for( ; i <x.length; i++) {
            if(x[i].split('=')[0].trim() == 'sessionID') {
                cookieValue = x[i].split('=')[1];
                break;
            }
        }
       return cookieValue;
    }

    getID(username: string) : Observable<any[]> {
        return this.http.get(`http://localhost:8080/movieflix/api/users/${username}`)
            .map(response => {response.json();console.log("Respo: "+response.json())})
            .catch(error => Observable.throw(error.statusText));
    }

}

