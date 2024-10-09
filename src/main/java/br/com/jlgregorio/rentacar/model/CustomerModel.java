package br.com.jlgregorio.rentacar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(length = 1, nullable = false)
    private String gender;

    @Column(name = "birthday", nullable = false)
    private Date birthDay;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityModel city;


}
