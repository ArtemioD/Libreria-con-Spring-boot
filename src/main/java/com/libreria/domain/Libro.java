package com.libreria.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "libro")
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotEmpty
    private String codigo;
    
    @NotEmpty
    private String titulo;
    
    @NotNull
    private double precio;
    
    // getters and setters
    
}
