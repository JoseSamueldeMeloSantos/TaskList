package com.tabalho_lp.task_list.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TaskRecorder {
	
	private String arquivoTasks = "tasks.txt";
	private DataRecorder gravador;
	
	public TaskRecorder() {
		this.gravador = new DataRecorder(arquivoTasks);
	}
	
	public void gravaTask(List<Task> tarefas)
		throws IOException {
		
		List<String> textoAGravar = new LinkedList<>();
		
		for (Task tarefa: tarefas) {
			String linha = tarefa.getNomeTarefa() + "§§§" + tarefa.getPrioridadeTarefa() + "§§§" + tarefa.getTipoTarefa();
			textoAGravar.add(linha);
		}
		
		this.gravador.gravaTextoEmArquivo(textoAGravar);
	}
	
	public List<Task> recuperaTask() throws IOException {
		
		
		List<String> dadosTasks = this.gravador.recuperaTextoDeArquivo();
		List<Task> listaTasks = new LinkedList<>();
		
		for (String t: dadosTasks) {
			String[] dados = t.split("§§§");
			Task tarefa = new Task(dados[0], dados[1] , dados[2]);
			listaTasks.add(tarefa);
		}
		
		return listaTasks;
	}
	
	
}
