package com.cristianruizblog;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cristianruizblog.Entity.Usuario;
import com.cristianruizblog.repository.UsuarioRepositorio;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioRepositorio repositorio;
	
	@GetMapping
	public String index(Model modelo, Usuario usuario) {
		modelo.addAttribute("usuario",new Usuario());
		modelo.addAttribute("usuarios",repositorio.findAll());//SELECT * FROM USUARIOS;
		
		return "index";
	}
	
	@PostMapping("/crearUsuario")
	public String crearUsuario(Model modelo, Usuario usuario) {
		repositorio.save(usuario);//INSERT INTO Usuario (...) value (...);
		modelo.addAttribute("usuario",new Usuario());
		modelo.addAttribute("usuarios",repositorio.findAll());//SELECT * FROM USUARIOS;
		return "index";
	}
	
	@GetMapping("/editarUsuario/{id}")
	public String editarUsuarioForm(Model modelo, @PathVariable(name="id") Long id) {
		Usuario usuarioParaEditar = repositorio.findById(id).get();
		modelo.addAttribute("usuario",usuarioParaEditar);
		modelo.addAttribute("usuarios",repositorio.findAll());//SELECT * FROM USUARIOS;
		return "index";
	}
	
	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(Model modelo, @PathVariable(name="id") Long id) {
		Usuario usuarioParaEliminar = repositorio.findById(id).get();
		repositorio.delete(usuarioParaEliminar);
		modelo.addAttribute("usuario",new Usuario());
		modelo.addAttribute("usuarios",repositorio.findAll());//SELECT * FROM USUARIOS;
		return "index";
	}
}
