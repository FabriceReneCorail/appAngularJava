import { Component, OnInit } from '@angular/core';
import { GiphyService } from '../giphy/giphy.service';
import { CarService } from '../shared/car/car.service';

@Component({
  selector: 'app-list-car',
  templateUrl: './list-car.component.html',
  styleUrls: ['./list-car.component.css']
})
export class ListCarComponent implements OnInit {
  cars: Array<any>;

  constructor( private carService : CarService, private giphyService : GiphyService) { }

  ngOnInit(): void {
    this.carService.getAll().subscribe((data)=>{
      this.cars = data;
      for ( const car of this.cars){
        this.giphyService.get(car.name).subscribe( url => car.giphyUrl = url)
      }
    }
    )
  }

}
