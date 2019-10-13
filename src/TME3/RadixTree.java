package TME3;

import java.util.ArrayList;

public class RadixTree {
	
	private Node node;
	
	
	
	public RadixTree(Node node) {
		
		this.node = node;
	}
	
	
	public RadixTree() {
		
		node = new Node("\"\"");
	}
	
	
	
	public Node getNode() { return node; }
	
	
	
	

	public ArrayList<Position> getPositionsOfString(ArrayList<StringPosition> lsp, String mot) {
		
		for(StringPosition sp:lsp) {
			if(sp.getWord().equals(mot))
				return sp.getPos();
		}
		
		return null;
	}
	
	
	
	
	public void insererMot(String mot, ArrayList<StringPosition> lsp) {
		
		/*si c est la racine*/
		if(node.getLabel() == "\"\"") {
			
			ArrayList<RadixTree> fils = node.getFils();
			/*si elle n a pas de fils, on cree un fils et on insere le mot au fils*/
			if(fils.size() == 0) {
				fils.add( new RadixTree( new Node(mot, getPositionsOfString(lsp, mot)) ) );	
				//fils.get(0).insererMot(mot, lsp);
			}
			
			/*si elle a deja un/des fils, on regarde si on peut
			 * le mettre ds un des fils sinon on en cree un et on insere le mot dedans*/
			else {
					
				boolean hasFindSon = false;
				
				for(RadixTree rt:node.getFils() ) {
					/*si l un des fils a le meme label, rien a faire*/
					if(mot.equals(rt.getNode().getLabel())) {
						System.out.println("mot deja insere, rien a faire");
						break;
					}
					else {
						if(rt.getNode().getLabel().charAt(0) == mot.charAt(0)) {
							rt.insererMot(mot, lsp);
							hasFindSon = true;
							System.out.println("has find son");
							break;
						}
					}
				}
				if(!hasFindSon) {
					System.out.println("has not find son");
					node.getFils().add( new RadixTree( new Node(mot) ) );
				}
			}
		}
		
		/*si c est pas la racine*/
		else {
			
			/*si feuille*/
			if(node.isLeaf()) {
				
				String currentLabel = node.getLabel();
				/*si le mot qu on insere est prefixe du label courant
				 * on cree un fils et y ajoute le suffixe*/
				if( isPrefix(mot, currentLabel) ) {
					node.setLabel(mot);
					String suffixe = suffixe(mot, currentLabel);
					System.out.println("suffixe "+suffixe);
					node.getFils().add( new RadixTree( new Node(suffixe) ) );
				}
				
				else {
					/*si le mot egal au label, mot deja insere*/
					if(mot.equals(currentLabel))
						System.out.println("mot deja insere, rien a faire");
					
					else {
						
					}
				}
				
			}
				
				
				
			/*si noeud interne*/	
			else {
				/*si le mot a inserer est prefixe du node,
				 * on garde le prefixe dans le node courant et on insere le suffixe 
				 * recursivement sur un des fils*/
				String currentLabel = node.getLabel();
				if( isPrefix(mot, currentLabel) ) {
					node.setLabel(mot);
					String suffixe = suffixe(mot, currentLabel);
					System.out.println("suffixe "+suffixe);
						
					boolean hasFindSon = false;
					/*cherche si on peut mettre le suffixe dans un des fils existant*/
					for(RadixTree rt:node.getFils() ) {
						if(rt.getNode().getLabel().charAt(0) == suffixe.charAt(0)) {
							rt.insererMot(suffixe, lsp);
							hasFindSon = true;
							System.out.println("has find son");
							break;
						}
					}
						
					/*si aucun des fils ne commence par la meme lettre que le mot
					 * on cree un nouveau fils et on y ajoute le suffixe*/
					if(!hasFindSon) {
						System.out.println("has not find son");
						node.getFils().add( new RadixTree( new Node(suffixe) ) );
					}						
				}
				
					
					
			}
		}
			
			
	}
		
		
		
	
	
	
	
	/*teste si mot1 est prefixe de mot2*/
	public boolean isPrefix(String mot1, String mot2) {
		
		/*un mot est prefixe de lui meme*/
		if(mot1.equals(mot2))
			return true;
		else{
			/*si le mot1 est plus gd que le mot2 il ne peut pas etre son prefixe*/
			if(mot1.length() > mot2.length())
				return false;
			
			else {
				for(int i=0 ; i<mot1.length() ; i++) {
					/*on parcourt lettre par lettre
					 * et si une lettre est differente il n est pas prefixe*/
					if( ! (mot1.charAt(i) == mot2.charAt(i) ) )
						return false;
				}
				/*mot1 est prefixe de mot2*/
				return true;
			}
		}
	}
	
	
	/*si mot1 est prefixe de mot2, retourne le suffixe qui est ds mot2*/
	
	public String suffixe(String mot1, String mot2) {
		
		if(mot1.equals(mot2))
			return mot1;
		else {			
			if(isPrefix(mot1, mot2))
				/*mot 1 prefixe de mot2 donc le suffixe est mot2 sans le prefixe*/
				return mot2.substring(mot1.length());
			else 				
				return "pas de suffixe possible dans 2e mot";			
			
		}
	}
	
	
	
	public boolean rechercher(String mot) {
		
		
		
		
		
		return false;
	}




	public void displayTree() {
		
		if(node == null) System.out.println("No init");
		if(node.isLeaf())
			System.out.println(node.getLabel() );
		else {
		
			System.out.println(node.getLabel() );
			for(RadixTree rt:node.getFils())
				
				rt.displayTree();
				
		}

	}

}
