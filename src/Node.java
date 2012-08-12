
public class Node {
	public String value;
	public Node nextSibling;
	public Node firstChild;
	
	public Node(String c) {
		this.value = c;
		this.nextSibling = null;
		this.firstChild = null;
	}

}
