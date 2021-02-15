package sn.esp.filter;


import sn.esp.entities.Utilisateur;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "admin-filter", value = {"/lists-utilisateurs", "/modifier-user", "/supprimer-user"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Utilisateur user = (Utilisateur) session.getAttribute("sessionUser");

        if (user == null) {
            response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/erreur");

        } else {
            if (!user.getRole().equals("ROLE_ADMIN")) {
                response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/erreur");
            } else {
                filterChain.doFilter(request, response);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
