package com.jogos;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Janela extends JFrame{
	private JPanel tela;
	
	public Janela(){
		tela = new JPanel(){
			public void paintComponent(Graphics g){
				
			}
		};
		
		super.getContentPane().add(tela);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Janela();
	}
	
}
