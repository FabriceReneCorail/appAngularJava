import { Component, OnInit } from '@angular/core';
import { CarService } from '../shared/car/car.service';

@Component({
  selector: 'app-list-car',
  templateUrl: './list-car.component.html',
  styleUrls: ['./list-car.component.css']
})
export class ListCarComponent implements OnInit {
  cars: Array<any>;

  constructor( private carService : CarService) { }

  ngOnInit(): void {
    this.carService.getAll().subscribe((data)=>{
      this.cars = data;
    }
    )
  }

}
