package br.com.jlgregorio.rentacar.service;

import br.com.jlgregorio.rentacar.dto.CustomerDto;
import br.com.jlgregorio.rentacar.exception.ResourceNotFoundException;
import br.com.jlgregorio.rentacar.mapper.CustomModelMapper;
import br.com.jlgregorio.rentacar.model.CustomerModel;
import br.com.jlgregorio.rentacar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public CustomerDto create(CustomerDto customerDto){
        CustomerModel customerModel = CustomModelMapper.parseObject(customerDto, CustomerModel.class);
        return CustomModelMapper.parseObject(repository.save(customerModel), CustomerDto.class);
    }

    public CustomerDto findById(long id){
        CustomerModel customerModel = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Cliente não encontrado!"));
        return CustomModelMapper.parseObject(customerModel, CustomerDto.class);
    }

}
