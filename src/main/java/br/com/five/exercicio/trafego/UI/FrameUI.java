package br.com.five.exercicio.trafego.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import br.com.five.exercicio.trafego.modelo.Car;
import br.com.five.exercicio.trafego.modelo.comandos.ComandoMover;

public class FrameUI extends JFrame {

	private JPanel jPanel2;

	public FrameUI() throws InterruptedException {
		initComponents();
	}

	private void initComponents() throws InterruptedException {
		jPanel2 = new Panel2();
		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.setContentPane(jPanel2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new FrameUI().setVisible(true);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	class Panel2 extends JPanel {
		private BlockingQueue<Car> carros;
		private ExecutorService threadPool;
		private AtomicBoolean estaLigado;

		Panel2() throws InterruptedException {
			setPreferredSize(new Dimension(850, 650));
			this.threadPool = Executors.newCachedThreadPool();
			this.estaLigado = new AtomicBoolean(true);
			iniciarCarros();
			teste();
		}

		private Color defineColor() {
			Random rand = new Random();
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			return new Color(r, g, b);
		}

		private void iniciarCarros() throws InterruptedException {
			Random rand = new Random();
			// Cria 10 novos carros
			carros = new ArrayBlockingQueue<Car>(10);
			for (int i = 0; i < 10; i++) {
				Car c = new Car(rand.nextInt(800), rand.nextInt(600), rand.nextInt(50), defineColor());
				c.mover();
				carros.put(c);
			}

		}

		private void teste() throws InterruptedException {
			
			this.threadPool.execute(new Runnable() {
				public void run() {	
					while(true) {
						Panel2.this.repaint();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}						
				}});
			
			this.carros.forEach(c -> this.threadPool.execute(new ComandoMover(c)));
			
			
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (Car c : carros) {
				c.draw(g);
			}
		}
	}
}
