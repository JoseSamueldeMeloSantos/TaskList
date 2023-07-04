package com.tabalho_lp.task_list.model;

import java.util.LinkedList;
import java.util.List;

public class TaskList implements TaskManager {

	private List<Task> tarefas = new LinkedList<>();
	
	@Override
	public void adicionarTarefa(Task tarefa) {
		tarefas.add(tarefa);
	}

	@Override
	public void removerTarefa(Task tarefa) {
		tarefas.remove(tarefa);
	}

	public List<Task> getTarefas() {
		return tarefas;
	}
	
	public void setTarefas(List<Task> tarefas) {
		this.tarefas = tarefas;
	}
	
	
}
