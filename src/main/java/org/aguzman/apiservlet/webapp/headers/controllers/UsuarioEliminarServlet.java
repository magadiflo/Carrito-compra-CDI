package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Usuario;
import org.aguzman.apiservlet.webapp.headers.services.UsuarioService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/usuarios/eliminar")
public class UsuarioEliminarServlet extends HttpServlet {

    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        if(id > 0){
            Optional<Usuario> optional = this.service.porId(id);
            if(optional.isPresent()){
                this.service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/usuarios");
            }else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el usuario en la base de datos");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el id es null, se debe enviar como parámetro en la url");
        }
    }
}
