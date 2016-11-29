package com.jogos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterativoTeclado extends JFrame {
	private JPanel tela;
	private int px;
	private int py;
	private boolean jogando = true;
	private boolean[] controleTecla = new boolean[4];

	private final int FPS = 1000 / 20;

	public InterativoTeclado() {
		super.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// tecla apertada
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// tecla pressionada
				setaTecla(e.getKeyCode(), true);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// tecla liberada
				setaTecla(e.getKeyCode(), false);
			}
		});

		tela = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, getWidth(), getHeight());

				int x = tela.getWidth() / 2 - 20 + px;
				int y = tela.getHeight() / 2 - 20 + py;

				g.setColor(Color.BLUE);
				g.fillRect(x, y, 40, 40);
				g.drawString("Agora estou em x: " + x + ", y: " + y, 5, 10);
			}
		};

		getContentPane().add(tela);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
	}

	public void inicia() {
		long prxAtualizacao = 0;

		while (jogando) {
			if (System.currentTimeMillis() >= prxAtualizacao) {
				atualizaJogo();
				tela.repaint();
				prxAtualizacao = System.currentTimeMillis() + FPS;
			}
		}
	}

	private void atualizaJogo() {
		if (controleTecla[0]) {
			py -= 5;}
		
		if (controleTecla[1]) {
			py += 5;}
		
		if (controleTecla[2]) {
			px -= 5;}
		
		if (controleTecla[3]) {
			px += 5;}
	}

	private void setaTecla(int tecla, boolean pressionada) {
		switch (tecla) {
		case KeyEvent.VK_ESCAPE:
			jogando = false;
			dispose();
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			controleTecla[0] = pressionada;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			controleTecla[1] = pressionada;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			controleTecla[2] = pressionada;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			controleTecla[3] = pressionada;
			break;
		}
	}
}
