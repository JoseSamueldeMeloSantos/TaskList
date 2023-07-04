package com.tabalho_lp.task_list.interface_swing;

import javax.swing.*;

import com.tabalho_lp.task_list.model.TaskList;

public class NewTaskFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private TaskList taskList;
	private NewTaskPanel newTaskPanel;
	private TaskListPanel taskListPanel;
	private static int largura = 400, altura = 310;
	
	public NewTaskFrame(TaskListPanel taskListPanel, TaskList taskList) {
		
		this.taskList = taskList;
		this.taskListPanel = taskListPanel;
		newTaskPanel = new NewTaskPanel(this, this.taskListPanel, this.taskList);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(largura, altura);
		this.setResizable(false);
		this.setTitle("Add New Task");
		this.setIconImage(new ImageIcon("src/com/tabalho_lp/task_list/imagens/cadernoIcone.png").getImage());
		this.setLocationRelativeTo(null);
		this.add(newTaskPanel);
		
		this.setVisible(true);
	}

	public static int getLargura() {
		return largura;
	}

	public static int getAltura() {
		return altura;
	}
	
}
