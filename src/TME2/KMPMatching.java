package TME2;

public class KMPMatching {
	
	
	/*retourne vrai si le facteur est trouve dans le texte*/
	public static boolean match(char[] facteur,								
								char[] texte,
								 int[] retenue) {
		
		
		int i=0; int j=0;
		
		while(i < texte.length) {
			
			if(j == facteur.length) 
				return true;
			if(texte[i] == facteur[j]) 
				{i++; j++;}
			else {
				if(retenue[j] == -1) 
					{i++; j=0;}
				else 
					j = retenue[j];
			}
		}
		
		if(j == facteur.length)
			return true;
		else
			return false;		
		
	}
	
	
	/*calcule le tableau de retenue*/
	public int[] prefixTableForKmp(char[] facteur) {
		
		int[] prefixTable = new int[facteur.length];
		prefixTable[0] = -1;
		
		char head = facteur[0];
		
		for(int i=1 ; i<facteur.length ; i++) {
			if(facteur[i] == head)
				prefixTable[i] = -1;
			else {
				
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		return prefixTable;
		
	}
	
	
	
	public static void main(String[] args) {
		
		
		
		
	}

}
