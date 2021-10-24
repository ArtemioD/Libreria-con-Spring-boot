package com.libreria.web;

import com.libreria.dao.ILibroDao;
import com.libreria.domain.Libro;
import com.libreria.service.ILibroService;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controlador {
    
    //@Autowired
    //private ILibroDao libroDao;
    @Autowired
    private ILibroService libroService;
    
    @GetMapping("/")
    public String inicio(Model m, Libro libro) {
        //m.addAttribute("libros", test());
        //var libros = libroDao.findAll(); // recuperamos libros de BBDD
        m.addAttribute("libros", librosDeBBDD());
        m.addAttribute("fecha", fecha());
        return "index";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Libro libro, Errors errors, Model m) {
        if(errors.hasErrors()) {
            // si campo vasio, refrescamos la pagina con errores
            m.addAttribute("libros", librosDeBBDD());
            m.addAttribute("fecha", fecha());
            return "index";
        }
        libroService.guardar(libro);
        return "redirect:/";
    }
    
    @GetMapping("/guardar")
    public String guardar2(@Valid Libro libro, Errors errors, Model m) {
        /* metodo get para que no salta error despues de mostrar 
        mensajes de que muestra error y tratar de cambiar la idioma 
        (necesita metodo get para funcionar)*/
        if(errors.hasErrors()) {
            // si campo vasio, refrescamos la pagina con errores
            m.addAttribute("libros", librosDeBBDD());
            m.addAttribute("fecha", fecha());
            return "index";
        }
        libroService.guardar(libro); 
        return "redirect:/";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(Libro libro, Model model) {
        // buscamos el libro por id y la asignamos a la misma variable
        libro = libroService.buscar(libro);
        model.addAttribute("libro", libro);
        model.addAttribute("fecha", fecha());
        return "modificar";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(Libro libro) {
        libroService.eliminar(libro);
        return "redirect:/";
    }
    
    private String fecha() {
        Calendar cal = Calendar.getInstance();
        //System.out.println("The Current Year is: " + cal.get(Calendar.YEAR));
        return "" + cal.get(Calendar.YEAR);
    }
    
    private List<Libro> librosDeBBDD() {
        return libroService.listarLibros();
    }
    
    private List<Libro> test() {
        var libro = new Libro();
        libro.setId(1254);
        libro.setTitulo("Batman y SuperMan");
        libro.setPrecio(25.50);
        
        var libro2 = new Libro();
        libro2.setId(5555);
        libro2.setTitulo("Batman");
        libro2.setPrecio(5.25);
       
        return Arrays.asList(libro, libro2);
    }
    
}
