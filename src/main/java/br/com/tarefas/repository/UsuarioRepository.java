package br.com.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarefas.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{
	
	Optional<Usuario> findByNome(String nome);
	

}
