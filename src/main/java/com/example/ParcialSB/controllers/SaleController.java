package com.example.ParcialSB.controllers;

import com.example.ParcialSB.entities.Customer;
import com.example.ParcialSB.entities.Product;
import com.example.ParcialSB.entities.Sale;
import com.example.ParcialSB.responses.ResponseHandler;
import com.example.ParcialSB.services.CustomerService;
import com.example.ParcialSB.services.ProductService;
import com.example.ParcialSB.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("sales")
public class SaleController {
    @Autowired
    SaleService saleService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findCustomer(@PathVariable Integer id) {

        try {

            Customer result = saleService.getCustomer(id);

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    @GetMapping("/sales/{id}")
    public ResponseEntity<Object> findProducts(@PathVariable Integer id) {

        try {

            List<Product> result = saleService.getProducts(id);

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    @PostMapping("/{id}")
    public ResponseEntity<Object> save(@RequestBody Sale sale, @PathVariable Integer id ){
        try {
            List<Product> venta = new ArrayList<>();
            Customer customer = customerService.findById( id );
            if( customer != null ){

                for(Product product:sale.getProducts()){
                    Boolean optionalp = productService.findById(product.getId());
                    if(optionalp==true){
                        Boolean stock=saleService.validarStock(product,product.getStock());
                        if(stock==true){
                            venta.add(product);
                        }
                    }
                }
                Sale result = saleService.save( sale, customer,venta );
                return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED,sale );
            }
            return ResponseHandler.generateResponse("Success Customer",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
