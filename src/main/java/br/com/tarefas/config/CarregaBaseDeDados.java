package br.com.tarefas.config;

import java.time.LocalDate;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.tarefas.model.ERole;
import br.com.tarefas.model.Role;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaCategoria;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.model.Usuario;
import br.com.tarefas.repository.RoleRepository;
import br.com.tarefas.repository.TarefaCategoriaRepository;
import br.com.tarefas.repository.TarefaRepository;
import br.com.tarefas.repository.UsuarioRepository;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TarefaCategoriaRepository categoriaRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Bean
	CommandLineRunner executar() {
		return args -> {
			
			Role roleAdmin = new Role(ERole.ROLE_ADMIN);
			roleAdmin = roleRepository.save(roleAdmin);
			
			Role roleUser = new Role(ERole.ROLE_USER);
			roleUser = roleRepository.save(roleUser);
			
			
			Usuario usuario = new Usuario();
			usuario.setNome("Admin");
			usuario.setSenha(encoder.encode("123456"));
			usuario.setRoles(Set.of(roleAdmin, roleUser));
			
			usuarioRepository.save(usuario);
			
			TarefaCategoria categoria = new TarefaCategoria();
			categoria.setNome("Estudos");
			categoriaRepository.save(categoria);
			
			Tarefa tarefa = new Tarefa();
			tarefa.setDescricao("Aprender Spring Boot");
			tarefa.setDataEntrega(LocalDate.now().plusDays(1));
			tarefa.setStatus(TarefaStatus.ABERTO);
			tarefa.setVisivel(true);
			tarefa.setCategoria(categoria);
			tarefa.setUsuario(usuario);
			tarefaRepository.save(tarefa);
			
			Tarefa tarefa2 = new Tarefa();
			tarefa2.setDescricao("Aprender Spring Data JPA");
			tarefa2.setDataEntrega(LocalDate.now().plusDays(1));
			tarefa2.setStatus(TarefaStatus.ABERTO);
			tarefa2.setVisivel(true);
			tarefa2.setCategoria(categoria);
			tarefa2.setUsuario(usuario);
			tarefaRepository.save(tarefa2);
		};
	}

}
