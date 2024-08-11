package cl.praxis.model.dao;

import java.util.List;

import cl.praxis.model.dto.UsuariosDTO;

/**
 * Define las operaciones básicas de CRUD 
 * (crear, leer, actualizar, eliminar)
 */

public interface UsuariosDAO {
	
	void create(UsuariosDTO c);
	UsuariosDTO read(String correo, String pass);
	boolean verificaEmail(String correo);
	List<UsuariosDTO> read();
	void update(UsuariosDTO u);
	String delete(int id);
}
