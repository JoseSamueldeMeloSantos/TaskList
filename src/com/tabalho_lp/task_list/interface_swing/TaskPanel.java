package com.tabalho_lp.task_list.interface_swing;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

import javax.swing.*;

import com.tabalho_lp.task_list.model.Task;
import com.tabalho_lp.task_list.model.TaskList;

public class TaskPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JRadioButton lixeiraRadioButton;
	private JLabel nomeTarefaLabel;
	private JLabel tipoTarefaLabel;
	private JLabel prioridadeTarefaLabel;
	private TaskListPanel taskListPanel;
	private Task tarefa;
	private TaskList taskList;
	
	public TaskPanel(Task tarefa) {
		
		this.tarefa = tarefa;
		lixeiraRadioButton = new JRadioButton();
		this.nomeTarefaLabel = new JLabel(tarefa.getNomeTarefa());
		this.tipoTarefaLabel = new JLabel("Tipo: " + tarefa.getTipoTarefa());
		this.prioridadeTarefaLabel = new JLabel("Prioridade: " + tarefa.getPrioridadeTarefa());
		
		this.setPreferredSize(new Dimension(683, 55));
		this.setBackground(new Color(0xB34200));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.setLayout(null);
		
		//configurando nomeTarefaLabel
		nomeTarefaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nomeTarefaLabel.setForeground(Color.BLACK);
		nomeTarefaLabel.setBounds(10, 3, 615, 25);
		this.add(nomeTarefaLabel);
		
		//configurando a  prioridadeTarefaLabel
		prioridadeTarefaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		prioridadeTarefaLabel.setForeground(Color.BLACK);
		prioridadeTarefaLabel.setBounds(100, 28, 107, 20);
		this.add(prioridadeTarefaLabel);
		
		//configurando o tipoTarefaLabel
		tipoTarefaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tipoTarefaLabel.setForeground(Color.BLACK);
		tipoTarefaLabel.setBounds(10, 28, 107, 20);
		this.add(tipoTarefaLabel);
		
		//configurando o radioButton
		lixeiraRadioButton.setBounds(640, 12, 32, 32);
		lixeiraRadioButton.setIcon(new ImageIcon("src/com/tabalho_lp/task_list/images/lixeira.png"));
		lixeiraRadioButton.setBackground(new Color(0xB34200));
		this.add(lixeiraRadioButton);
	
		lixeiraRadioButton.addActionListener(this);
	}
	
	public TaskPanel(Task tarefa, TaskListPanel taskListPanel, TaskList taskList) {
		this(tarefa);
		this.taskListPanel = taskListPanel;
		this.taskList  = taskList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == lixeiraRadioButton) {
			taskListPanel.removerTarefa(tarefa);
			taskList.removerTarefa(tarefa);
		}
	}
	
	public String getTipoTarefaString() {
		return tipoTarefaLabel.getText()
				.substring(tipoTarefaLabel.getText().indexOf(" ") + 1);
	}

	public String getPrioridadeTarefaString() {
		return prioridadeTarefaLabel.getText()
				.substring(prioridadeTarefaLabel.getText().indexOf(" ") + 1);
	}
	
	public String getNomeTarefaString() {
		return nomeTarefaLabel.getText();
	}

	@Override
	public int hashCode() {
		return Objects.hash(lixeiraRadioButton, nomeTarefaLabel, prioridadeTarefaLabel, tipoTarefaLabel);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof TaskPanel) {
			
			TaskPanel outro = (TaskPanel) obj;
			
			boolean outroNomeTarefa = outro.getNomeTarefaString().equals(this.getNomeTarefaString());
			boolean outroTipoTarefa = outro.getTipoTarefaString().equals(this.getTipoTarefaString());
			boolean outroPrioridadeTarefa = outro.getPrioridadeTarefaString().equals(this.getPrioridadeTarefaString());
			
			return outroNomeTarefa && outroTipoTarefa && outroPrioridadeTarefa;
		} else {
			return false;
		}
	}
}
