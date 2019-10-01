package TME1;

import java.util.ArrayList;
import java.util.List;

public class Automate {

	private int[][] automata;
	private List<Integer> states;
	private int[] epsTransit;
	private final static int nbCol = 256;
	public int nbStates;
	
	private static int cpt=0;
	
	/*automata for leafs*/
	public Automate(int nbStates, int labelTransit) {
		
		this.automata = new int[nbStates][nbCol];
		this.states = new ArrayList<>();
		this.epsTransit = new int[nbStates];
		
		this.initAutomata();
		automata[cpt][labelTransit] = cpt+1;
		this.nbStates = nbStates;
		//afficherAutomate();
		cpt+=2;
		
	}
	
	
	/*automata for nodes*/
	public Automate(int nbStates, boolean isNode) {
		
		this.automata = new int[nbStates][nbCol];
		this.states = new ArrayList<>();
		this.epsTransit = new int[nbStates];
		
		this.initAutomata();
		
		this.nbStates = nbStates;
	}
	
	public List<Integer> cloneList(List<Integer> toClone) {
		
		List<Integer> res = new ArrayList<>();
		
		if(toClone != null && toClone.size() > 0) {
			for(Integer s:toClone) 
				res.add(Integer.valueOf(s.intValue()));
		}
		
		return res;		
	}
	
	public int[][] getAutomata() { return automata; }
	
	public List<Integer> getStates(){ return states; }
	
	public int getNbStates() { return states.size(); }
	
	public int[] getEpsTransit() { return epsTransit; }
	
	public void setStates(List<Integer> s) { states = cloneList(s); }
	
	public static void incCpt() { Automate.cpt++; }
	
	public static int getCpt() { return Automate.cpt; }
	
	public void setAutomata(int[][] toSet) { this.automata = toSet; }
	
	public void initAutomata() {
		for(int i=0 ; i<nbStates ; i++)
			for(int j=0 ; j<nbCol ; j++) {
				this.automata[i][j] = -1;
			}
	}
	
	/*Treatment methods*/	
	public void afficherAutomate() {
		
		for(int i=0 ; i<nbStates ; i++) {
			for(int j=0 ; j<nbCol ; j++) {
				if(j/100 > 0)
					System.out.print(automata[i][j]+"      ");
				else {
					if(j/10 > 0)
						System.out.print(automata[i][j]+"     ");
					else
						System.out.print(automata[i][j]+"    ");
				}					
			}
			System.out.println();
		}
		
		for(int i=0 ; i<automata[0].length ; i++) 				
			System.out.print(i+"    ");				

		System.out.println("\n");
	}
	
	
	
	public Automate fusionAutomataAltern(Automate toFusion) {
		
		Automate fusion = new Automate(nbStates, true);
		int[][] automataFusion = fusion.getAutomata();
		
		for(int i=0 ; i<this.nbStates ; i++)
			for(int j=0 ; j<nbCol ; j++) 
				if(automata[i][j] != -1)
					automataFusion[i][j] = automata[i][j];
				else {
					if(toFusion.getAutomata()[i][j] != -1)
						automataFusion[i][j] = toFusion.getAutomata()[i][j];
					else 
						automataFusion[i][j] = -1;
				}
		
		return fusion;
	}
	
	public Automate fusionAutomataConcat(Automate toFusion) {
		
		Automate fusion = new Automate(nbStates, true);
		int[][] automataFusion = fusion.getAutomata();
		
		for(int i=0 ; i<this.nbStates ; i++)
			for(int j=0 ; j<nbCol ; j++) 
				if(automata[i][j] != -1)
					automataFusion[i][j] = automata[i][j];
				else {
					if(toFusion.getAutomata()[i][j] != -1)
						automataFusion[i][j] = toFusion.getAutomata()[i][j];
					else 
						automataFusion[i][j] = -1;
				}
		
		return fusion;
		
	}

	public Automate fusionAutomataEtoile() {
		
		Automate fusion = new Automate(nbStates, true);
		int[][] automataFusion = fusion.getAutomata();
		
		for(int i=0 ; i<this.nbStates ; i++)
			for(int j=0 ; j<nbCol ; j++) 			
				automataFusion[i][j] = automata[i][j];				
				
		
		return fusion;
	}
	
	
}
