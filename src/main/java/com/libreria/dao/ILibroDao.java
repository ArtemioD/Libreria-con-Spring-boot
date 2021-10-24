package com.libreria.dao;

import com.libreria.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ILibroDao extends JpaRepository<Libro, Long> {
    
}
