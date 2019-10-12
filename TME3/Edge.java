package TME3;

public class Edge {
	
	private String label;
	private Node pere;
	private RadixTree fils;
	
	public Edge(String label, Node pere, RadixTree fils) {
		
		this.label = label;
		this.pere = pere;
		this.fils = fils;
	}
	
	
	public Edge(String label) {
		
		this.label = label;
		this.pere = null;
		this.fils = null;
	}
	
	
	
	public String getLabel() { return label; }
	
	public void setLabel(String s) { label = s; }
	
	public Node getPere() { return pere; }
	
	public void setPere(Node p) { pere = p; }
	
	public RadixTree getFils() { return fils; }
	
	public void setFils(RadixTree f) { fils = f; }
	
	
	
	

}
