package TME3;

import java.util.ArrayList;

public class RadixTreeTest {
	
	public static void main(String[] args) {
		
		ArrayList<StringPosition> lsp;
		
		try {
			
			/*mettre a false le 2e param si on veut pas break la lecture du fichier a une certaine ligne
			 * mettre a false le 2e param si on veut pas afficher la table*/
			lsp = IndexTable.processIndexTable(100, true, false, "56667-0.txt");
			
			RadixTree t = new RadixTree();
			
			t.insererMot("bonjour", lsp);
			t.displayTree();
			
			//t.insererMot("bon", lsp);
			//t.displayTree();
			
			t.insererMot("bonjour", lsp);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}
