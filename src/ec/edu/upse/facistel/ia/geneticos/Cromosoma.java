package ec.edu.upse.facistel.ia.geneticos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Cromosoma implements Cruzable, Mutable {

	private final int longitud;
	private char[] vectorCromosoma;
	private final Alfabeto alfabeto;
	private double fitness = 0;
	
	Random r = new Random();
	
	public Cromosoma(int longitud, Alfabeto alfabeto) {
		super();
		this.longitud = longitud;
		this.vectorCromosoma = new char[longitud];
		this.alfabeto = alfabeto;
	}
	
	public Cromosoma(Cromosoma otroCromosoma)
	{
		this.longitud = otroCromosoma.longitud;
		this.vectorCromosoma = Arrays.copyOf(otroCromosoma.vectorCromosoma, longitud);
		this.alfabeto = otroCromosoma.alfabeto;
		calcularFitness();
	}
	
	
	//A manera de factory (fabrica)
	public Cromosoma generarCromosomaRandomico()
	{
		Random r = new Random();
		for(int i=0; i<longitud; i++)
		{
			char alelo = 
					alfabeto.getCaracteres().get(r.nextInt(alfabeto.getCaracteres().size()));
			vectorCromosoma[i] = alelo;
		}
		return this;
	}
	
	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	
	@Override 
	public String toString()
	{
		String cromosomaString = new String();
		for(char c: vectorCromosoma)
		{
			cromosomaString = cromosomaString + c;
		}
		cromosomaString = cromosomaString + "\t fitness" + fitness;
		cromosomaString = cromosomaString +"\n";
		return cromosomaString;
	}

	public int getLongitud() {
		return longitud;
	}

	public char[] getVectorCromosoma() {
		return vectorCromosoma;
	}
	
	public void setVectorCromosoma(char[] vector) {
		this.vectorCromosoma = vector;
	}

	public Alfabeto getAlfabeto() {
		return alfabeto;
	}
	
	public abstract double calcularFitness();
	
	public abstract Cromosoma clonar(Cromosoma c);
	
	@Override
	public Cromosoma cruzar(Cromosoma p1, Cromosoma p2, MecanismoCruce mecanismo) {
		
		Cromosoma cruzado;
		switch (mecanismo) {
		case SINGLE_POINT_CX:
			int puntoCruce = p1.getVectorCromosoma().length/2;
			cruzado = cruceSinglePoint(p1, p2, puntoCruce);
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + mecanismo);
		}
		return cruzado;
	}
	
	private static Cromosoma cruceSinglePoint(Cromosoma p1, Cromosoma p2, int puntoCruce)
	{
		char[] nuevoVectorReal = new char[p1.getVectorCromosoma().length];
		
		for(int i = 0; i<puntoCruce; i++)
		{
			nuevoVectorReal[i] = p1.getVectorCromosoma()[i];
		}
		
		for(int i = puntoCruce; i<p1.getVectorCromosoma().length; i++)
		{
			nuevoVectorReal[i] = p2.getVectorCromosoma()[i];
		}
		
			
		Cromosoma nuevoHijo = p1.clonar(p1);
		nuevoHijo.setVectorCromosoma(nuevoVectorReal);
		nuevoHijo.calcularFitness();
		return nuevoHijo;
	}
	
	private static char[] convertirVectorCharacterAChar(Character[] arreglo)
	{
		char[] vectorSimple = new char[arreglo.length];
		for(int i=0; i<arreglo.length; i++)
		{
			vectorSimple[i] = arreglo[i];
		}
		return vectorSimple;
	}
	
	public void mutar()
	{
		
		System.out.println("Antes de mutar " + this);
		int genMutable = r.nextInt(longitud);
		vectorCromosoma[genMutable] = mutarGen();
		mutarGen();
		System.out.println("Depues de mutar " + this);
	}
	
	private char mutarGen()
	{
		char genNuevo = alfabeto.getCaracteres().get(r.nextInt(alfabeto.getCaracteres().size()));
		return genNuevo;
	}
}
