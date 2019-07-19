package ec.edu.upse.facistel.ia.geneticos;

public interface Cruzable {
	public Cromosoma cruzar(Cromosoma p1, Cromosoma p2, MecanismoCruce mecanismo);
}
