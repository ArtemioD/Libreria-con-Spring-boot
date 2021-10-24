package com.libreria.dao;

import com.libreria.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    
    Usuario findByUsername(String username);
}
