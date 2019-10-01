package TME1;

import java.util.ArrayList;
import java.util.List;

public class Automate2 {

	private int[][] automata;
	private List<Integer> states;
	private int[] epsTransit;
	
	private static int cpt = 0;
	
	
	/*Constructors*/
	public Automate2() {
		this.automata = null;
		states = null;
		epsTransit = null;
	}

	
	public Automate2(int lig, int col) {
			
			
		this.automata = new int[lig][col];
			
		for(int i=0 ; i<lig ; i++)
			for(int j=0 ; j<col ;j++)
				this.automata[i][j] = -1;
			
		this.states = new ArrayList<>();
		this.epsTransit = new int[lig];
			
		for(int i=0 ; i<lig ; i++) {
			
			states.add(Integer.valueOf(cpt));
			cpt++;
			epsTransit[i] = -1;
		}
			
	}
	
	
	/*Accessors*/
	public int[][] getAutomata() { return automata ;}
	
	public List<Integer> getStates() { return states; }
	
	public int[] getEpsTransit() { return epsTransit; } 
	
	/*Setters*/
	public void setStates(List<Integer> s) {
		this.states = s;
	}
	
	public void setMatrice(int[][] a) {
		this.automata = a;
	}
	
	public void setEpsTransits(int[] e) {
		this.epsTransit = e;
	}
	
	
	public void set1EpsTransit(int index, int value) {
		this.epsTransit[index] = value;
	}
	
	/*Treatment methods*/	
	public static void afficherAutomate(int[][] automata) {
		
		for(int i=0 ; i<automata.length ; i++) {
			for(int j=0 ; j<automata[0].length ; j++) {
				if(j/100 > 0)
					System.out.print(automata[i][j]+"     ");
				else {
					if(j/10 > 0)
						System.out.print(automata[i][j]+"    ");
					else
						System.out.print(automata[i][j]+"   ");
				}					
			}
			System.out.println();
		}
		
		for(int i=0 ; i<automata[0].length ; i++) 				
			System.out.print(i+"    ");				

	}
	
	
	public void reiniCpt() { cpt = 0;} 
	
	public void updateAutomata(Automate2 at) {
		
		int[][] a = at.getAutomata();
		
		for(int i=0 ; i<a.length ; i++)
			for(int j=0 ; j<a[0].length ; j++)
				if(a[i][j] != -1) 
					a[i][j] = a[i][j]+1;
					
					
	}
	
	public void resizeSubAutomata(Automate2 at, int size) {
		
		List<Integer> states = at.getStates();
		
		int[][] matAuto = at.getAutomata();
		int[][] automataResized = new int[size][matAuto[0].length];
		
		for(int i = 0 ; i<size ; i++) {
			for(int j=0 ; j<matAuto[0].length ; j++) {				
				automataResized[i][j] = -1; 						
			}
		}
		
		
		for(int i = 0 ; i<matAuto.length ; i++) {
			for(int j=0 ; j<matAuto[0].length ; j++) {				
				automataResized[i][j] = matAuto[i][j]; 						
			}
		}
		
		//on parcourt les etats pour recuperer leur succ
		
			
			
			
	
			
		
		
		
		
		
		at.setMatrice(automataResized);
				
	}
	
	public void updateAndResizeSubAutomataForFusion(Automate2 at, int size) {
		
		updateAutomata(at);
		resizeSubAutomata(at, size);
	}
	
