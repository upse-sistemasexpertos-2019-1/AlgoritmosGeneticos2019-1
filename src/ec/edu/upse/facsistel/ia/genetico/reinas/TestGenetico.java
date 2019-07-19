package ec.edu.upse.facsistel.ia.genetico.reinas;

import java.util.List;

import ec.edu.upse.facistel.ia.geneticos.AlgoritmoGenetico;
import ec.edu.upse.facistel.ia.geneticos.Cromosoma;

public class TestGenetico {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlfabetoReina alfabetoReina = 
				new AlfabetoReina(new char[] {'0','1','2','3'});
		CromosomaReina cromosomaReina = 
				new CromosomaReina(alfabetoReina.getSetCaracteresUnicos().size(), 
						alfabetoReina);
		
		AlgoritmoGenetico.ejecutar(cromosomaReina, 10, 0.25, 100);
		
//		//Inicializar
//		List<Cromosoma> poblacionInicial =  AlgoritmoGenetico.inicializar(cromosomaReina, 10);
//		System.out.println("Poblacion Inicial");
//		System.out.println(poblacionInicial);
//		
//		//Evaluar
//		List<Cromosoma> poblacionEvaluada = AlgoritmoGenetico.evaluar(poblacionInicial);
//		System.out.println("Poblacion Evaluada");
//		System.out.println(poblacionEvaluada);
//		
//		//Seleccionar
//		List<Cromosoma> poblacionSeleccionada = AlgoritmoGenetico.seleccionar(poblacionEvaluada);
//		System.out.println("Poblacion Seleccionada");
//		System.out.println(poblacionSeleccionada);
//		
//		//Cruzar
//		List<Cromosoma> poblacionCruzada = AlgoritmoGenetico.recombinar(poblacionEvaluada);
//		System.out.println("Poblacion Cruzada");
//		System.out.println(poblacionCruzada);
//		
//		//Cruzar
//		List<Cromosoma> poblacionMutada = AlgoritmoGenetico.mutar(poblacionCruzada, 0.99);
//		System.out.println("Poblacion Mutada");
//		System.out.println(poblacionMutada);
	}

}
