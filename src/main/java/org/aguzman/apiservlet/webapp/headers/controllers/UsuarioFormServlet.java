package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Usuario;
import org.aguzman.apiservlet.webapp.headers.services.UsuarioService;
import org.aguzman.apiservlet.webapp.headers.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/form")
public class UsuarioFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        Usuario usuario = new Usuario();
        if(id > 0){
            Optional<Usuario> optional = service.porId(id);
            if(optional.isPresent()){
                usuario = optional.get();
            }
        }
        req.setAttribute("title", req.getAttribute("title") + " | Registro de usuario");
        req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/usuarios/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        Map<String, String> errores = new HashMap<>();
        if (username == null || username.trim().isEmpty()) {
            errores.put("username", "El username es requerido");
        }

        if (password == null || password.trim().isEmpty()) {
            errores.put("password", "El password es requerido");
        }

        if (email == null || email.trim().isEmpty()) {
            errores.put("email", "El email es requerido");
        }

        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);

        if(errores.isEmpty()){
            service.guardar(usuario);
            resp.sendRedirect(req.getContextPath() + "/usuarios");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("title", req.getAttribute("title") + " | Registro de usuario");
            req.setAttribute("usuario", usuario);
            getServletContext().getRequestDispatcher("/usuarios/form.jsp").forward(req, resp);
        }
    }
}
