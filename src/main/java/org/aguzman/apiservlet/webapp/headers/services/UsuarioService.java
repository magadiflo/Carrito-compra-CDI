package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar();

    void guardar(Usuario usuario);

    void eliminar(Long id);

    Optional<Usuario> login(String username, String password);

    Optional<Usuario> porId(Long id);

}
