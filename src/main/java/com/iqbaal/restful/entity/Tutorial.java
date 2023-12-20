package com.iqbaal.restful.entity;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tutorial {
    @Id
    @GeneratedValue
    private BigInteger id;

    private String description;

    private boolean published;

    private String title;
    
}
