package br.com.five.exercicio.trafego.modelo.comandos;

import br.com.five.exercicio.trafego.modelo.Car;

public class ComandoMover implements Runnable{
	
	private Car carro;
	private CaminhaoAbastecimento caminhaoPosto;
		
	public ComandoMover(Car carro, CaminhaoAbastecimento caminhaoPosto) {
		this.carro = carro;
		this.caminhaoPosto = caminhaoPosto;
	}
	@Override
	public void run() {
		
		while (true) {
						
			try {
				if(carro.getCombustivel() <= 0) {
					System.out.println("Necessário abastecer o veiculo: " + carro.getId());
					caminhaoPosto.chamarCaminhaoAbastecimento(carro);
				}
				carro.mover();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
