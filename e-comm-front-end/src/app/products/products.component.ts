import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  productsList: any;
  constructor(private http:HttpClient) { } // injection

  ngOnInit(): void {
    this.http.get("http://localhost:8989/gateway-service/inventory-service/products?projection=fullProduct").subscribe(
      {
        next: (data)=>{
          this.productsList = data;
          console.log(data);
        },
        error:(err)=>{
          console.log(err);
        }
      }
    );
}
}