	public Automate2 fusionAutomataAltern(Automate2 a2) {
		
		Automate2 fusion;
		
		int nbStatesFusion = 2 + this.automata.length + a2.getAutomata().length;
		int transit = 256;
		
		//on reinitialise les etats pour la fusion
		reiniCpt();
				

		//System.out.println("states in R1 "+states);
		//System.out.println("states in R2 "+a2.getStates());
		
		fusion = new Automate2(nbStatesFusion, transit);
		int[][] resFusion = fusion.getAutomata();
		List<Integer> statesFusion = fusion.getStates();
		
		set1EpsTransit(statesFusion.get(0), statesFusion.get(1));		
		set1EpsTransit(statesFusion.get(0), statesFusion.get(3));
		set1EpsTransit(statesFusion.get(2), statesFusion.get(states.size()-1));
		set1EpsTransit(statesFusion.get(4), states.get(statesFusion.size()-1));
		
		
		//System.out.println("\nnb states fusion "+nbStatesFusion);

		//System.out.println("states in R1 "+states);
		//System.out.println("states in R2 "+a2.getStates());
		//System.out.println("states in fusion "+fusion.getStates());
				
		
		
		//System.out.println("\nr1 before update ");
		//afficherAutomate(automata);
		//System.out.println("\nr2 before update ");
		//afficherAutomate(a2.getAutomata());
		
		updateAndResizeSubAutomataForFusion(this, nbStatesFusion);
		updateAndResizeSubAutomataForFusion(a2, nbStatesFusion);
		
		//System.out.println("\nr1 after update ");
		//afficherAutomate(automata);
		//System.out.println("\nr2 after update ");
		//afficherAutomate(a2.getAutomata());
		
		//on remplit le tab fusion d'abord par tab de R1
		for(int i=0 ; i<nbStatesFusion ; i++)
			for(int j=0 ; j<transit ; j++) 				
				resFusion[i][j] = this.automata[i][j];				
				
			
		//ensuite on remplit avec R2
		for(int i=0 ; i<nbStatesFusion ; i++)
			for(int j=0 ; j<transit ; j++) {
				int currentA2elem = a2.getAutomata()[i][j];
				if(resFusion[i][j] == -1 && currentA2elem != -1)
					resFusion[i][j] = currentA2elem;
			}		
		System.out.println("fusion ");
		afficherAutomate(resFusion);
		
		return fusion;
	}
	
	public Automate2 fusionAutomataConcat(Automate2 a2) {
			
			Automate2 fusion;
			
			int nbStatesFusion = this.automata.length + a2.getAutomata().length;
			int transit = 256;
			
			reiniCpt();
			fusion = new Automate2(nbStatesFusion, transit);
			int[][] resFusion = fusion.getAutomata();
			
			List<Integer> statesFusion = fusion.getStates();
			
			set1EpsTransit(statesFusion.get(1), statesFusion.get(2));
			
			//on remplit le tab fusion d'abord par tab de R1
			for(int i=0 ; i<nbStatesFusion ; i++)
				for(int j=0 ; j<transit ; j++) 				
					resFusion[i][j] = this.automata[i][j];				
					
				
			//ensuite on remplit avec R2
			for(int i=0 ; i<nbStatesFusion ; i++)
				for(int j=0 ; j<transit ; j++) {
					int currentA2elem = a2.getAutomata()[i][j];
					if(resFusion[i][j] == -1 && currentA2elem != -1)
						resFusion[i][j] = currentA2elem;
				}		
			System.out.println("fusion ");
			afficherAutomate(resFusion);
			
			return fusion;
		}
	
	public Automate2 fusionAutomataEtoile() {
		
		Automate2 fusion;
		
		int nbStatesFusion = this.automata.length + 2;
		int transit = 256;
		
		reiniCpt();
		fusion = new Automate2(nbStatesFusion, transit);
		List<Integer> statesFusion = fusion.getStates();
		
		set1EpsTransit(statesFusion.get(0), statesFusion.get(1));
		set1EpsTransit(statesFusion.get(2), statesFusion.get(1));
		set1EpsTransit(statesFusion.get(0), statesFusion.get(statesFusion.size()-1));
		set1EpsTransit(statesFusion.get(2), statesFusion.get(statesFusion.size()-1));
		
		updateAndResizeSubAutomataForFusion(this, nbStatesFusion);
		
		fusion.setMatrice(this.automata);
		
		return fusion;
	}
	
	
}
