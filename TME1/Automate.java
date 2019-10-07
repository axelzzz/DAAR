package TME1;

import java.util.ArrayList;
import java.util.List;

public class Automate {

	private int[][] automata;
	private int[][] epsTransit;
	/*peut remplacer les tableaux par 2 entiers finalement*/
	private boolean[] isFinalState;
	private boolean[] isStartingState;
	private final static int nbCol = 256;
	private int nbStates;
	
	private static int cpt=0;
	
	/*automata for leafs*/
	public Automate(int nbStates, int labelTransit) {
		
		this.automata = new int[nbStates][nbCol];
		this.epsTransit = new int[nbStates][nbStates];		
		this.isFinalState = new boolean[nbStates];
		this.isStartingState = new boolean[nbStates];
		this.nbStates = nbStates;
		
		for(int i=0 ; i<nbStates ; i++) {
			
			this.isFinalState[i] = false;
			this.isStartingState[i] = false;
			
			for(int j=0 ; j<nbCol ; j++) 
				this.automata[i][j] = -1;
			for(int j=0 ; j<nbStates ; j++)
				this.epsTransit[i][j] = -1;
		}
		
		isFinalState[cpt+1] = true;
		isStartingState[cpt] = true;
		
		automata[cpt][labelTransit] = cpt+1;		
		cpt+=2;
		
	}
	
	
	/*automata for nodes*/
	public Automate(int nbStates, boolean isNode, int startingState, int finalState) {
		
		this.automata = new int[nbStates][nbCol];
		this.epsTransit = new int[nbStates][nbStates];
		this.isFinalState = new boolean[nbStates];
		this.isStartingState = new boolean[nbStates];		
		this.nbStates = nbStates;
		
		for(int i=0 ; i<nbStates ; i++) {
			
			this.isFinalState[i] = false;
			this.isStartingState[i] = false;
			
			for(int j=0 ; j<nbCol ; j++) 
				this.automata[i][j] = -1;
			for(int j=0 ; j<nbStates ; j++)
				this.epsTransit[i][j] = -1;
			
		}
		
		isFinalState[finalState] = true;
		isStartingState[startingState] = true;
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
	
	public int[][] getEpsTransit() { return epsTransit; }
	
	public int getNbStates() { return nbStates; }
	
	public static int getCpt() { return Automate.cpt; }
	
	public static void incCpt() { Automate.cpt++; }
	
	public void setEpsTransit(int fromState, int toState) {
		epsTransit[fromState][toState] = 1;
	}
	

	public int getNumberOfStartingState() {
		
		for(int i=0 ; i<nbStates ; i++)
			if(isStartingState[i])
				return i;
		return -1;
	}
	
	public int getNumberOfFinalState() {
		
		for(int i=0 ; i<nbStates ; i++)
			if(isFinalState[i])
				return i;
		return -1;
	}
	
	
	/*Display methods*/	
	public void afficherAutomate() {
		
		for(int i=0 ; i<nbStates ; i++) {
			for(int j=0 ; j<nbCol ; j++) {
				if(j/100 > 0)
					System.out.print(automata[i][j]+"      ");
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

		System.out.println("\n");
	}
	
	
	
	
	
	public void displayPertinentColumns() {
		
		for(int i=0 ; i<nbStates ; i++) {
			for(int j=0 ; j<nbCol ; j++) {
				if(automata[i][j] != -1) 
					System.out.println(i+" --"+Character.toString(j)+"--> "+automata[i][j]);
			}
		}
	}
	

	public void afficherFinalState() {
		
		for(int i=0 ; i<nbStates ; i++) 
			System.out.print(isFinalState[i]+" ");
	}
	
	
	
	public void afficherStartingState() {
		
		for(int i=0 ; i<nbStates ; i++) 
			System.out.print(isStartingState[i]+" ");
	}
	
	
	
	public void afficherEpsTransit() {
		
		System.out.println();
		for(int i=0 ; i<nbStates ; i++) {
			for(int j=0 ; j<nbStates ; j++) {
				if(epsTransit[i][j] != -1)
					System.out.print("eps-transit from "+i+" to "+j+"\n");
			}			
		}
	}
	
	
	/*Treatment methods*/
	public Automate fusionAutomataAltern(Automate toFusion) {
		
		Automate fusion = new Automate(nbStates, true, this.getNumberOfStartingState()-1, toFusion.getNumberOfFinalState()+1);
		int[][] automataFusion = fusion.getAutomata();
		
		for(int i=0 ; i<this.nbStates ; i++) {
		
			for(int j=0 ; j<nbCol ; j++) {
				if(automata[i][j] != -1)
					automataFusion[i][j] = automata[i][j];
				else {
					if(toFusion.getAutomata()[i][j] != -1)
						automataFusion[i][j] = toFusion.getAutomata()[i][j];
					else 
						automataFusion[i][j] = -1;
				}
			}
			
			for(int j=0 ; j<nbStates ; j++) {
				
				if(this.getEpsTransit()[i][j] != -1 && toFusion.getEpsTransit()[i][j] == -1)
					fusion.getEpsTransit()[i][j] = this.getEpsTransit()[i][j];
				else {
					if(this.getEpsTransit()[i][j] == -1 && toFusion.getEpsTransit()[i][j] != -1)
						fusion.getEpsTransit()[i][j] = toFusion.getEpsTransit()[i][j];
					else {
						if(this.getEpsTransit()[i][j] != -1 && toFusion.getEpsTransit()[i][j] != -1)
							fusion.getEpsTransit()[i][j] = this.getEpsTransit()[i][j];
					}
				}
			}
		}
		//pour epstransit faire des fusions comme pr matrice
		fusion.setEpsTransit(fusion.getNumberOfStartingState(), this.getNumberOfStartingState());
		fusion.setEpsTransit(fusion.getNumberOfStartingState(), toFusion.getNumberOfStartingState());
		fusion.setEpsTransit(this.getNumberOfFinalState(), fusion.getNumberOfFinalState());
		fusion.setEpsTransit(toFusion.getNumberOfFinalState(), fusion.getNumberOfFinalState());
		
		return fusion;
	}
	
	public Automate fusionAutomataConcat(Automate toFusion) {
		
		Automate fusion = new Automate(nbStates, true, this.getNumberOfStartingState(), toFusion.getNumberOfFinalState());
		int[][] automataFusion = fusion.getAutomata();
		
		for(int i=0 ; i<this.nbStates ; i++) {
			
			for(int j=0 ; j<nbCol ; j++) {
				if(automata[i][j] != -1)
					automataFusion[i][j] = automata[i][j];
				else {
					if(toFusion.getAutomata()[i][j] != -1)
						automataFusion[i][j] = toFusion.getAutomata()[i][j];
					else 
						automataFusion[i][j] = -1;
				}
			}
			
			for(int j=0 ; j<nbStates ; j++) {
				
				if(this.getEpsTransit()[i][j] != -1 && toFusion.getEpsTransit()[i][j] == -1)
					fusion.getEpsTransit()[i][j] = this.getEpsTransit()[i][j];
				else {
					if(this.getEpsTransit()[i][j] == -1 && toFusion.getEpsTransit()[i][j] != -1)
						fusion.getEpsTransit()[i][j] = toFusion.getEpsTransit()[i][j];
					else {
						if(this.getEpsTransit()[i][j] != -1 && toFusion.getEpsTransit()[i][j] != -1)
							fusion.getEpsTransit()[i][j] = this.getEpsTransit()[i][j];
					}
				}
			}
		}
		
		fusion.setEpsTransit(this.getNumberOfFinalState(), toFusion.getNumberOfStartingState());
		
		return fusion;
		
	}

	
	public Automate fusionAutomataEtoile() {
		
		Automate fusion = new Automate(nbStates, true, this.getNumberOfStartingState()-1, this.getNumberOfFinalState()+1);
		int[][] automataFusion = fusion.getAutomata();
		
		for(int i=0 ; i<this.nbStates ; i++) {
			
			for(int j=0 ; j<nbCol ; j++) 			
				automataFusion[i][j] = automata[i][j];	
			
			for(int j=0 ; j<nbStates ; j++)
				fusion.getEpsTransit()[i][j] = this.getEpsTransit()[i][j];
		}		
				
		
		fusion.setEpsTransit(fusion.getNumberOfStartingState(), this.getNumberOfStartingState());
		fusion.setEpsTransit(fusion.getNumberOfStartingState(), fusion.getNumberOfFinalState());
		fusion.setEpsTransit(this.getNumberOfFinalState(), this.getNumberOfStartingState());
		fusion.setEpsTransit(this.getNumberOfFinalState(), fusion.getNumberOfFinalState());
		
		return fusion;
	}
	
	
	/*public Automate determiniser() {
		
		
	}*/
	
	
}
