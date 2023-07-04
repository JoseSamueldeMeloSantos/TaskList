package com.tabalho_lp.task_list.model;

public interface TaskManager {

	void adicionarTarefa(Task tarefa);
	
	void removerTarefa(Task tarefa);
	
	default void filtarTarefas() {}
}
