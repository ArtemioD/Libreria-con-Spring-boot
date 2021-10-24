
package com.libreria.service;

import com.libreria.dao.ILibroDao;
import com.libreria.domain.Libro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServiceImp implements ILibroService {
    
    @Autowired
    private ILibroDao libroDao;

    @Override
    @Transactional(readOnly = true) // solo lectura
    public List<Libro> listarLibros() {
        return libroDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Libro libro) {
        libroDao.save(libro);
    }

    @Override
    @Transactional
    public void eliminar(Libro libro) {
        libroDao.delete(libro);
    }

    @Override
    @Transactional(readOnly = true) // solo lectura
    public Libro buscar(Libro libro) {
        // busca por id si no encuentra regresa null
        return libroDao.findById(libro.getId()).orElse(null);
    }
    
}
