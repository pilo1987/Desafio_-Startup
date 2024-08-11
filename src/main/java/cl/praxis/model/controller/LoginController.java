package cl.praxis.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cl.praxis.model.dto.*;
import cl.praxis.model.dao.*;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public LoginController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Crea una instancia del DAO para interactuar con la base de datos.
	    UsuariosDAO aDAO = new UsuariosDAOImpl();
	    
		UsuariosDTO usuarios = aDAO.read (request.getParameter("correo"),request.getParameter("password"));
		System.out.println(usuarios);
		 
		if (usuarios != null) {
		        // Usuario encontrado, redirige a la página de inicio.
		        request.setAttribute("usuarios", usuarios);
		        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		    } else {
		        // Usuario no encontrado, redirige a una página de error o muestra un mensaje.
		        request.setAttribute("errorMessage", "Correo o contraseña incorrectos.");
		        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		    }

	}
}
