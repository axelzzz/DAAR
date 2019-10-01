package TME1;

import java.util.ArrayList;
import java.util.List;

public class Dfa {
	
	
	public static boolean isLeaf(int value) {
		
		return ( (value >= 0x30 && value <= 0x39) ||
				 (value >= 0x41 && value <= 0x5A) ||
				 (value >= 0x61 && value <= 0x7A) ||
				 (value == 0x2E) );
	}
	
	
	public static boolean isNodeConcatAltern(int value) {
		
		return ( (value == RegEx.ALTERN) || (value == RegEx.CONCAT) );
			
	}
	
	
	public static boolean isNodeEtoile(int value) {
		
		return value == RegEx.ETOILE;
	}
	
	
	public static int nbStates(RegExTree ret) {
		
		if(ret == null) return 0;
		
		else {			
			if( isLeaf(ret.root) ) 				
				return 2;			
			else {				
				if( isNodeConcatAltern(ret.root) ) 
					return 2 + nbStates(ret.subTrees.get(0)) + nbStates(ret.subTrees.get(1)); 				
				else {					
					if( isNodeEtoile(ret.root) ) 
						return nbStates(ret.subTrees.get(0));					
					else
						return 0;
				}
				
			}
		}
	}
	
	public static Automate epsilonAutomation(RegExTree ret, int cpt, int nbStates) {
		

		if( ret == null ) return null;
		
		else {
			if( isLeaf(ret.root) ) 
				return new Automate(nbStates, ret.root);
	
			
			else {
				
				RegExTree r1 = ret.subTrees.get(0);
				RegExTree r2 = null;
				
				if(ret.subTrees.size() > 1) 
					r2 = ret.subTrees.get(1);
					
				
				
				switch(ret.root) {
				
					case RegEx.ALTERN :	
						
						System.out.println("altern");
						Automate.incCpt();
						
						Automate a1 = epsilonAutomation(r1, cpt, nbStates);
						Automate a2 = epsilonAutomation(r2, cpt, nbStates);
						
						return a1.fusionAutomataAltern(a2);
					
					
					case RegEx.CONCAT :
					
						System.out.println("concat");
						Automate.incCpt();
						
						Automate c1 = epsilonAutomation(r1, cpt, nbStates);
						Automate c2 = epsilonAutomation(r2, cpt, nbStates);
						
						return c1.fusionAutomataConcat(c2);
					
					
					
				
					case RegEx.ETOILE :
						
						System.out.println("etoile");
						Automate.incCpt();
						
						return epsilonAutomation(r1, cpt, nbStates)
								.fusionAutomataEtoile();
						
					default :
						
						return null;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		ArrayList<RegExTree> subTreez = new ArrayList<>();
		subTreez.add(new RegExTree(99, new ArrayList<RegExTree>()));
		
		RegExTree etoile = new RegExTree(RegEx.ETOILE, subTreez);
		
		ArrayList<RegExTree> subTreez2 = new ArrayList<>();
		subTreez2.add(new RegExTree(98, new ArrayList<RegExTree>()));
		subTreez2.add(etoile);
			
		RegExTree concat = new RegExTree(RegEx.CONCAT, subTreez2);
		
		ArrayList<RegExTree> subTreez3 = new ArrayList<>();
		subTreez3.add(new RegExTree(97, new ArrayList<RegExTree>()));
		subTreez3.add(concat);
		
		RegExTree tree = new RegExTree(RegEx.ALTERN, subTreez3);
		
		
		System.out.println(tree.toString());
		System.out.println("nb states "+nbStates(tree));
		
		
		Automate test = epsilonAutomation(tree, 0, nbStates(tree) );
		test.afficherAutomate();
	}

}
