package com.joaolevi.paymentservice.model;

import lombok.Getter;
import java.io.Serializable;

@Getter // To create 'get' methods
public class Payment implements Serializable{

    private Long id;
    private Long idUser;
    private Long idProduct;
    private String cardNumber;
    
}
