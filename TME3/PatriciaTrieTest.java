package TME3;

public class PatriciaTrieTest {
	public static void main(String []args) {
		PatriciaTrie myPAT1 = new PatriciaTrie();
		PatriciaTrie myPAT2 = new PatriciaTrie();
		PatriciaTrie myPAT = new PatriciaTrie();

		

//		myPAT1.insertionPAT("AA");
//		myPAT1.insertionPAT("AC");
//		myPAT1.insertionPAT("TC");	
//		myPAT1.insertionPAT("TCA");/
		
		
//		myPAT2.insertionPAT("ACB");
//		myPAT2.insertionPAT("ACBA");		
//		myPAT2.insertionPAT("TCAB");

//		res.insertionPAT("AA");
//		res.insertionPAT("AC");
//		res.insertionPAT("TC");
//		res.insertionPAT("TCA");
//		res.insertionPAT("ACB");
//		res.insertionPAT("ACBA");
//		res.insertionPAT("TCAB");
		
//		System.out.println(myPAT.listeMots());
//		System.out.println(myPAT.profondeurMoyene());
		
		myPAT1.insertionPAT("A");
		myPAT1.insertionPAT("quel");
		myPAT1.insertionPAT("genial");
		myPAT1.insertionPAT("professeur");
		myPAT1.insertionPAT("de");
		myPAT1.insertionPAT("dactylographie");
		myPAT1.insertionPAT("sommes");
		myPAT1.insertionPAT("nous");
		myPAT1.insertionPAT("redevables");
		myPAT1.insertionPAT("de");
		myPAT1.insertionPAT("la");
		myPAT1.insertionPAT("superbe");
		myPAT1.insertionPAT("phrase");
		myPAT1.insertionPAT("ci");
		myPAT1.insertionPAT("dessous,");
	
		
		myPAT2.insertionPAT("un");
		myPAT2.insertionPAT("modele");
		myPAT2.insertionPAT("du");
		myPAT2.insertionPAT("genre,");
		myPAT2.insertionPAT("que");
		myPAT2.insertionPAT("toute");
		myPAT2.insertionPAT("dactylo");
		myPAT2.insertionPAT("connait");
		myPAT2.insertionPAT("par");
		myPAT2.insertionPAT("coeur");
		myPAT2.insertionPAT("puisque,");
		myPAT2.insertionPAT("elle");
		myPAT2.insertionPAT("fait");
		myPAT2.insertionPAT("appel");
		myPAT2.insertionPAT("a");
		myPAT2.insertionPAT("chacune");
		myPAT2.insertionPAT("des");
		myPAT2.insertionPAT("touches");
		myPAT2.insertionPAT("du");
		myPAT2.insertionPAT("clavier");
		myPAT2.insertionPAT("de");
		myPAT2.insertionPAT("la");
		myPAT2.insertionPAT("machine");
		myPAT2.insertionPAT("a");
		myPAT2.insertionPAT("ecrire");
		myPAT2.insertionPAT("?");


		
		myPAT.insertionPAT("A");
		myPAT.insertionPAT("quel");
		myPAT.insertionPAT("genial");
		myPAT.insertionPAT("professeur");
		myPAT.insertionPAT("de");
		myPAT.insertionPAT("dactylographie");
		myPAT.insertionPAT("sommes");
		myPAT.insertionPAT("nous");
		myPAT.insertionPAT("redevables");
		myPAT.insertionPAT("de");
		myPAT.insertionPAT("la");
		myPAT.insertionPAT("superbe");
		myPAT.insertionPAT("phrase");
		myPAT.insertionPAT("ci");
		myPAT.insertionPAT("dessous,");
		myPAT.insertionPAT("un");
		myPAT.insertionPAT("modele");
		myPAT.insertionPAT("du");
		myPAT.insertionPAT("genre,");
		myPAT.insertionPAT("que");
		myPAT.insertionPAT("toute");
		myPAT.insertionPAT("dactylo");
		myPAT.insertionPAT("connait");
		myPAT.insertionPAT("par");
		myPAT.insertionPAT("coeur");
		myPAT.insertionPAT("puisque,");
		myPAT.insertionPAT("elle");
		myPAT.insertionPAT("fait");
		myPAT.insertionPAT("appel");
		myPAT.insertionPAT("a");
		myPAT.insertionPAT("chacune");
		myPAT.insertionPAT("des");
		myPAT.insertionPAT("touches");
		myPAT.insertionPAT("du");
		myPAT.insertionPAT("clavier");
		myPAT.insertionPAT("de");
		myPAT.insertionPAT("la");
		myPAT.insertionPAT("machine");
		myPAT.insertionPAT("a");
		myPAT.insertionPAT("ecrire");
		myPAT.insertionPAT("?");
		
		System.out.println(myPAT1);
		System.out.println("liste des mots : "+myPAT1.listeMots());
		System.out.println("--------------------------------------\n");
		System.out.println(myPAT2);
		System.out.println("liste des mots : "+myPAT2.listeMots());

		System.out.println("--------------------------------------\nRES\n");
		System.out.println(myPAT);
		System.out.println(myPAT.listeMots());
		System.out.println("liste des mots : "+myPAT.comptageMots());
//		System.out.println("nbMots sans doublons : "+myPAT.comptageMots());
//		System.out.println("liste des mots : "+myPAT.listeMots());
//		System.out.println("hauteur arbre : "+myPAT.hauteur());
//		System.out.println("recherche: " +myPAT.recherchePAT("dessous,"));
//		System.out.println("Supression dessous");
//		
////		myPAT.suppression("dessous,");
//		System.out.println("recherche apres supreesion: " +myPAT.recherchePAT("dessous,"));
//		
//		System.out.println("Comptage Nil : "+myPAT.comptageNil());
//				
//		System.out.println("Comtpage prefixe : "+myPAT.prefixe("d"));
		
		
	}
}
