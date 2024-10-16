package br.com.jlgregorio.rentacar.controller;

import br.com.jlgregorio.rentacar.dto.CustomerDto;
import br.com.jlgregorio.rentacar.exception.CustomExceptionResponse;
import br.com.jlgregorio.rentacar.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customers", description = "Endpoint usado para operações que envolvem Customers")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cria ou cadastra um novo Customer (Cliente)", tags = {"Customer"},responses = {
            @ApiResponse(description = "CREATED", responseCode = "201", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomerDto.class)
            )}),
    })
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto){
        CustomerDto customer = customerService.create(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }


    @Operation(summary = "Recuperar um Customer (ou Cliente) mediante um ID informado", tags = {"Customer"},
        responses = {
                @ApiResponse(description = "Customer recuperado com sucesso!", responseCode = "200", content = {@Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = CustomerDto.class)
                )}),
                @ApiResponse(description = "Resource not found", responseCode = "404", content = {@Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = CustomExceptionResponse.class)
                )})
        }
    )
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
