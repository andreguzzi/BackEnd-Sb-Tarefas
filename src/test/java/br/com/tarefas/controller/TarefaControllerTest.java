package br.com.tarefas.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;

import br.com.tarefas.controller.response.TarefaResponse;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaCategoria;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.model.Usuario;
import br.com.tarefas.services.TarefaService;

@SpringBootTest
public class TarefaControllerTest {

	@Autowired
	private TarefaController controller;
	
	@MockBean
	private TarefaService service;
	

	@Test
	void validaTarefaResponse() {
		int tarefaId = 999;
		
		Tarefa tarefa = new Tarefa();
		tarefa.setId(tarefaId);
		tarefa.setStatus(TarefaStatus.ABERTO);
		
		Usuario usuario = new Usuario();
		usuario.setId(1);
		tarefa.setUsuario(usuario );
		
		TarefaCategoria categoria = new TarefaCategoria();
		categoria.setId(2);
		tarefa.setCategoria(categoria );
		
		Mockito.when(service.getTarefaPorId(tarefaId)).thenReturn(tarefa);
		
		EntityModel<TarefaResponse> tarefaModel = controller.umaTarefa(tarefaId);
		TarefaResponse tarefaResp = tarefaModel.getContent();
		
		Assertions.assertEquals(tarefaId, tarefaResp.getId());
		Assertions.assertEquals(2, tarefaResp.getCategoriaId());
		Assertions.assertEquals(1, tarefaResp.getUsuarioId());
		Assertions.assertEquals(TarefaStatus.ABERTO.name(), tarefaResp.getStatus());
	}
	
}
