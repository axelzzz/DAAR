package TME3;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class PatriciaTrie {
	List<Case> PAT;
	
	
	
	// Primitive PAT vide
	public PatriciaTrie(){
		PAT = new ArrayList<Case>(128);
		
		for(int i=0; i<128; i++) {
			PAT.add(new Case(null));
		}
	}


	
	
	//Primitive : Recuperer la case dont la clef commence par c
	// Comme nous avons choisi comme caractere de fin de chaine '^', dont le code ascii est 94
	public Case getCase(char c) {
		int ascii = (int) c;
		
		if(ascii < 94) {
			return PAT.get(ascii+1);
		}
		
		if(ascii == 94) {
			return PAT.get(0);
		}else {
			return PAT.get(ascii);
		}
	}
	
	
	
	
	// Primitive : Recuperer l'index selon le caractere
	public int getIndexFromChar(char c) {
		int ascii = (int) c;
		
		if(ascii < 94) {
			return ascii+1;
		}
		if(ascii == 94) {
			return 0;
		}else {
			return ascii;
		}
	}
	
	
	
	
	//Primitive : Affectation de la case d'index "index" avec c
	public void setCase(int index, Case c) {
		PAT.set(index, c);
	}
	
	
	
	
	// Primitive : Recuperer l'index du prefixe commun
	public int getIndexPrefixeCommun(String mot1, String mot2) {
		int beginIndex=0;
		//on compare les caracteres des deux mots
		//si ils ont des caracteres en commun (alors ils ont un prefixe commun)
		//
		for(int i=0; i<Math.min(mot1.length(), mot2.length()); i++) {
			if(mot1.charAt(i) == mot2.charAt(i)) {
				beginIndex++;
			}else {
				break;
			}
		}
		return beginIndex;
	}
	
	
	
	
	public boolean recherchePAT(String mot) {
		
		// Le mot est vide
		if(mot.length()==0){
			
			// et il s'agit d'une fin de mot qui existe dans l arbre
			if(! getCase('^').estVide()) {
				return true;
			}else { // et il ne s'agit pas d'une fin de mot(?? existe pas dans l arbre)
				return false;
			}
		}
	
		Case laCase = getCase(mot.charAt(0));
		String tmp = new String(mot);
		tmp+="^"; //
		
		
		// Le mot n'est pas vide et la case contenant son premier caractere est vide
		if(laCase.estVide()) {
			return false;
		}
		
		// Si le mot est completement equivalent a la clef de la case.
		if(laCase.getKey().compareTo(tmp)==0) {
			return true;
		}else {
			int beginIndex = getIndexPrefixeCommun(mot, laCase.getKey());
			
			if(laCase.getFils()==null) {
				return false;
			}
			return laCase.getFils().recherchePAT(mot.substring(beginIndex));
		}
	}
	
	
	
	
	// Primitive : insertion
	public void insertionPAT(String mot) {
		if(contientCaractFin(mot)) {
			System.out.println("Mot contenant le caractere de fin de mot : "+mot);
			return;
		}
		
		insertionPATpere(mot, null);
	}
	
	
	
	
	private void insertionPATpere(String mot, Case pere) {
		
		
		// Si le mot est vide, on ajoute une fin de mot
		if(mot.length() == 0) {
			if(getCaseIndex(0).estVide())
				setCase(0, new Case("^", null, pere));
			return;
		}
		
		Case laCase = getCase(mot.charAt(0));
		
		
		// Si aucun mot deja existant ne commence pareil, on l'ajoute en entier
		if(laCase.estVide()) {
			
			laCase = new Case(mot+'^', null, pere);
			setCase(getIndexFromChar(mot.charAt(0)), laCase);
			
		}else {
			int beginIndex = getIndexPrefixeCommun(mot, laCase.getKey());
			String motSuffixe = mot.substring(beginIndex); 

			
			// Si le mot que l'on souhaite inserer est le prefixe d'un mot deja existant, on indique la fin d'un mot
			if(beginIndex == laCase.getKey().length()) {
				laCase.getFils().insertionPATpere(motSuffixe, laCase);
			}else {		
				// Sinon, on lui creer un fils si besoin, et on met a jour le PAT.
				String motPrefixe = mot.substring(0, beginIndex); 
				String laCaseSuffixe = laCase.getKey().substring(beginIndex, laCase.getKey().length()); 
				
				
				// Le cas ou on doit casser la clef du pere, car le prefixe n'est pas le meme que celui du mot que l'on souhaite ajouter
				laCase.setKey(motPrefixe);
				setCase(getIndexFromChar(mot.charAt(0)), laCase);
				
				if (laCase.getFils() == null) {
					laCase.setFils(new PatriciaTrie());
					laCase.getFils().getCase(laCaseSuffixe.charAt(0)).setKey(laCaseSuffixe);
					laCase.getFils().getCase(laCaseSuffixe.charAt(0)).setPere(laCase);
					laCase.getFils().insertionPATpere(motSuffixe, laCase);

				}else{
					PatriciaTrie tmpFils = laCase.getFils();
					
					laCase.setFils(new PatriciaTrie());
					
					laCase.getFils().getCase(laCaseSuffixe.charAt(0)).setKey(laCaseSuffixe);
					laCase.getFils().getCase(laCaseSuffixe.charAt(0)).setPere(laCase);
					
					//maj du pere de tmpFils
					for(int i=0; i<128; i++) {
						if(! tmpFils.getCaseIndex(i).estVide()) {
							tmpFils.getCaseIndex(i).setPere(laCase.getFils().getCase(laCaseSuffixe.charAt(0)));
						}
					}
					
					laCase.getFils().getCase(laCaseSuffixe.charAt(0)).setFils(tmpFils);

					
					laCase.getFils().insertionPATpere(motSuffixe, laCase);

				}
				
			}
				

		}
	}
	
	
	
	
	public void suppression(String mot) {
		suppressionPere(mot, null);
	}
	
	
	
	
	private void suppressionPere(String mot, Case pere) {

		if(! recherchePAT(mot)) {
			System.out.println("Mot n'existant pas dans le dictionnaire : "+mot);
			return;
		}
		
		// Le mot est vide
		if(mot.length()==0){
			
			// et il s'agit d'une fin de mot
			if(! getCase('^').estVide()) {
				setCase(0, new Case(pere));
				
				if(compteFrere()==1 ) {
					Case monFrere = getFrere(); //premier frere
					if(monFrere.getPere() == null) {
						return;
					}
					String monFrereKey = monFrere.getKey();
					String pereKey = monFrere.getPere().getKey();
					monFrere.getPere().setKey(pereKey+monFrereKey);
					monFrere.getPere().setFils(null);
					
				}
				
			}
			return;
		}
	
		Case laCase = getCase(mot.charAt(0));
		String tmp = new String(mot);
		tmp+="^";
		

		// Si le mot est completement equivalent a la clef de la case.
		if(laCase.getKey().compareTo(tmp)==0) {
			setCase(getIndexFromChar(mot.charAt(0)), new Case(pere));
			
			if(compteFrere()==1) {
				Case monFrere = getFrere();
				if(monFrere.getPere() == null) {
					return;
				}
				String monFrereKey = monFrere.getKey();
				String pereKey = monFrere.getPere().getKey();
				monFrere.getPere().setKey(pereKey+monFrereKey);
				monFrere.getPere().setFils(null);
			}
			return;
			
		}else {
			int beginIndex = getIndexPrefixeCommun(mot, laCase.getKey());
			
			laCase.getFils().suppressionPere(mot.substring(beginIndex), laCase);
		}
	}
	
	
	
	
	
	//Primitive : Compte le nombre de frere d'une case
	public int compteFrere() {
		int nb=0;
		for(int i=0; i<128; i++) {
			if(! PAT.get(i).estVide()) {
				nb++;
			}
		}
		return nb;
	}
	
	
	
	
	
	//Primitive : Recupere le premier frere d'une case
	public Case getFrere() {
		for(int i=0; i<128; i++) {
			if(! PAT.get(i).estVide()) {
				return PAT.get(i);
			}
		}
		return null;
	}
	
	
	
	
	
	//Primitive : Check si le mot ne contient pas le caractere sp�cial de fin de mot
	public boolean contientCaractFin(String mot) {
		if(mot.contains("^")) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	public int comptageMots(){
		int nbMots=0;
		
		if(! PAT.get(0).estVide()) {
			if(PAT.get(0).estFinMot()) {
				nbMots++;
			}
		}
		for(int i=1; i<128; i++){
			if(PAT.get(i).getKey().contains("^")) {
				nbMots++; //ou if (c.estFinMot())
			}
			if(PAT.get(i).getFils()!=null){
				nbMots+=PAT.get(i).getFils().comptageMots();
			}
			
		}		
		return nbMots;
	}
	
	
	
	
	public int hauteur(){
		
		int hauteurA=0;
		int hauteurMaxFils=0;
		
		for(Case c:PAT){
			if(c.getFils()!=null){
				int hTmp = c.getFils().hauteur();
				if(hTmp > hauteurMaxFils){
					hauteurMaxFils = hTmp;
				}
			}
		}
		hauteurA = 1+hauteurMaxFils;
		
		return hauteurA;
	}
	
	
	
	
	public int comptageNil(){
		
		int nbNils=0;
		
		for(Case c:PAT){
			if(c.getFils()!=null){
				nbNils+=c.getFils().comptageNil();
			}else{
				nbNils++;
			}
		}		
		return nbNils;
	}
	
	
	
	
	public List<String> listeMots(){
		ArrayList<String> L = new ArrayList<>();
		listeMotsAvecPrefixe("", L);
		return L;
	}
	
	
	
	
	private void listeMotsAvecPrefixe(String prefixe, List<String> L){
		
		for(int i=0; i<128; i++){
			
			if(this.getCaseIndex(i).estVide()){
				continue;
			}
			if(this.getCaseIndex(i).getKey().contains("^")){
				L.add(prefixe+this.getCaseIndex(i).getKeySansCaracFinMot());
			}else{
				String newPrefixe = prefixe + this.getCaseIndex(i).getKey();
				if(this.getCaseIndex(i).getFils() != null){
					this.getCaseIndex(i).getFils().listeMotsAvecPrefixe(newPrefixe, L);
				}
			}
		}
	}
	
	
	
	
	
	public double profondeurMoyene(){
		
		if(nbFeuilles()==0){
			return 0;
		}else{
			return profondeurTot(0)/nbFeuilles();
		}
		
	}
	
	
	
	
	private int nbFeuilles(){
		int nbFeuilles=0;
		boolean estFeuille = true;
		for(Case c : PAT){
			if(c.getFils() != null){
				estFeuille = false;
				nbFeuilles += c.getFils().nbFeuilles();
			}
		}
		
		if(estFeuille){
			return 1+nbFeuilles;
		}else{
			return nbFeuilles;
		}
	}
	
	
	
	
	private double profondeurTot(int level){
		int tot = 0;
		boolean estFeuille =true;
		for(Case c: PAT){
			if(c.getFils()!=null){
				estFeuille = false;
				tot += c.getFils().profondeurTot(level+1);
			}
		}
		if(estFeuille){
			return level;
		}else{
			return tot;
		}
	}
	
	
	
	
	public int prefixe(String pref){
		
		// Le mot est vide
		if(pref.length()==0){
			
			return comptageMots();
		}
	
		Case laCase = getCase(pref.charAt(0));
		
		
		// Le mot n'est pas vide et la case contenant son premier caractere est vide
		if(laCase.estVide()) {
			return 0;
		}
		
		// Si le mot est completement equivalent a la clef de la case.
		if(laCase.getKey().compareTo(pref)==0) {

			return laCase.getFils().prefixe("");		
		}else {
			int beginIndex = getIndexPrefixeCommun(pref, laCase.getKey());
	
			if(pref.length() < laCase.getKeySansCaracFinMot().length()){
				if(beginIndex < (pref.length()-1)){
					return 0;
				}else{
					if(laCase.getFils()!= null){
						return laCase.getFils().comptageMots();
					}else{
						return 1;
					}
				}
			}else{
				if(laCase.getFils()!= null){
					return laCase.getFils().prefixe(pref.substring(beginIndex));
				}else{
					return 0;
				}
			}
		}
	}
	
	
	
	
	
	// Primitive : "Affichage" du PAT
	public String toString() {
		String s;
		if(getFrere()== null) {
			return "";
		}
		if(getFrere().getPere()==null) {
			s=" : ";
		}else {
			s=getFrere().getPere().getKey()+" : ";
		}
		for(int i=0; i<128; i++) {
			s+=PAT.get(i).getKey()+"\t";
		}
		
		s+="\n";
		
		for(int i=0; i<128; i++) {
			if(PAT.get(i).getFils()!= null) 
				s+=PAT.get(i).getFils().toString()+"\n";			
		}
		return s;
		
	}
	
	
	
	
	// Primitive : Recupere la case d'index i
	public Case getCaseIndex(int i) {
		return PAT.get(i);
	}
	
	
	
	
	
	
	private ArrayList<Case> getMesFreres(){
		ArrayList<Case> res = new ArrayList<>();
		
		for(int i=0; i<128 ;i++) {
			if(! getCaseIndex(i).estVide()) {
				if(getCaseIndex(i).getFils() != null) {
					Case newCase = getCaseIndex(i);
					if(! newCase.getFils().getCaseIndex(0).estVide()) {
						newCase.setKey(newCase.getKey()+"^");
						newCase.getFils().setCase(0, new Case(newCase));
					}
					res.add(getCaseIndex(i));
				}else {
					res.add(getCaseIndex(i));
				}
			}
		}
		return res;
	}
	
	
	
	
	

	public void setPereAMesFils(Case pere) {
		for(int i=0; i<128 ;i++) {
			getCaseIndex(i).setPere(pere);
		}
	}
}
