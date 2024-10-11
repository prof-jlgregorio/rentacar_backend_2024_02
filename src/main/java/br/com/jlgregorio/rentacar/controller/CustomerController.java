package br.com.jlgregorio.rentacar.controller;

import br.com.jlgregorio.rentacar.dto.CustomerDto;
import br.com.jlgregorio.rentacar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
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

    @PutMapping
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto){
        CustomerDto customerUpdated = customerService.update(customerDto);
        return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") long id){
        customerService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAll(){
        var customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
