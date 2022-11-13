import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  
  productItemsList: any;
  orderId!: number;
  constructor(private http:HttpClient, private activatedRoute: ActivatedRoute, private router: Router) { 
    this.orderId = activatedRoute.snapshot.params['orderId'];
  }

  ngOnInit(): void {
    let url = `http://localhost:8989/gateway-service/order-service/orders/${this.orderId}/productItemList` 

    this.http.get(url).subscribe({
      next: data => {
        this.productItemsList = data;
      },
      error: err =>{
        console.log(err);
      }
    });

    
  }


}
