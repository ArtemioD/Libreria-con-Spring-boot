package com.libreria.service;

import com.libreria.dao.IUsuarioDao;
import com.libreria.domain.Rol;
import com.libreria.domain.Usuario;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // recuperamos el usuario filtrado por username
        Usuario usuario = usuarioDao.findByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        // recuperamos los roles del usuario
        var roles = new ArrayList<GrantedAuthority>();
        for(Rol rol: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        // return el objeto con los parametros recuperados
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
