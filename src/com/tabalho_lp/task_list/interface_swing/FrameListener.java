package com.tabalho_lp.task_list.interface_swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.tabalho_lp.task_list.model.Task;
import com.tabalho_lp.task_list.model.TaskList;
import com.tabalho_lp.task_list.model.TaskRecorder;

public class FrameListener extends WindowAdapter {
    
	private TaskRecorder gravadorDeTarefas = new TaskRecorder();
	private TaskListPanel taskListPanel;
	private TaskList taskList;
	
	public FrameListener(TaskList taskList, TaskListPanel taskListPanel) {
		this.taskList = taskList;
		this.taskListPanel = taskListPanel;
	}
	
	@Override
    public void windowClosing(WindowEvent e) {
        
        try { 
        	gravadorDeTarefas.gravaTask(taskList.getTarefas());        	
        } catch(IOException error) {
        	JOptionPane.showMessageDialog(null, "Não foi possivel gravar as tarefas"
        									, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	@Override
	public void windowOpened(WindowEvent e) {
		
		try {
			taskList.setTarefas(gravadorDeTarefas.recuperaTask());							
		} catch(IOException error) {
			JOptionPane.showMessageDialog(null, "Não foi possivel carregar as terefas"
											, "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		for (Task tarefa: taskList.getTarefas()) {
			taskListPanel.adicionarTarefa(tarefa);
		}
	}
}
