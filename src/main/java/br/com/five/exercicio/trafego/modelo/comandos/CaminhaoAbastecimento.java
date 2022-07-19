package br.com.five.exercicio.trafego.modelo.comandos;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import br.com.five.exercicio.trafego.modelo.Car;

public class CaminhaoAbastecimento {

	private int qtdCombustivelPosto;
	private BlockingQueue<Car> filaCarros;
	
	public CaminhaoAbastecimento() {
		this.qtdCombustivelPosto = 50;
		this.filaCarros = new ArrayBlockingQueue<Car>(10);
	}

	public void chamarCaminhaoAbastecimento(Car carro) {
		synchronized (this) {
			
			this.filaCarros.offer(carro);
			
			try {
				if (getQtdCombustivelPosto() > 0) {
					//Random rand = new Random();
					//int sortx = rand.nextInt(getQtdCombustivelPosto());
					Car carroDaVez = this.filaCarros.take();
					System.out.println("iniciando abastecimento do carro: " + carro.getId());
					Thread.sleep(5000);
					System.out.println("finalizado o abastecimento do carro: " + carro.getId());
					carroDaVez.abastecer(10);				
					this.retirarCombustivel(10);
					System.out.println("Combustivel em estoque: " + getQtdCombustivelPosto());
				} else {
					
					System.out.println("As bombas de combustivel ficaram vazias, aguardar reabastecimento");
					this.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public int getQtdCombustivelPosto() {
		return this.qtdCombustivelPosto;
	}
	
	private void retirarCombustivel(int qtdCombustivel) {
		this.qtdCombustivelPosto = qtdCombustivelPosto - qtdCombustivel;
	}

	public void encherBombas(int combustivelNovo) {
		synchronized (this){
			this.qtdCombustivelPosto = qtdCombustivelPosto + combustivelNovo;
			System.out.println("As bombas de combustivel foram reabastecidas "
					+ "|Combutivel no reservatorio: " + getQtdCombustivelPosto() + "|");
			this.notifyAll();
		}
		
		
	}

}
