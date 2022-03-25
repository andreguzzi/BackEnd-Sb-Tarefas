package br.com.tarefas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tarefas")
@NamedQuery(name = "Tarefa.tarefasPorCategoria", query = ("select t from Tarefa t inner join t.categoria c where c.nome = ?1"))
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "{tarefa.descricao.not-blank}")
	@Size(min = 5, max = 150, message = "{tarefa.descricao.size}")
	@Column(name = "ds_tarefa", nullable = false, length= 150)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TarefaStatus status = TarefaStatus.ABERTO;
	
	@FutureOrPresent(message = "{tarefa.descricao.future-or-present}")
	private LocalDate dataEntrega;
	
	private boolean visivel;
	
	@ManyToOne //aonde estou e para onde vou
	@JoinColumn(nullable = false) //uma categoria nao pode ser nulla quando criar uma tarefa
	private TarefaCategoria categoria;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TarefaStatus getStatus() {
		return status;
	}

	public void setStatus(TarefaStatus status) {
		this.status = status;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public TarefaCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(TarefaCategoria categoria) {
		this.categoria = categoria;
	}

		public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
