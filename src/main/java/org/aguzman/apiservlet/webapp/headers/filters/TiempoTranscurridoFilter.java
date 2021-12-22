package org.aguzman.apiservlet.webapp.headers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.Logger;

@WebFilter("/*")
public class TiempoTranscurridoFilter implements Filter {

    private static final Logger logger = Logger.getLogger("TiempoTranscurridoFilter");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long inicio = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long fin = System.currentTimeMillis();
        long resultado = fin - inicio;
        System.out.println("El tiempo de carga de la página es de " + resultado + " milisegundos");
        logger.info(String.format("El tiempo de carga de la página es de %s milisegundos", resultado));
    }
}
