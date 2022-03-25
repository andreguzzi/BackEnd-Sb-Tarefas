package br.com.tarefas.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.tarefas.exception.TarefaStatusException;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

	@Mock
	private TarefaRepository repositorio;
	
	@InjectMocks
	private TarefaService service;
	
	@Test
	void naoDeveConcluirTarefaCancelada() {
		
		Integer idExemplo = 1;
		
		Tarefa tarefa = new Tarefa();
		tarefa.setId(idExemplo);
		tarefa.setDescricao("teste 01");
		tarefa.setStatus(TarefaStatus.CANCELADA);
		
		Mockito.when(repositorio.findById(1)).thenReturn(Optional.of(tarefa));
		
		Assertions.assertThrows(TarefaStatusException.class,() -> service.concluirTarefaPorId(idExemplo));
	}
}
