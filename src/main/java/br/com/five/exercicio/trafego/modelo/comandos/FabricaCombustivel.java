package br.com.five.exercicio.trafego.modelo.comandos;

public class FabricaCombustivel implements Runnable {
	
	private CaminhaoAbastecimento caminhaoPosto;
	

	public FabricaCombustivel(CaminhaoAbastecimento caminhaoPosto) {
		this.caminhaoPosto = caminhaoPosto;
	}


	@Override
	public void run() {
		try {
			Thread.sleep(30000);
			caminhaoPosto.encherBombas(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}


	
	
	
}
