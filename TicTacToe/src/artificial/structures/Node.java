package artificial.structures;

import java.util.ArrayList;
import java.util.Comparator;

public class Node<G> implements Comparator<Node<G>>{
    private G state;
    private int position;
    private ArrayList<Node<G>> children;
    public Node<G> parent;
    private int utility=-9999;
    private UtilityInformation information;

    public Node(G state, UtilityInformation information) {
        this(state,0,null,new ArrayList<>(),information);
    }
    
    public Node(G state, Node parent, UtilityInformation information) {
        this(state,0,parent,new ArrayList<>(),information);
    }

    public Node(G state, int position, UtilityInformation information){
        this(state,position,null,new ArrayList<>(),information);
    }


    public Node(G state,Node parent, int position, UtilityInformation information){
        this(state,position,parent,new ArrayList<>(),information);
    }

    public Node(G state, int position,Node<G> parent, ArrayList<Node<G>> children, UtilityInformation information) {
        this.state = state;
        this.position = position;
        this.parent=parent;
        this.children = children;
        this.information=information;
        this.utility=this.information.calculateUtility(this);
    }

    public G getState() {
        return state;
    }

    public void setState(G state) {
        this.state = state;
    }

    public int getposition() {
        return position;
    }

    public void setposition(int position) {
        this.position = position;
    }

    public ArrayList<Node<G>> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node<G>> children) {
        this.children = children;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

	@Override
	public int compare(Node<G> o1, Node<G> o2) {
		if(o1.utility== o2.utility)
			return 0;
		if(o1.utility>o2.utility)
			return 1;
		return -1;
	}
	
	public interface UtilityInformation{
		int calculateUtility(Node node);
	}
}
