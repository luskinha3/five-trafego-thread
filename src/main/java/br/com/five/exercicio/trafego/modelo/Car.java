package br.com.five.exercicio.trafego.modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Car {

	private int x;
	private int y;
	private Color color;
	private int combustivel;

	public Car(int x, int y, int combustivel, Color color) {
		this.x = x;
		this.y = y;
		this.combustivel = combustivel;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getCombustivel() {
		return combustivel;
	}

	public Color getColor() {
		return color;
	}

	public void mover() {
		if (combustivel > 0) {
			Random rand = new Random();
			int sortx = rand.nextInt(30) - 10;
			x += sortx;
			y += rand.nextInt(30) - 10;
			if (x > 800 || y > 600) {
				//
				System.out.print("volta pro inicio");
				x = 0;
				y = 0;
			}
			// consome o combustível
			combustivel -= sortx;
		}
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(getX(), getY(), 30, 30);
		if (combustivel <= 0) {
			g.drawString("X", getX(), getY() + 10);
			//
			System.out.println("Necessário abastecer o veiculo");
		}
	}
}
