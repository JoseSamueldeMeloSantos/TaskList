package com.tabalho_lp.task_list.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataRecorder {
	
	String caminhoDoArquivo;
	
	public DataRecorder(String nomeArquivo) {
		this.caminhoDoArquivo = "src/" + nomeArquivo;
	}
	
	public List<String> recuperaTextoDeArquivo() 
			throws IOException {
		
		BufferedReader leitor = null;
		List<String> textoLido = new ArrayList<>();
		
		try {
			leitor = new BufferedReader(new FileReader(caminhoDoArquivo));
			String texto = null;
			
			do {
				texto = leitor.readLine();
				
				if (texto != null) {
					textoLido.add(texto);
				}	
			} while (texto != null);
			
		} finally {
			leitor.close();
		}
		
		return textoLido;
	}
	

	public void gravaTextoEmArquivo(List<String> texto) 
			throws IOException {
		
		BufferedWriter gravador = null;
		
		try {
			gravador = new BufferedWriter(new FileWriter(caminhoDoArquivo));
			
			for (String s: texto) {
				gravador.write(s + "\n");
			}
		} finally {
			if (gravador != null) {
				gravador.close();
			}
		}
	}
}
