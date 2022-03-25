package br.com.tarefas.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.tarefas.controller.TarefaCategoriaController;
import br.com.tarefas.controller.response.TarefaCategoriaResponse;
import br.com.tarefas.model.TarefaCategoria;


@Component
public class TarefaCategoriaModelAssembler implements 
	RepresentationModelAssembler<TarefaCategoria, EntityModel<TarefaCategoriaResponse>> {

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public EntityModel<TarefaCategoriaResponse> toModel(TarefaCategoria categoria) {
		
		TarefaCategoriaResponse categoriaResp = mapper.map(categoria, TarefaCategoriaResponse.class);
		
		EntityModel<TarefaCategoriaResponse> tarefaModel = EntityModel.of(categoriaResp, 
				linkTo(methodOn(TarefaCategoriaController.class).umaCategoria(categoriaResp.getId())).withSelfRel(),
				linkTo(methodOn(TarefaCategoriaController.class).todasCategorias()).withRel("categorias"));

		
		return tarefaModel;
	}

}
