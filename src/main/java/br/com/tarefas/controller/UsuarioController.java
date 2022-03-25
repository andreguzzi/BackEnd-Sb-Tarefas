package br.com.tarefas.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.controller.assembler.UsuarioModelAssembler;
import br.com.tarefas.controller.request.UsuarioRequest;
import br.com.tarefas.controller.response.UsuarioResponse;
import br.com.tarefas.model.Usuario;
import br.com.tarefas.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioModelAssembler assembler;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/{id}")
	public EntityModel<UsuarioResponse> umUsuario(@PathVariable Integer id) {
		Usuario usuario = usuarioService.getUsuarioPorId(id);
		EntityModel<UsuarioResponse> usuarioResponse = assembler.toModel(usuario);
		return usuarioResponse;
	}

	@PostMapping
	public ResponseEntity<EntityModel<UsuarioResponse>> salvarUsuario(@Valid @RequestBody UsuarioRequest usuarioReq) {

		Usuario usuario = mapper.map(usuarioReq, Usuario.class);
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		EntityModel<UsuarioResponse> usuarioModel = assembler.toModel(usuarioSalvo);

		return ResponseEntity.created(usuarioModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(usuarioModel);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EntityModel<UsuarioResponse>> atualizarUsuario(@PathVariable Integer id,
			@Valid @RequestBody UsuarioRequest usuarioReq) {

		Usuario usuario = mapper.map(usuarioReq, Usuario.class);
		Usuario usuarioSalvo = usuarioService.atualizar(id, usuario);
		EntityModel<UsuarioResponse> usuarioModel = assembler.toModel(usuarioSalvo);

		return ResponseEntity.ok().body(usuarioModel);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirUsuario(@PathVariable Integer id) {
		usuarioService.deleteById(id);
	}

}
