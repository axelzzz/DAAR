package TME3;

public class Case {
	private String key;
	private PatriciaTrie fils;
	private Case pere;
	
	public Case(String key, PatriciaTrie fils, Case pere) {
		this.key = key;
		this.fils = fils;
		this.pere=pere;
	}
	
	//Primitive : Case vide
	public Case(Case pere) {
		this("", null, pere);
	}
	
	// Primitive : recuperer le fils
	public PatriciaTrie getFils() {
		return fils;
	}
	
	// Primitive : recupere la cl�e
	public String getKey() {
		return key;
	}
	
	public String getKeySansCaracFinMot(){
		String s = getKey();
		if(s.contains("^")){
			return s.substring(0, s.length()-1);
		}
		return s;
	}
	
	// Primitive : savoir si c'est un mot
	public boolean estFinMot() {
		
		if(key.charAt(key.length()-1)== '^') {
			return true;
		}
		
		if(fils.getCase('^')!= null) {
			return true;
		}
		return false;
	}
	
	//Primitive : Case vide
	public boolean estVide() {
		return key.isEmpty();
	}
	
	//Primitive : setteur d'une cl�e pour une case
	public void setKey(String key) {
		this.key=key;
	}
	
	//Primitive : Setteur d'un fils pour une case
	public void setFils(PatriciaTrie PAT) {
		fils=PAT;
	}
	
	//Primitive : getteur du pere d'une case
	public Case getPere() {
		return pere;
	}
	
	//Primitive : setteur du pere d'une case
	public void setPere(Case pere) {
		this.pere=pere;
	}
	
	public boolean estFinMotSansFils() {
		if(key.charAt(key.length()-1)== '^') {
			return true;
		}
		
		return false;
	}
	
}
