import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  ordersList: any;
  customerId!: number;
  customer: any;
  constructor(private http:HttpClient, private activatedRoute: ActivatedRoute) { 
    this.customerId = activatedRoute.snapshot.params['customerId'];
  }

  ngOnInit(): void {
    let url = `http://localhost:8989/gateway-service/order-service/orders/search/byCustomerId?projection=fullOrder&customerId=${this.customerId}` 
    let url_customer = `http://localhost:8989/gateway-service/customer-service/customers/${this.customerId}?projection=fullCustomer` 

    this.http.get(url).subscribe({
      next: data => {
        this.ordersList = data;
      },
      error: err =>{
        console.log(err);
      }
    });

    this.http.get(url_customer).subscribe({
      next: data => {
        this.customer = data;
      },
      error: err =>{
        console.log(err);
      }
    });
  }

}
