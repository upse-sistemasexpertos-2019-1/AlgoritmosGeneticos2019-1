package ec.edu.upse.facistel.ia.geneticos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Alfabeto {

	private final Set<Character> caracteresUnicos = new HashSet<Character>();
	private final List<Character> caracteres = new ArrayList<Character>();
	
	public Alfabeto(char[] arregloCaracteres)
	{
		for(int i = 0; i<arregloCaracteres.length; i++)
		{
			caracteresUnicos.add(arregloCaracteres[i]);
		}
		
		caracteres.addAll(caracteresUnicos);
	}
	
	public Set<Character> getSetCaracteresUnicos()
	{
		return this.caracteresUnicos;
	}
	
	public List<Character> getCaracteres()
	{
		return this.caracteres;
	}
}
