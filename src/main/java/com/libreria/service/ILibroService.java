/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.service;

import com.libreria.domain.Libro;
import java.util.List;

/**
 *
 * @author Artem
 */
public interface ILibroService {
    
    public List<Libro> listarLibros();
    
    public void guardar(Libro libro);
    
    public void eliminar(Libro libro);
    
    public Libro buscar(Libro libro);
    
    
    
}
