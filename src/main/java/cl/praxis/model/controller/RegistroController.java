package cl.praxis.model.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.praxis.model.dto.UsuariosDTO;
import cl.praxis.model.dao.*;

@WebServlet("/RegistroController")
public class RegistroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegistroController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Crea una instancia del DAO para interactuar con la base de datos.
		UsuariosDAO cDAO = new UsuariosDAOImpl();
		
		// Obtiene los parámetros del formulario enviado por el cliente.
		String correo = request.getParameter("correo");
		String nick = request.getParameter("nick");
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		String pesoStr = request.getParameter("peso");
		
		  int peso = 0;
		    try {
		        peso = Integer.parseInt(pesoStr);
		    } catch (NumberFormatException e) {
		        System.out.println("Error al convertir el peso a entero: " + e.getMessage());
		    }
		
		// Crea una instancia de la clase DTO para almacenar los datos del usuario.
		Date now = new Date();
		UsuariosDTO usuarios = new UsuariosDTO();
	  
		// Establece los valores del DTO con los datos obtenidos del formulario.
	    usuarios.setCorreo(correo);
	    usuarios.setNick(nick);
	    usuarios.setNombre(nombre);
	    usuarios.setPassword(password);
	    usuarios.setPeso(peso);
		usuarios.setCreated_at(now);
	    usuarios.setUpdated_at(now);
	    
	    // Usa el DAO para guardar la información del usuario en la base de datos.
	    cDAO.create(usuarios);
				
	    // Redirige la solicitud a la página 'index.jsp'.
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
}