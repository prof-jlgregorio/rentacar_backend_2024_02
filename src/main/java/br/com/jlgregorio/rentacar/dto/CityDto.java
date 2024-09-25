package br.com.jlgregorio.rentacar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CityDto {

    private long id;
    private String name;
    private String state;

    public String getFullName(){
        return name + "/" + state;
    }

}
