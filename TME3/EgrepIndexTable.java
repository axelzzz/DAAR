package TME3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class EgrepIndexTable {

	private static String toSearch;
	private static String whichBook;
	
	
	
	public static boolean contientDejaInteger(ArrayList<Integer> list, Integer toTest) {
		
		for(Integer i:list)
			if(i.equals(toTest) )
				return true;
		return false;
	}



	
	public static ArrayList<Integer> supprimerDoublons(ArrayList<Integer> list) {
		
		 ArrayList<Integer> res = new ArrayList<>();
		 
		 for(Integer i:list) 
			 if(contientDejaInteger(res, i) )
				 continue;
			 else
				 res.add(i);
		 
		 return res;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
	    System.out.print("  >> Please enter a word to search: ");
	    toSearch = scanner.next();
	      
	    scanner = new Scanner(System.in);
	    System.out.print("  >> Please enter the book to search in (49345 or 56667): ");
	    whichBook = scanner.next();
	      
	     
		String indexTable = "fileIndexTable"+whichBook+"-0.txt";
		String book = whichBook+"-0.txt";
		
		File fileIndexTable = new File(indexTable);
		File fileBook = new File(book);
				
		
		String readLine = null;
		
			
		
		/*
		BufferedReader brIT = new BufferedReader(
					new InputStreamReader( new FileInputStream(fileIndexTable), "UTF-8") );
		*/
		
				
		
		
		BufferedReader brB = new BufferedReader(
					new InputStreamReader( new FileInputStream(fileBook), "UTF-8") );
		 
			
		System.out.println("Processing "+indexTable+"...");
		
		long startTime1 = System.nanoTime();
		
	    //ArrayList<StringPosition> iTable = IndexTable.processIndexTable(0, false, false, book);	
	    ArrayList<StringPosition> iTable = IndexTable.processIndexTable(100, false, false, book);	
	    
	    long endTime1 = System.nanoTime();
	    long duration = endTime1-startTime1;
	    duration = duration / 1000000;
	    System.out.println("Time to process the index table : "+duration+"ms");

	    ArrayList<Integer> lignsToDisplay = new ArrayList<>();
		
	    StringPosition wordToSearch = new StringPosition(toSearch);
	    ArrayList<Position> posOfWordToSearch = wordToSearch.getPos();
	    
	   
	    
	    System.out.println("Checking "+indexTable+"...");
	    
	    long startTime2 = System.nanoTime();
		
	    //on cherche le mot souhaite dans l index table
		for(StringPosition sp:iTable) {
			
			if(sp.getWord().equals(toSearch)) {
				
				System.out.println(sp.displayWordPos());
				//lorsqu on le trouve, on recense toutes les lignes qui l utilisent
				for(Position pos:sp.getPos()) {
					
					posOfWordToSearch.add(pos);
					//on aura des doublons, on les supprime apres
					lignsToDisplay.add(Integer.valueOf(pos.getNumLigne()));					
				}
				
			}
			
			//attention, un mot peut contenir le mot souhaite il faut recree une position avec bon index
			
			if(sp.getWord().contains(toSearch)) {
				
				//rajouter a l index le bon nombre, exemple si le mot a chercher est "test" dans "attested" 
				//il faut ajouter 2 a l'index
				for(Position pos:sp.getPos()) {
					int toAdd = sp.getWord().indexOf(toSearch);
					Position newPos = new Position(pos.getNumLigne(), pos.getOffset()+toAdd);
					posOfWordToSearch.add(newPos);
					//on aura des doublons, on les supprime apres
					lignsToDisplay.add(Integer.valueOf(pos.getNumLigne()));					
				}
			}
			
			
			
			
		}
		
		//System.out.println("size avant supp "+lignsToDisplay.size());
		lignsToDisplay = supprimerDoublons(lignsToDisplay);
		
		long endTime2 = System.nanoTime();
	    long duration2 = endTime2-startTime2;
	    duration2 = duration2 / 1000000;
	    System.out.println("Time to check and get the result : "+duration2+"ms");
	    
		//System.out.println("size apres supp "+lignsToDisplay.size());
		System.out.println("<----->\nResult :\n");
		
		int numLigne = 1;
		
		while ((readLine = brB.readLine()) != null) {
			if(lignsToDisplay.contains(Integer.valueOf(numLigne)))
				System.out.println(readLine);
			
			numLigne++;
		}
		
	}
}
