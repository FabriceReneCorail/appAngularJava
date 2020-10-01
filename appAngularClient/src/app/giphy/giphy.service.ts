import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs//operators';


@Injectable({
  providedIn: 'root'
})
export class GiphyService {

  giphyApiKey ="//api.giphy.com/v1/gifs/search?api_key=9Xros18XifsTmhkNHvgiwgLR49SUbpqj&limit=1&q="

  constructor(public http : HttpClient) { }

  get(searchTerm){
    const apiLink = this.giphyApiKey + searchTerm;
    return this.http.get(apiLink).pipe(map((response: any) => {
     if(response.data.length > 0){
       return response.data[0].images.original.url;
     } else {
       return 'https://giphy.com/gifs/cat-dance-dancing-26BoElcdr7OiEHmfu';
     }
    }));

  }

}
