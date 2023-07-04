package com.tabalho_lp.task_list.interface_swing;

import java.awt.CardLayout;

import javax.swing.*;

import com.tabalho_lp.task_list.model.TaskList;

public class ProgramFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private TaskList taskList;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private HomePanel homePanel;
	private TaskListPanel taskListPanel;
	private static int lagura = 804, altura = 553;
	
	public ProgramFrame() {
		
		taskList = new TaskList();
		cardLayout = new CardLayout();
		mainPanel = new JPanel();
		homePanel = new HomePanel(this);
		taskListPanel = new TaskListPanel(this, taskList);

		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(lagura, altura);
		this.setIconImage(new ImageIcon("src/com/tabalho_lp/task_list/images/cadernoIcone.png").getImage());
		this.setTitle("Task List");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		//configurando o main panel
		this.add(mainPanel);
		mainPanel.setLayout(cardLayout);
		mainPanel.add(homePanel, "homePage");
		mainPanel.add(taskListPanel, "taskListPage");
		cardLayout.show(mainPanel, "homePage");
		
		this.setVisible(true);
		
		this.addWindowListener(new FrameListener(taskList, taskListPanel));
	}

	public static int getLagura() {
		return lagura;
	}

	public static int getAltura() {
		return altura;
	}
	
	public void mudarPagina(String chavePagina) {
		cardLayout.show(mainPanel, chavePagina);
	}

}
