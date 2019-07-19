package ec.edu.upse.facistel.ia.geneticos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ec.edu.upse.facsistel.ia.genetico.reinas.CromosomaReina;

public class AlgoritmoGenetico {

	public static Random r = new Random();
	//7 pasos
	//1 Inicializacion
	//2 Evaluacion
	//3 Seleccion
	//4 Recombinacion CX
	//5 Mutation
	//6 Sustitucion
	//7 Condiciones de Para


	private static List<Cromosoma> poblacion = new ArrayList<Cromosoma>();

	//Paso 1: Inicializo
	public static List<Cromosoma> inicializar(Cromosoma cromosoma, int tamanoPoblacion)
	{
		List<Cromosoma> poblacionInicilizada = new ArrayList<Cromosoma>();

		for(int i = 0; i<tamanoPoblacion; i++)
		{
			Cromosoma c = factoryCromosoma("reina", cromosoma); 
			c.generarCromosomaRandomico();
			poblacionInicilizada.add(c);
		}

		return poblacionInicilizada;
	}

	//Paso 2: Evaluo
	public static List<Cromosoma> evaluar(List<Cromosoma> poblacion)
	{
		for(Cromosoma c: poblacion)
		{
			c.calcularFitness();
		}

		return poblacion;
	}

	//Paso 3: Seleccion
	public static List<Cromosoma> seleccionar(List<Cromosoma> poblacion)
	{
		List<Cromosoma> poblacionSeleccionada = new ArrayList<Cromosoma>();
		List<Cromosoma> poblacionRepresentadaSegunFitness = new ArrayList<Cromosoma>();

		double mayorFitness=0;
		Cromosoma individuoMayorFitness;
		for(Cromosoma c: poblacion)
		{
			if(c.getFitness()>mayorFitness)
			{
				mayorFitness = c.getFitness();
				individuoMayorFitness = c;
			}
		}

		for(Cromosoma c: poblacion)
		{
			double valorFitnessInvertido = Math.abs(c.getFitness() - mayorFitness);
			for(int x=0; x<valorFitnessInvertido; x++)
			{
				poblacionRepresentadaSegunFitness.add(c.clonar(c));
			}
		}

		for(int y=0; y<poblacion.size(); y++)
		{
//			System.err.println("Antes del error");
//			System.err.println(poblacionRepresentadaSegunFitness.size());
//			System.err.println(poblacionRepresentadaSegunFitness);
			if(poblacionRepresentadaSegunFitness.size()<1)
			{
				return poblacion;
			}
			int indiceIndividuoSeleccionado = r.nextInt(poblacionRepresentadaSegunFitness.size());
			poblacionSeleccionada.add(poblacionRepresentadaSegunFitness.get(indiceIndividuoSeleccionado))
			;		}

		return poblacionSeleccionada;
	}

	//Paso 4: CrossOver (Cruce ... CX ... Recombinacion).
	public static List<Cromosoma> recombinar(List<Cromosoma> poblacionSeleccionada)
	{
		List<Cromosoma> poblacionCruzada = new ArrayList<Cromosoma>();

		do {
			//Selecciono Para cruce
			Cromosoma parent1 = poblacionSeleccionada.get(r.nextInt(poblacionSeleccionada.size()));
			Cromosoma parent2 = poblacionSeleccionada.get(r.nextInt(poblacionSeleccionada.size()));

			//Los cruzo
			Cromosoma hijo1 = parent1.cruzar(parent1, parent2, MecanismoCruce.SINGLE_POINT_CX);
			Cromosoma hijo2 = parent1.cruzar(parent2, parent1, MecanismoCruce.SINGLE_POINT_CX);
			poblacionCruzada.add(hijo1);
			poblacionCruzada.add(hijo2);
		}
		while(poblacionCruzada.size()<poblacionSeleccionada.size());

		return poblacionCruzada;
	}

	//Paso 5: Mutar
	public static List<Cromosoma> mutar(List<Cromosoma> poblacionCruzada, double probabilidadMutacion)
	{
		if(probabilidadMutacion>1)
		{
			System.err.println("Probabilidad mutacion debe ser menor");
		}

		boolean clonacion = false;
		int muto = r.nextInt(100);
		int probabilidadReal = (int) Math.round(probabilidadMutacion*100);
		for(int i = 0; i<probabilidadReal; i++)
		{
			if(muto == r.nextInt(100))
			{
				clonacion = true;
			}
		}

		if(clonacion)
		{
			Cromosoma cromosomaMutable = poblacionCruzada.get(r.nextInt(poblacionCruzada.size()));
			cromosomaMutable.mutar();
		}


		return poblacionCruzada;
	}

	//Paso 6 Reemplazo
	//Paso 7 COndiciones (los pongo en la ejecuacion)

	public static void ejecutar(Cromosoma base, int  tamanoPoblacion, double probabilidadMutacion, int numeroGeneraciones)
	{
		List<Cromosoma> poblacionInicial = inicializar(base, tamanoPoblacion);

		int generacion = 0;
		do {
			System.out.println("Poblacion Actual");
			System.out.println(poblacionInicial);
			pause();
			List<Cromosoma> poblacionEvaluada = evaluar(poblacionInicial);
			List<Cromosoma> poblacionSeleccionada = seleccionar(poblacionEvaluada);
			List<Cromosoma> poblacionCruzada = recombinar(poblacionSeleccionada);
			List<Cromosoma> poblacionMutada = mutar(poblacionCruzada, probabilidadMutacion);
			//Paso 6 Reemplazo
			poblacionInicial = poblacionMutada;
			generacion++;
		}while(generacion<numeroGeneraciones);
		
		System.out.println("Ultima Poblacion");
		System.out.println(poblacionInicial);
	}


	private static void pause() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Cromosoma factoryCromosoma(String s, Cromosoma c1)
	{
		Cromosoma c;
		switch (s) {
		case "reina":
			c = new CromosomaReina(c1.getLongitud(), c1.getAlfabeto());

			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + s);
		}
		return c;
	}
}
