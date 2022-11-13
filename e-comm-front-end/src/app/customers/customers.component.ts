import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customersList: any;
  constructor(private http:HttpClient, private router: Router) { }

  ngOnInit(): void {
    // getting data
    this.http.get("http://localhost:8989/gateway-service/customer-service/customers?projection=fullCustomer").subscribe({
      next:(data)=>{
          this.customersList = data;
      },
      error: (err)=>{
        console.log(err)
      }
    });
  }

  getCustomerOrders(customer: any):void {
    console.log(customer);
    this.router.navigateByUrl(`/orders/${customer.id}`);
  }

}
