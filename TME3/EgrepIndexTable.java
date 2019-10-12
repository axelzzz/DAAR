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
		
		System.out.println("Processing "+indexTable+"...");
		
		
		String readLine = null;
		
		/*
		BufferedReader brIT = new BufferedReader(
					new InputStreamReader( new FileInputStream(fileIndexTable), "UTF-8") );
		*/
		
		BufferedReader brB = new BufferedReader(
					new InputStreamReader( new FileInputStream(fileBook), "UTF-8") );
		 
			
	    ArrayList<StringPosition> iTable = IndexTable.processIndexTable(0, false, false, book);	
	    ArrayList<Integer> lignsToDisplay = new ArrayList<>();
		
	    System.out.println("Checking "+indexTable+"...");
	    
	    /*on cherche le mot souhaite dans l index table*/
		for(StringPosition sp:iTable) {
			if(sp.getWord().equals(toSearch)) {
				
				System.out.println(sp.displayWordPos());
				/*lorsqu on le trouve, on recense toutes les lignes qui l utilisent*/
				for(Position pos:sp.getPos()) {
					/*on aura des doublons, on les supprime apres*/
					lignsToDisplay.add(Integer.valueOf(pos.getNumLigne()));					
				}
				
			}
		}
		
		//System.out.println("size avant supp "+lignsToDisplay.size());
		lignsToDisplay = supprimerDoublons(lignsToDisplay);
		
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
