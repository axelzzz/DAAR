package TME1;

import java.util.ArrayList;
import java.util.List;

//automate déterministe
public class Dfa2 {
	
	//ETAPE 2
	//pour faire la démo etape 2 faire un sysout de la matrice automata

	//4 tableaux
	//une matrice, + tab init + tab final + tab eps-transit?
	//1 seul etat initial et 1 seul etat final?
	
	//fonction recursive
	public static Automate2 epsilonAutomation(RegExTree ret, 
											Automate2 automata) {
		
		
		//cas terminaux
		//lettres min + maj + 10 chiffres + le dot = 63 feuilles diff
		//fig 10.27c
		
		//si code ascii lettre ou chiffre, 
		if(ret.root >= 0x30 && ret.root <= 0x39 ||
		   ret.root >= 0x41 && ret.root <= 0x5A ||
		   ret.root >= 0x61 && ret.root <= 0x7A ||
		   ret.root == 0x2E)
			
		{
			
			//figure 10.27c
			//on cree un automate à 2 etats
			Automate2 nouvelAutomate = new Automate2(2, 256);
			int[][] tabNouvAutomate = nouvelAutomate.getAutomata();	
			List<Integer> statesNewAutomate = nouvelAutomate.getStates();
			tabNouvAutomate[0][ret.root] = statesNewAutomate.get(statesNewAutomate.size()-1);
			
			
			return nouvelAutomate;
		}
		
		
		else {
			
			
			//cas inductif
			//3 noeuds internes possibles : * | .
			
			
			RegExTree r1 = ret.subTrees.get(0);
			RegExTree r2 = null;
			
			if(ret.subTrees.size() > 1)
				r2 = ret.subTrees.get(1);
			
			
			switch(ret.root) {
			
				//pour | fig 10.28a
				case RegEx.ALTERN :
					
					
					Automate2 aR1 = new Automate2();
					Automate2 aR2 = new Automate2();
					
					return epsilonAutomation(r1, aR1)
							.fusionAutomataAltern(epsilonAutomation(r2, aR2));
					
					
				//pour . fig 10.28b	
				case RegEx.CONCAT :
					
					Automate2 aR1concat = new Automate2();
					Automate2 aR2concat = new Automate2();
					
					return epsilonAutomation(r1, aR1concat)
							.fusionAutomataAltern(epsilonAutomation(r2, aR2concat));
					
					
					
				//pour  * fig 10.28c
				case RegEx.ETOILE :
					
					Automate2 aR1etoile = new Automate2();
					
					return epsilonAutomation(r1, aR1etoile)
							.fusionAutomataEtoile();
					
				default :
					
					return null;
					
				
			}
			
		}
		
		
		
		
	}
	
	
	
	//ETAPE 3 determiniser
	//tableau
	
	//un tab definissant qui est etat init, etats finaux, et eps-transit
	//une matrice autom : int[10][256]
	//			  avec en indice ligne = numero etat,
	//					  indice colonne = numero transit
	//					  mat[x][y] = successeur
	
	
	
	
	
	
	
	
	
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
	
	
	
	
	public static void main(String[] args) {
		
		/*Init des structures*/
		Automate2 automata = new Automate2();
		
		
		/*Test 1 lettre*/
		
		/*
		RegExTree a = new RegExTree(97, new ArrayList<RegExTree>());
		
		System.out.println("TEST 1 lettre "+a.toString());
		
		automata = epsilonAutomation(a, automata, isInitialState, isFinalState, 
									epsilonTransit);
		
		afficherAutomate(automata.getAutomata());
		*/
		
		/*Test alternative a|b*/
		
		/*
		automata.reiniCpt();
		ArrayList<RegExTree> subTreez = new ArrayList<>();
		subTreez.add(new RegExTree(97, new ArrayList<RegExTree>()));
		subTreez.add(new RegExTree(98, new ArrayList<RegExTree>()));
		
		RegExTree altern = new RegExTree(RegEx.ALTERN, subTreez);
		
		automata = epsilonAutomation(altern, automata, epsilonTransit);
		System.out.println("\nTEST 1e alternative "+altern.toString());
		afficherAutomate(automata.getAutomata());
		*/
		
		/*Test alternative a|b|c*/
		
		/*
		automata.reiniCpt();
		
		ArrayList<RegExTree> subTreez2 = new ArrayList<>();
		
		subTreez2.add(new RegExTree(98, new ArrayList<RegExTree>()));
		subTreez2.add(new RegExTree(99, new ArrayList<RegExTree>()));	
		
		RegExTree altern2 = new RegExTree(RegEx.ALTERN, subTreez2);
		
		ArrayList<RegExTree> subTreez3 = new ArrayList<>();
		
		subTreez3.add(new RegExTree(97, new ArrayList<RegExTree>()));
		subTreez3.add(altern2);
		
		RegExTree altern3 = new RegExTree(RegEx.ALTERN, subTreez3);
		
		
		
		automata = epsilonAutomation(altern3, automata, epsilonTransit);
		
		
		System.out.println("\nTEST 2e alternative "+altern3.toString());
		afficherAutomate(automata.getAutomata());
		*/
		
		/*Test 1 concat a.b*/
		
		/*
		automata.reiniCpt();
		ArrayList<RegExTree> subTreez = new ArrayList<>();
		subTreez.add(new RegExTree(97, new ArrayList<RegExTree>()));
		subTreez.add(new RegExTree(98, new ArrayList<RegExTree>()));
		
		RegExTree concat = new RegExTree(RegEx.CONCAT, subTreez);
		
		automata = epsilonAutomation(concat, automata, epsilonTransit);
		System.out.println("\nTEST 1e concat "+concat.toString());
		afficherAutomate(automata.getAutomata());
		*/
		
		/*Test 2 concat a.b.c*/
		
		/*
		automata.reiniCpt();
		
		ArrayList<RegExTree> subTreez2 = new ArrayList<>();
		
		subTreez2.add(new RegExTree(98, new ArrayList<RegExTree>()));
		subTreez2.add(new RegExTree(99, new ArrayList<RegExTree>()));	
		
		RegExTree concat2 = new RegExTree(RegEx.CONCAT, subTreez2);
		
		ArrayList<RegExTree> subTreez3 = new ArrayList<>();
		
		subTreez3.add(new RegExTree(97, new ArrayList<RegExTree>()));
		subTreez3.add(concat2);
		
		RegExTree concat3 = new RegExTree(RegEx.CONCAT, subTreez3);
		
		
		
		automata = epsilonAutomation(concat3, automata, epsilonTransit);
		
		
		System.out.println("\nTEST 2e concat "+concat3.toString());
		afficherAutomate(automata.getAutomata());
		*/
		
		/*Test 1 etoile a* */
		
		automata.reiniCpt();
		ArrayList<RegExTree> subTreez = new ArrayList<>();
		subTreez.add(new RegExTree(97, new ArrayList<RegExTree>()));
		
		RegExTree etoile = new RegExTree(RegEx.ETOILE, subTreez);
		
		automata = epsilonAutomation(etoile, automata);
		System.out.println("\nTEST 1e etoile "+etoile.toString());
		afficherAutomate(automata.getAutomata());
	}
	
	
	//ETAPE 4 optionnelle (pour optimiser vitesse exec etape 5)
	
	
	//ETAPE 5 resultat pareil que commande grep 

}
