package com.tabalho_lp.task_list.interface_swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.tabalho_lp.task_list.model.*;

public class TaskListPanel extends JPanel implements ActionListener, TaskManager {

	private static final long serialVersionUID = 1L;
	
	private JPanel superiorPanel;
	private JButton homeButton;
	private JLabel tipoTarefaLabel;
	private JComboBox<String> tipoTarefaComboBox;
	private JLabel prioridadeTarefaLabel;
	private JComboBox<String> prioridadeTarefaComboBox;
	private JButton filtarButton;
	private JButton novaTarefaButton;
	private JPanel principalPanel;
	private JScrollPane scrollPane;
	private ProgramFrame programFrame;
	private TaskList taskList;
	
	public TaskListPanel() {
		
		superiorPanel = new JPanel();
		homeButton = new JButton("Home");
		tipoTarefaLabel = new JLabel("Tipo");
		String[] t = {"geral", "Esporte", "Pessoal", "Lazer", "Trabalho"};
		tipoTarefaComboBox = new JComboBox<>(t);
		prioridadeTarefaLabel = new JLabel("Prioridade");
		String[] p = {"geral", "Alta", "Média", "Baixa"};
		prioridadeTarefaComboBox = new JComboBox<>(p);
		filtarButton = new JButton("Filtrar");
		novaTarefaButton = new JButton("Nova Tarefa");
		
		
		this.setPreferredSize(new Dimension(ProgramFrame.getLagura(), ProgramFrame.getAltura()));
		this.setBackground(new Color(204, 204, 204));
		this.setLayout(null);
		
		//configurando superiorPanel e homeButton;
		this.add(homeButton);
		this.add(superiorPanel);
		superiorPanel.setBackground(new Color(0, 102, 102));
		superiorPanel.setBounds(0, 0, 800, 71);
		homeButton.setBounds(310, 23, 170, 23);
		homeButton.setFocusable(false); 
		homeButton.setBackground(new Color(204, 204, 204));
		
		//configurando tipoTarefaLabel e tipoTarefaComboBox
		this.add(tipoTarefaLabel);
		this.add(tipoTarefaComboBox);
		tipoTarefaLabel.setBounds(30, 108, 37, 16);
		tipoTarefaComboBox.setBounds(30, 129, 100, 22);
		
		//configurando prioridadeTarefaLabel e PrioridadeTarefaComboBox
		this.add(prioridadeTarefaLabel);
		this.add(prioridadeTarefaComboBox);
		prioridadeTarefaLabel.setBounds(160, 108, 70, 16);
		prioridadeTarefaComboBox.setBounds(160, 129, 100, 22);
		
		//configurando o filtrarButton e novaTarefaButton
		this.add(filtarButton);
		this.add(novaTarefaButton);
		filtarButton.setFocusable(false);
		novaTarefaButton.setFocusable(false);
		filtarButton.setBounds(290, 129, 80, 22);
		novaTarefaButton.setBounds(640, 129, 110, 22);
		filtarButton.setEnabled(false);
		
		//configurando o principalPanel e scrollPane
		principalPanel = new JPanel();
		principalPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		principalPanel.setPreferredSize(new Dimension(719, 335));
		principalPanel.setBackground(new Color(202, 202, 202));
		scrollPane = new JScrollPane(principalPanel
									, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
									, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30, 161, 719, 335);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 102), 5));
		this.add(scrollPane);
		
		//configurando os actionListeners
		homeButton.addActionListener(this);
		novaTarefaButton.addActionListener(this);
		filtarButton.addActionListener(this);
		prioridadeTarefaComboBox.addActionListener(this);
		tipoTarefaComboBox.addActionListener(this);
	}
	
	public TaskListPanel(ProgramFrame programFrame, TaskList taskList) {
		this();
		this.programFrame = programFrame;
		this.taskList = taskList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == homeButton) {
			programFrame.mudarPagina("homePage");
		} else if (e.getSource() == novaTarefaButton) {
			new NewTaskFrame(this, taskList);
		} else if (e.getSource() == filtarButton) {
			this.filtarTarefas();
		}
	}
	
	@Override
	public void adicionarTarefa(Task tarefa) {
		
		if(taskList.getTarefas().size() != 0) {
			TaskPanel tarefaPanel = new TaskPanel(tarefa, this, taskList);
			principalPanel.add(tarefaPanel);
			
			if(principalPanel.getComponentCount() >= 6) {
				principalPanel.setPreferredSize(new Dimension(principalPanel.getWidth()
															, principalPanel.getHeight() + 55));
			}
			
			principalPanel.revalidate();
		}
		
		if (principalPanel.getComponentCount() > 0) {
			filtarButton.setEnabled(true);
		}
	}

	@Override
	public void removerTarefa(Task tarefa) {
		
		if (taskList.getTarefas().size() != 0) {
			TaskPanel tarefaPanel = new TaskPanel(tarefa, this, taskList);
			
			Component[] taskPanels = principalPanel.getComponents();
			
			for (Component t: taskPanels) {
				TaskPanel task = (TaskPanel) t;
				
				if (tarefaPanel.equals(task)) {
					
					if(principalPanel.getComponentCount() >= 6) {
						principalPanel.setPreferredSize(new Dimension(principalPanel.getWidth()
								, principalPanel.getHeight() - 55));
					}

					principalPanel.remove(task);
					
					repaint();
					principalPanel.revalidate();
				}
			}
			
		}
		
		if (principalPanel.getComponentCount() == 0) {
			filtarButton.setEnabled(false);
		}
	}
	
	@Override
	public void filtarTarefas() {
		
		Component[] taskPanels = principalPanel.getComponents();
		String tipoTask = (String) tipoTarefaComboBox.getSelectedItem();
		String prioridadeTask = (String) prioridadeTarefaComboBox.getSelectedItem();
		
		for (Component t: taskPanels) {
			
			TaskPanel task = (TaskPanel) t;
		
			if (tipoTask.equals("geral") && prioridadeTask.equals("geral")) {
				task.setVisible(true);
			} else if (task.getTipoTarefaString().equals(tipoTask) 
							&& prioridadeTask.equals("geral")) {
				task.setVisible(true);
			} else if (task.getPrioridadeTarefaString().equals(prioridadeTask) 
							&& tipoTask.equals("geral")) {
				task.setVisible(true);
			} else if ((task.getTipoTarefaString().equals(tipoTask) 
							&& task.getPrioridadeTarefaString().equals(prioridadeTask))) {
				task.setVisible(true);
			} else {
				task.setVisible(false);
			}
		}
	}
	
	
	public JPanel getPrincipalPanel() {
		return principalPanel;
	}
}
