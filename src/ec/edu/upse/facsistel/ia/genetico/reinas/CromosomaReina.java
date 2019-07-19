package ec.edu.upse.facsistel.ia.genetico.reinas;

import ec.edu.upse.facistel.ia.geneticos.Alfabeto;
import ec.edu.upse.facistel.ia.geneticos.Cromosoma;

public class CromosomaReina extends Cromosoma {

	public CromosomaReina(int longitud, Alfabeto alfabeto) {
		super(longitud, alfabeto);
	}

	public CromosomaReina(Cromosoma c) {
		super(c);
	}

	@Override
	public double calcularFitness() {
		
		int choquesTotales = 0;
		for(int i=0; i<this.getVectorCromosoma().length-1; i++)
		{
			choquesTotales = choquesTotales + contarChoques(i, this.getVectorCromosoma());
		}
		this.setFitness(choquesTotales);
		return choquesTotales;
	}

	private int contarChoques(int i, char[] vectorCromosoma) {
		
		int choquesParaI = 0;
		int cuadrosDiferencia =0;
		for(int j = i+1; j<this.getVectorCromosoma().length; j++)
		{
			cuadrosDiferencia++;
			//Choques Verticales no hay 
			//Choques horizontales [0,0,0,0]
			if(this.getVectorCromosoma()[i] == this.getVectorCromosoma()[j])
			{
				choquesParaI++;
			}
			
			//Choques Diagonales [0,1,2,3]
			if(this.getVectorCromosoma()[i]==this.getVectorCromosoma()[j]+cuadrosDiferencia)
			{
				choquesParaI++;
			}
			
			if(this.getVectorCromosoma()[i]==this.getVectorCromosoma()[j]-cuadrosDiferencia)
			{
				choquesParaI++;
			}
			
		}
		return choquesParaI;
	}

	@Override
	public Cromosoma clonar(Cromosoma c) {
		Cromosoma clonado = new CromosomaReina(c);
		return clonado;
	}


}
