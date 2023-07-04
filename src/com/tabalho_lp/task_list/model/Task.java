package com.tabalho_lp.task_list.model;

import java.util.Objects;

public class Task {

	private String nomeTarefa;
	private String prioridadeTarefa;
	private String tipoTarefa;
	
	
	public Task(String nomeTarefa, String prioridadeTarefa, String tipoTarefa) {
		this.nomeTarefa = nomeTarefa;
		this.prioridadeTarefa = prioridadeTarefa;
		this.tipoTarefa = tipoTarefa;
	}
	
	public String getNomeTarefa() {
		return nomeTarefa;
	}
	
	public String getPrioridadeTarefa() {
		return prioridadeTarefa;
	}
	
	public String getTipoTarefa() {
		return tipoTarefa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomeTarefa, prioridadeTarefa, tipoTarefa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(nomeTarefa, other.nomeTarefa) && Objects.equals(prioridadeTarefa, other.prioridadeTarefa)
				&& Objects.equals(tipoTarefa, other.tipoTarefa);
	}
	
	
}
