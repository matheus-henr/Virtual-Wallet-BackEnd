package br.com.virtual_wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.virtual_wallet.dto.UsuarioDTO;
import br.com.virtual_wallet.model.Usuario;
import br.com.virtual_wallet.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("${origem-permitida}")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findUsuario(@PathVariable Integer id){
		Usuario usuario = service.findOne(id);
		return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody UsuarioDTO dto){
		service.save(dto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/senha")
	public ResponseEntity<Void> editarSenha(@RequestBody String senha){
		service.update(senha);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
