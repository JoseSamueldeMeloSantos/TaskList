package com.tabalho_lp.task_list.interface_swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.tabalho_lp.task_list.model.*;

public class NewTaskPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel tituloPanel;
	private JLabel tituloLabel;
	private JLabel nomeTarefaLabel;
	private JLabel tipoTarefaLabel;
	private JLabel prioridadeTarefaLabel;
	private JButton adicionarTarefaButton;
	private JTextField nomeTarefaTextField;
	private JComboBox<String> tipoTarefaComboBox;
	private JComboBox<String> prioridadeTarefaComboBox;
	private NewTaskFrame newTaskFrame;
	private TaskListPanel taskListPanel;
	private TaskList taskList;
	
	public NewTaskPanel() {
		
		tituloPanel = new JPanel();
		tituloLabel = new JLabel("Task Menu");
		nomeTarefaLabel = new JLabel("Nome da Tarefa");
		tipoTarefaLabel = new JLabel("Tipo");
		prioridadeTarefaLabel = new JLabel("Prioridade");
		adicionarTarefaButton = new JButton("Adicionar");
		nomeTarefaTextField = new JTextField();
		String[] t = {"Esporte", "Pessoal", "Lazer", "Trabalho"};
		tipoTarefaComboBox = new JComboBox<>(t);
		String[] p = {"Alta", "Média", "Baixa"};
		prioridadeTarefaComboBox = new JComboBox<>(p);
		
		//configurando o panel
		this.setPreferredSize(new Dimension(NewTaskFrame.getLargura(), NewTaskFrame.getAltura()));
		this.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 102), 5));
		this.setBackground(new Color(204, 204, 204));
		this.setLayout(null);
		
		//configurando o tituloPanel e o tituloLabel
		this.add(tituloPanel);
		tituloPanel.add(tituloLabel);
		tituloPanel.setBounds(77, 0, 229, 46);
		tituloPanel.setBackground(new Color(0, 102, 102));
		tituloLabel.setFont(new Font("Segoe Print", Font.BOLD, 22));
		tituloLabel.setBounds(10, 0, 119, 40);
		tituloLabel.setForeground(Color.BLACK);
		
		//configurando nomeTarefaLabel e nomeTarefaTextFild
		this.add(nomeTarefaLabel);
		this.add(nomeTarefaTextField);
		nomeTarefaLabel.setBounds(60, 80,90, 16);
		nomeTarefaTextField.setBounds(60, 101,260, 35);
		nomeTarefaTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		//configurando tipoTarefaLabel e o TipoTarefaComboBox
		this.add(tipoTarefaLabel);
		this.add(tipoTarefaComboBox);
		tipoTarefaLabel.setBounds(60, 141, 60, 18);
		tipoTarefaComboBox.setBounds(60, 164,72, 22);
		
		//configurando prioridadeTarefaButton e prioridadeTarefaComboBox
		this.add(prioridadeTarefaLabel);
		this.add(prioridadeTarefaComboBox);
		prioridadeTarefaLabel.setBounds(247,141, 60, 18);
		prioridadeTarefaComboBox.setBounds(247, 164, 72, 22);
		
		//configurando o adicionarTarefaButton
		this.add(adicionarTarefaButton);
		adicionarTarefaButton.setBounds(147, 200,90, 23);
		adicionarTarefaButton.setFocusable(false);      
		
		//configurando os actionListeners
		adicionarTarefaButton.addActionListener(this);
	}
	
	public NewTaskPanel(NewTaskFrame newTaskFrame
						, TaskListPanel taskListPanel, TaskList taskList) {
		this();
		this.newTaskFrame = newTaskFrame;
		this.taskListPanel = taskListPanel;
		this.taskList = taskList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == adicionarTarefaButton) {

			try {
				
				String nomeTarefa = nomeTarefaTextField.getText();
				String prioridadeTarefa = (String) prioridadeTarefaComboBox.getSelectedItem();
				String tipoTarefa =  (String) tipoTarefaComboBox.getSelectedItem();
				
				Task tarefa = new Task(nomeTarefa, prioridadeTarefa, tipoTarefa);
				
				this.checharTarefa(tarefa);
				
				newTaskFrame.setVisible(false);
				taskList.adicionarTarefa(tarefa);
				taskListPanel.adicionarTarefa(tarefa);
			} catch (InvalidTaskNameException e1) {
				JOptionPane.showMessageDialog(null, "Nome da tarefa Invalido"
											 	,"Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (TaskAlreadyExistsException e1) {
				JOptionPane.showMessageDialog(null, "Essa tarefa já existe"
												, "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	
	public void checharTarefa(Task tarefa) 
			throws InvalidTaskNameException, TaskAlreadyExistsException {
		
		if (nomeTarefaTextField.getText().strip().length() == 0) {
			throw new InvalidTaskNameException();
		} else {
			Component[] taskPanels = taskListPanel.getPrincipalPanel().getComponents();
			for (Component t: taskPanels) {
				JPanel task = (JPanel) t;
				
				if(task.equals(new TaskPanel(tarefa))) {
					throw new TaskAlreadyExistsException();
				}
			}
		}
	}
}
