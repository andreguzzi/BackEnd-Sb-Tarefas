package br.com.tarefas.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tarefas.exception.TarefaStatusException;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaStatus;

@SpringBootTest
public class TarefaServiceIntegrationTests {

	@Autowired
	private TarefaService service;
	
	@Test
	void deveIniciarTarefa() {
		Tarefa tarefa = service.iniciarTarefaPorId(3);
		Assertions.assertEquals(TarefaStatus.EM_ANDAMENTO, tarefa.getStatus());
	}
	
	@Test
	void naodeveIniciarTarefaConcluida() {
		Tarefa tarefa = service.getTarefaPorId(3);
		tarefa.setStatus(TarefaStatus.CONCLUIDA);
		service.salvarTarefa(tarefa);
		
		Assertions.assertThrows(TarefaStatusException.class,() -> service.iniciarTarefaPorId(3));
	}
}
