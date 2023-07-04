package com.tabalho_lp.task_list.interface_swing;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import javax.swing.*;

public class HomePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JButton tarefasButton;
	private JButton minhaApresentacaoButton;
	private JButton sairButton;
	private JLabel textoLabel;
	private JLabel imagemLabel;
	private JPanel centralPanel;
	private ProgramFrame programFrame;
	
	public HomePanel() {

		centralPanel = new JPanel();
		imagemLabel = new JLabel();
		textoLabel = new JLabel("Task List");
		tarefasButton = new JButton("Tarefas");
		minhaApresentacaoButton = new JButton("Minha apresentação");
		sairButton = new JButton("Sair");
		
		
		this.setPreferredSize(new Dimension(ProgramFrame.getLagura(), ProgramFrame.getAltura()));
		this.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 102), 5));
		this.setBackground(new Color(204,204,204));
		this.setLayout(null);
		
		//configurando o central panel
		this.add(centralPanel);
		centralPanel.setBounds(225, 0, 336, 553);
		centralPanel.setBackground(new Color(0,102,102));
		centralPanel.setLayout(null);
		
		//configurando o textoLabel e o imagemLabel
		centralPanel.add(textoLabel);		
		centralPanel.add(imagemLabel);
		imagemLabel.setIcon(new ImageIcon("src/com/tabalho_lp/task_list/images/cadernoImagem.png"));
		textoLabel.setFont(new Font("Segoe Print", Font.BOLD, 40));
		textoLabel.setForeground(Color.BLACK);
		imagemLabel.setBounds(130, 130, 100, 100);
		textoLabel.setBounds(80, 30, 1000, 100);
		
		//configurando os buttons
		centralPanel.add(tarefasButton);
		centralPanel.add(minhaApresentacaoButton);
		centralPanel.add(sairButton);
		
		minhaApresentacaoButton.setSize(75, 23);
		sairButton.setSize(75, 23);
		
		tarefasButton.setBounds(94, 255, 160, 23);
		minhaApresentacaoButton.setBounds(94, 288, 160, 23);
		sairButton.setBounds(94, 321, 160, 23);        
		
		tarefasButton.setFocusable(false);
		minhaApresentacaoButton.setFocusable(false);
		sairButton.setFocusable(false);
		
		tarefasButton.setBackground(new Color(204, 204, 204));
		minhaApresentacaoButton.setBackground(new Color(204, 204, 204));
		sairButton.setBackground(new Color(204, 204, 204));
		
		//adicionando os eventos
		tarefasButton.addActionListener(this);
		minhaApresentacaoButton.addActionListener(this);
		sairButton.addActionListener(this);
	}
	
	public HomePanel(ProgramFrame programFrame) {
		this();
		this.programFrame = programFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == tarefasButton) {
			programFrame.mudarPagina("taskListPage");
		} else if (e.getSource() == minhaApresentacaoButton) {
			
			Desktop d = Desktop.getDesktop();
			
			try {
			    d.browse(new URI("https://www.youtube.com/watch?v=gPntbTRAG1U"));
			    JOptionPane.showMessageDialog(null, "A apresentação foi aberta no navegador"
			    								, "", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException | URISyntaxException e2) {
				 JOptionPane.showMessageDialog(null, "Não foi possivel abrir o video"
							, "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if (e.getSource() == sairButton) {
			System.exit(1);
		}
	}
}
