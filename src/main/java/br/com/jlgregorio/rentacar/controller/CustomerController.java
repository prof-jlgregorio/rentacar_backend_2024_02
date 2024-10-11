package br.com.jlgregorio.rentacar.controller;

import br.com.jlgregorio.rentacar.dto.CustomerDto;
import br.com.jlgregorio.rentacar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto){
        CustomerDto customer = customerService.create(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable(name = "id") long id){
        CustomerDto customer = customerService.findById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
