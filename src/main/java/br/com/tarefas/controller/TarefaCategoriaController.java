package br.com.tarefas.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.controller.assembler.TarefaCategoriaModelAssembler;
import br.com.tarefas.controller.request.TarefaCategoriaRequest;
import br.com.tarefas.controller.response.TarefaCategoriaResponse;
import br.com.tarefas.model.TarefaCategoria;
import br.com.tarefas.services.TarefaCategoriaService;


@RestController
@RequestMapping("/categoria")
public class TarefaCategoriaController {

	@Autowired
	private TarefaCategoriaService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private TarefaCategoriaModelAssembler assembler;
	
	@GetMapping
	public CollectionModel<EntityModel<TarefaCategoriaResponse>> todasCategorias() {
		List<TarefaCategoria> categorias = service.getTodasCategorias();
		List<EntityModel<TarefaCategoriaResponse>> categoriasModel = categorias
			.stream()
			.map(assembler::toModel)
			.collect(Collectors.toList());
		
		return CollectionModel.of(categoriasModel,
				linkTo(methodOn(TarefaCategoriaController.class).todasCategorias()).withSelfRel());
	}
	
	@GetMapping("/{id}")
	public EntityModel<TarefaCategoriaResponse> umaCategoria(@PathVariable Integer id) {
		return assembler.toModel(service.getCategoriaPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<EntityModel<TarefaCategoriaResponse>> salvarCategoria(@RequestBody TarefaCategoriaRequest categoriaReq) {
		TarefaCategoria categoria = mapper.map(categoriaReq, TarefaCategoria.class);
		EntityModel<TarefaCategoriaResponse> categoriaModel = assembler.toModel(service.salvar(categoria));
		return ResponseEntity
				.created(categoriaModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(categoriaModel);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirTarefa(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
}
