package br.com.jlgregorio.rentacar.service;

import br.com.jlgregorio.rentacar.dto.CustomerDto;
import br.com.jlgregorio.rentacar.exception.ResourceNotFoundException;
import br.com.jlgregorio.rentacar.mapper.CustomModelMapper;
import br.com.jlgregorio.rentacar.model.CityModel;
import br.com.jlgregorio.rentacar.model.CustomerModel;
import br.com.jlgregorio.rentacar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CustomerDto update(CustomerDto customerDto){
        CustomerModel customerModel = repository.findById(customerDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Cliente não encontrado!")
        );
        customerModel.setBirthDay(customerDto.getBirthDay());
        customerModel.setGender(customerDto.getGender());
        customerModel.setFullName(customerDto.getFullName());
        //parse or convert cityDto to cityModel before setting the value
        customerModel.setCity(CustomModelMapper.parseObject(customerDto.getCity(), CityModel.class));
        //convert the model to dto to return
        return CustomModelMapper.parseObject(repository.save(customerModel), CustomerDto.class);
    }

    public void delete(long id){
        CustomerModel customerModel = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Cliente não encontrado!")
        );
        repository.delete(customerModel);
    }

    public List<CustomerDto> findAll(){
        var customers = repository.findAll();
        return CustomModelMapper.parseObjectList(customers, CustomerDto.class);
    }

}
