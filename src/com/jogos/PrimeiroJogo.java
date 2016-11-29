package com.jogos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrimeiroJogo extends JFrame {
	private final int FPS = 1000 / 20;

	class Elemento {
		public int x, y, width, height;
		public float speed;

		public Elemento(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
	}

	private JPanel tela;
	private boolean jogando = true;
	private boolean fimDeJogo = false;

	private Elemento tiro;
	private Elemento jogador;
	private Elemento[] blocos;

	private int pontos;
	private int tamanhoPadrao = 50;
	private int linhaLimite = 350;
	private java.util.Random r = new java.util.Random();

	private boolean[] controleTecla = new boolean[4];

	public PrimeiroJogo() {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Stub de método gerado automaticamente

			}

			@Override
			public void keyReleased(KeyEvent e) {
				setaTecla(e.getKeyCode(), false);

			}


			@Override
			public void keyPressed(KeyEvent e) {
				setaTecla(e.getKeyCode(), true);

			}
		});

		tiro = new Elemento(0, 0, 1, 0);
		jogador = new Elemento(0, 0, tamanhoPadrao, tamanhoPadrao);
		jogador.speed = 5;

		blocos = new Elemento[5];
		for (int i = 0; i < blocos.length; i++) {
			int espaco = i * tamanhoPadrao + 10 * (i + 1);
			blocos[i] = new Elemento(espaco, 0, tamanhoPadrao, tamanhoPadrao);
			blocos[i].speed = 1;
		}

		tela = new JPanel() {
			private static final long seriaVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, tela.getWidth(), tela.getHeight());

				g.setColor(Color.RED);
				g.fillRect(tiro.x, tiro.y, tiro.width, tela.getHeight());

				g.setColor(Color.GREEN);
				g.fillRect(jogador.x, jogador.y, jogador.width, jogador.height);
				;

				g.setColor(Color.BLUE);
				for (Elemento bloco : blocos) {
					g.fillRect(bloco.x, bloco.y, bloco.width, bloco.height);
				}

				g.setColor(Color.GRAY);
				g.drawLine(0, linhaLimite, tela.getWidth(), linhaLimite);

				g.drawString("Pontos: " + pontos, 0, 10);

			}
		};

		getContentPane().add(tela);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
		setResizable(false);

		jogador.x = tela.getWidth() / 2 - jogador.x / 2;
		jogador.y = tela.getHeight() - jogador.height;
		tiro.height = tela.getHeight() - jogador.height;

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
		if(fimDeJogo){
			return;
		}
		
		if(controleTecla[2]){
			jogador.x -= jogador.speed;
		} else if (controleTecla[3]){
			jogador.x += jogador.speed;
		}
		
		if(jogador.x < 0){
			jogador.x = tela.getWidth() - jogador.width;
		}
		
		if(jogador.x + jogador.width > tela.getWidth()){
			jogador.x = 0;
		}
		
		tiro.y = 0;
		tiro.x = jogador.x + jogador.width / 2;
		
		for (Elemento bloco : blocos) {
			if(bloco.y > linhaLimite){
				fimDeJogo = true;
				break;
			}
			
			if(colide(bloco, tiro) && bloco.y > 0){
				bloco.y -= bloco.speed * 2;
				tiro.y = bloco.y;
			} else {
				int sorte = r.nextInt(10);
				
				if(sorte == 0){
					bloco.y += bloco.speed + 1;
				} else if(sorte == 5){
					bloco.y -= bloco.speed;
				} else {
					bloco.y += bloco.speed;
				}
			}
			
			pontos = pontos + blocos.length;
		}
		
	}

	private boolean colide(Elemento elemento1, Elemento elemento2) {
		if(elemento1.x + elemento1.width >= elemento2.x && elemento1.x <= elemento2.x + elemento2.width){
			return true;
		}
		return false;
	}
	
	private void setaTecla(int tecla, boolean pressionada) {
		switch(tecla){
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
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			controleTecla[3] = pressionada;
			break;		
			
		}
		
	}

}
