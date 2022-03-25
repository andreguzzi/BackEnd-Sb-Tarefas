package br.com.tarefas.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tarefas.model.TarefaCategoria;
import br.com.tarefas.repository.TarefaCategoriaRepository;

@Service
public class TarefaCategoriaService {

	@Autowired
	private TarefaCategoriaRepository repositorio;
	
	public List<TarefaCategoria> getTodasCategorias() {
		return repositorio.findAll();
	}

	public TarefaCategoria getCategoriaPorId(Integer id) {
		return repositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public TarefaCategoria salvar(TarefaCategoria categoria) {
		return repositorio.save(categoria);
	}

	public void deleteById(Integer id) {
		repositorio.deleteById(id);
	}

}
