package TME3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReaderWriter {
	
	
	
	public static void loadMap(String txt, int ligne, Map<String, List<Couple>> map) {
		
		
		
		List<Couple> llCouples = new ArrayList<>();
		
		String currentWord = "";
		
		for(int i=0 ; i<txt.length() ; i++) {
			char c = txt.charAt(i);
			
			
			if(c == ' ') {
				
				Couple couple = new Couple(Integer.valueOf(ligne), Integer.valueOf(i-currentWord.length() ) );
				//llCouples.add(couple);
				//blacklist taille <= 2
				if(currentWord.length() > 2) {
					
					if(map.containsKey(currentWord)) {
						map.get(currentWord).add(couple);
					}
					llCouples.add(couple);
					map.put(currentWord, llCouples);
				}
				
				//System.out.println("mot trouvé : \""+currentWord+"\" à la ligne "+ligne);
				currentWord="";
			}
			else
				currentWord+=c;
		}
		
		
		
		
		
	}
	
	
	public static Map<String, List<Couple>> sortOccurrencesCroissant(Map<String, List<Couple>> toSort) {
		
		Map<String, List<Couple>> res = new LinkedHashMap<>();
		
		
		
		return res;
	}

	
	public static void afficherMap(Map<String, List<Couple>> toDisplay) {
		
		Iterator it = toDisplay.entrySet().iterator();
			  
		while (it.hasNext()) {
			Map.Entry<String, List<Couple>> pair = (Entry<String, List<Couple>>) it.next();
		
			System.out.println("Mot ajouté : "+pair.getKey()+" nb occur : "+pair.getValue().size());
		}
		  
	}
	
	public static void main(String[] args) throws Exception {
		
		
		Map<String, List<Couple>> map = new LinkedHashMap<>();
		
		
		File file = new File("/root/bigFatWorkspace/M2/DAAR/eclipse_workspace/DAAR/TME3/49345.txt"); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String st;
		int ligne = 1;
		while ((st = br.readLine()) != null) {
			if(st == "\n")
				continue;
			else {
				
				//System.out.println("avant replace "+st);
				/*ATTENTION AUX ACRONYMES, EX : U.S. United States,
				* AUX ADRESSES MAILS BIZARRES */
				st = st.replaceAll("[^A-Za-z-]+", " ");
				//System.out.println("apres replace "+st);
				loadMap(st, ligne, map);
				ligne++;
			}
		  
		}
		
		
		System.out.println("taille map "+map.size());

		afficherMap(map);
		
		
		
	}

}
