package com.fds.fooddeliverysystem.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4,max = 100)
    private String name;

    @NotNull
    @Email
    @Size(min = 5,max = 40)
    private String mail;

    @NotNull
    @Size(min = 7,max = 10)
    private String contact;

    @NotNull
    @Size(min = 10,max = 150)
    private String address;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime opening_Time;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closing_Time;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private BusinessWallet businessWallet;


}
