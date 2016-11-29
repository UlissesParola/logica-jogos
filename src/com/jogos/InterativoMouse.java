package com.jogos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterativoMouse extends JFrame {
	private JPanel tela;
	private int px = 0;
	private int py = 0;
	private Point mouseClick = new Point();
	private boolean jogando = true;
	private final int FPS = 1000 / 20;

	public InterativoMouse() {
		
		tela = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, getWidth(), getHeight());

				int x = px;
				int y = py;

				g.setColor(Color.BLUE);
				g.fillRect(x, y, 40, 40);
				g.drawString("Agora estou em x: " + x + ", y: " + y, 5, 10);
			}
		};

		tela.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// Botão clicado
				mouseClick = e.getPoint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// Botão pressionado
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// Botão liberado
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// Mouse entrou na tela
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Mouse saiu da tela
			}

		});

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
		px = mouseClick.x;
		py = mouseClick.y;

	}
}
