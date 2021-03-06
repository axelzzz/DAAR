package TME3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class IndexTable {
	
	
	public static void displayMap(ArrayList<Map.Entry<String, Integer>> map) {
		
		for (int i = 0; i < map.size(); i++) 
		      System.out.println(map.get(i).getKey() + ": " + map.get(i).getValue());
	}
	
	
	
	public static boolean contientDejaMot(ArrayList<StringPosition> list, String mot) {
		
		for(StringPosition sp:list)
			if(sp.getWord().equals(mot))
				return true;
		return false;
	}
	
	
	
	
	public static boolean egalPosition(Position p1, Position p2) {
		
		return p1.getNumLigne() == p2.getNumLigne() && p1.getOffset() == p2.getOffset();
	}
	
	
	
	
	
	public static boolean egalStringPosition(StringPosition sp1, StringPosition sp2) {
		
		if(! sp1.getWord().equals(sp2.getWord() ) )
			return false;
		
		if(sp1.getPos().size() != sp2.getPos().size() )
			return false;
		else {
			
			ArrayList<Position> pos1 = sp1.getPos();
			ArrayList<Position> pos2 = sp2.getPos();
			
			for(int i=0 ; i<sp1.getPos().size() ; i++) {
				
				if(! egalPosition(pos1.get(i), pos2.get(i)))
					return false;
			}
			
			
			return true;
		}
	}
	
	
	public static boolean contientDejaStringPosition(ArrayList<StringPosition> list, StringPosition toTest) {
		
		for(StringPosition sp:list)
			if(egalStringPosition(sp, toTest) )
				return true;
		return false;
	}
	
	
	public static ArrayList<StringPosition> supprimerDoublons(ArrayList<StringPosition> list) {
		
		 ArrayList<StringPosition> res = new ArrayList<>();
		 
		 for(StringPosition sp:list) 
			 if(contientDejaStringPosition(res, sp) )
				 continue;
			 else
				 res.add(sp);
		 
		 return res;
	}
	

	
	public static ArrayList<Position> getPositionsOfWordInListOfStringPosition(ArrayList<StringPosition> lsp, String word) {
		
		for(StringPosition sp:lsp)
			if(word.equals(sp.getWord()))
				return sp.getPos();
			
		return null;
	}
	
	
	
	public static ArrayList<String> getBlackList() {
		
		 ArrayList<String> blackList = new ArrayList<>();
		 
		 blackList.add("the");
		 blackList.add("and");
		 blackList.add("has");
		 blackList.add("with");
		 blackList.add("her");
	     blackList.add("from");
	     blackList.add("but");
	     blackList.add("are");
	     blackList.add("was");
		 blackList.add("this");
		 blackList.add("its");
		 blackList.add("you");
		 blackList.add("that");
		 blackList.add("after");
		 blackList.add("for");
		 blackList.add("his");
		 blackList.add("which");
		 blackList.add("have");
		 blackList.add("The");
		 blackList.add("been");
		 blackList.add("their");
		 blackList.add("not");
		 blackList.add("were");
		 blackList.add("had");
		 blackList.add("they");
	
		 return blackList;
	}
	
	/*nbLigneBreak : ligne a laquelle on arrete le traitement
	 * doBreak : si on veut arreter le traitement a une certaine ligne
	 * display : true pour afficher les tables sur la console
	 * book : name of book*/
  public static ArrayList<StringPosition> processIndexTable(int nbLigneBreak, 
		  													boolean doBreak,
		  													boolean display,
		  													String book) throws Exception {
	  
	  //FAIRE STREEEEAAAAAAAMMMMMM
	  /*
	  String fileName = "D:\\\\M2\\\\DAAR\\\\workspace\\\\DAAR\\\\TME3HX\\\\56667-0.txt";

		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(System.out::println);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
*/
	  
	  
	  ArrayList<String> blackList = getBlackList();
	  
	  
	  String fichier1 = book;
	
	  File file = new File("testbeds/"+fichier1);
	  
	  BufferedReader br = new BufferedReader(
			  				new InputStreamReader( new FileInputStream(file), "UTF-8") );
	    
	  ArrayList<String> strList = new ArrayList<String>(); 
	  String readLine = null;
	  int numLigne=1;
	     
	  /*table d'index*/
	  Map<StringOccurrence, ArrayList<Position> > indexTable = new LinkedHashMap<>();   
	    
	    
	  ArrayList<StringPosition> sPositions = new ArrayList<>();
    
    
	  while ((readLine = br.readLine()) != null) {
	      
	    String[] wordsSplit = readLine.split("[^a-zA-Z'-]"); 
	      
	    //if(numLigne == 1) 
	  	//	System.out.println("1er ligne :"+readLine);
	      
	    WholeWordIndexFinder finder = new WholeWordIndexFinder(readLine);
	      
	
	    for (String word : wordsSplit) {
	    	     	  
	    	  /*choisir de mettre en lower case ou pas, attention aux noms*/	
	          //word = word.toLowerCase();	
	    	
	          /*on blackliste les mots de taille < 3*/
	          if (word.length() > 2 && !blackList.contains(word)) {         	
	        	
	              /*on calcule les index dans la ligne ou se trouve le mot courant*/	
		          List<IndexWrapper> indexes = finder.findIndexesForKeyword(word);
		          //finder.displayIndexes(indexes, word);
		          ArrayList<Integer> indx = finder.indexWrapperToIndexList(indexes);
		          
		          /*on definit les positions (num L, offset) du mot*/
		          StringPosition sp = new StringPosition(word);
		          
		          for(Integer i:indx)         	 
		        	  sp.addPosition( new Position(numLigne, i.intValue()) );          
		          
		          /*enregistrer dans une liste la pos du mot -> (ligne + offset)*/
		          sPositions.add(sp);
		     
		         
		          strList.add(word);
	            
	         }    	 
	     }
	      
	      if(doBreak)
	    	  if(numLigne == nbLigneBreak) break;
	      numLigne++;
	     
	  }
    
    /*
    for(StringPosition sp:sPositions)
    	System.out.println(sp.displayWordPos());
    */
    
    
    //System.out.println("size sposition before supp doublon "+sPositions.size());
    sPositions = supprimerDoublons(sPositions);
    //System.out.println("size sposition after supp doublon "+sPositions.size());
    
    
    ArrayList<StringPosition> finalListPositions = new ArrayList<>();
    
    for(StringPosition sp:sPositions) {
    	
    	/*pas la peine de calculer pour une autre occurrence de mot si on l'a deja fait une fois*/
    	if(contientDejaMot(finalListPositions, sp.getWord())) 
    		//System.out.println("contien deja mot "+sp.getWord());
    		continue;
    	
    	else     		
    		finalListPositions.add( sp.allPosOfString(sPositions) );
    			   	
    }
    
    /*
    if(display) {
    	for(StringPosition sp:finalListPositions)
        	System.out.println(sp.displayWordPos());
    }
    */
    
    
    //System.out.println("il y a "+numLigne+" lignes");
    br.close();


    Map<String, Integer> wordsCount = new TreeMap<String, Integer>(); // key = word, value = freqence
    for (String li : strList) {
      if (wordsCount.get(li) != null) {
        wordsCount.put(li, wordsCount.get(li) + 1);
      } else {
        wordsCount.put(li, 1);
      }
    }

    /*map qui contient les mots tries par ordre d occurrence*/
    ArrayList<Map.Entry<String, Integer>> mapSortedWords = SortMap(wordsCount); 
    
    /*on cree un fichier pour la table d index avec nb occ et un pour index table sans occ */
    File fileIndexTableOcc = new File("fileIndexTableOcc"+fichier1); 
    fileIndexTableOcc.createNewFile(); 
    BufferedWriter out = new BufferedWriter(new FileWriter(fileIndexTableOcc));
    
    File fileIndexTable = new File("fileIndexTable"+fichier1); 
    fileIndexTable.createNewFile(); 
    BufferedWriter out2 = new BufferedWriter(new FileWriter(fileIndexTable));
    
    System.out.println("<----------------------------------------->");
    for (int i = 0 ; i < mapSortedWords.size() ; i++) {
    	
    	Entry<String, Integer> e = mapSortedWords.get(i);
    	    	
    	/*on remplit la table d index*/
    	StringOccurrence so = new StringOccurrence(  e.getKey(), e.getValue().intValue() );
    	ArrayList<Position> lp = getPositionsOfWordInListOfStringPosition(finalListPositions, e.getKey() );
    	indexTable.put(so,  lp);
    	
    	/*on ecrit dans un fichier*/
    	out.write(" \""+so.getWord()+"\" "+so.getNbOcc()+" ");
    	out2.write(" \""+so.getWord()+"\"");
    	
    	if(display)
    		System.out.print(" \""+so.getWord()+"\" "+so.getNbOcc()+" ");
    	 	
        for(Position p:lp) {
        	
        	out.write(p.displayPosition());
        	out2.write(p.displayPosition());
        	
        	if(display)
        		System.out.print( p.displayPosition() );        	
        }
        
        out.write("\n");
        out2.write("\n");
        
        if(display)
        	System.out.println();
    }

   
    
    /*for (int i = 0; (i < mapSortedWords.size()) && (mapSortedWords.get(i).getValue()<10) ; i++) {
      out.write(mapSortedWords.get(i).getKey()+"\r\n");
    }*/
    out.flush(); 
    out.close();
    
    out2.flush(); 
    out2.close();

    return finalListPositions;

  }

  public static ArrayList<Map.Entry<String, Integer>> SortMap(Map<String, Integer> oldmap) {

    ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldmap.entrySet());

    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
      @Override
      public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
        return o1.getValue() - o2.getValue(); // ordre croissante
      }
    });

    return list;
  }
  
  
  
  
  public static void main(String[] args) {
	  
	  try {
		  System.out.println("test");
		ArrayList<StringPosition> finalLsp = processIndexTable(100, true, true, "56667-0.txt");
	} catch (Exception e) {
		
		e.printStackTrace();
	}
  }


}
