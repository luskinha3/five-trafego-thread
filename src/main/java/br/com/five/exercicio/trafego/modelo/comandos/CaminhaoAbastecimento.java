package br.com.five.exercicio.trafego.modelo.comandos;

import java.util.Random;

import br.com.five.exercicio.trafego.modelo.Car;

public class CaminhaoAbastecimento {

	private int qtdCombustivelPosto;
	
	public CaminhaoAbastecimento() {
		this.qtdCombustivelPosto = 200;
	}

	public void chamarCaminhaoAbastecimento(Car carro) {
		synchronized (this) {
			try {
				if (getQtdCombustivelPosto() > 0) {
					Random rand = new Random();
					int sortx = rand.nextInt(30);
					
					System.out.println("iniciando abastecimento do carro:" + carro.getId());
					Thread.sleep(5000);
					System.out.println("abastecimento finalizado, proximo");
					carro.abastecer(sortx);
					System.out.println("qtd abastecida:" +sortx);
					this.retirarCombustivel(sortx);
				} else {
					
					System.out.println("cabousse combustivel");
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

}
