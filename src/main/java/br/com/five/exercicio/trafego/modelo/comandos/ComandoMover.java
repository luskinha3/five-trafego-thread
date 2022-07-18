package br.com.five.exercicio.trafego.modelo.comandos;

import br.com.five.exercicio.trafego.modelo.Car;

public class ComandoMover implements Runnable{
	
	private Car carro;
		
	public ComandoMover(Car carro) {
		this.carro = carro;
	}
	@Override
	public void run() {
		
		while (carro.getCombustivel() > 0) {
			
			carro.mover();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}