package artificial.botfactory;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.company.Board;

import artificial.structures.Node;

public class Charlie {

    public Behavior mBehavior;

    public Charlie(Behavior behavior){
        this.mBehavior=behavior;
    }

    public void minimaX(Node state){
    	Node decision= maxValue(state);
    	mBehavior.makeMovement(decision);
    	
    }
    private Node maxValue(Node state) {
    	int utility = state.getUtility();//Board.win(state);
    	if(utility!=9999) {
    		//Juego ganado
    		return state;
    	}
    	Node maxNode=state;
    	Node auxNode;
    	mBehavior.generateChildStates(state);//Generamos hijos
    	for(Node stat : (ArrayList<Node>)state.getChildren()) {
    		auxNode= minValue(stat);
    		maxNode = (maxNode.compare(maxNode, stat)<0? stat: maxNode);
    	}
    	return maxNode;
    }

    private Node minValue(Node state) {
    	int utility = state.getUtility();//Board.win(state);
    	if(utility!=9999) {
    		//Juego ganado
    		return state;
    	}
    	Node minNode=state;
    	Node auxNode;
    	mBehavior.generateChildStates(state);
    	for(Node stat : (ArrayList<Node>)state.getChildren()) {
    		auxNode= maxValue(stat);
    		minNode = (minNode.compare(minNode, stat)>0? stat: minNode);
    	}
    	return minNode;
    }
    
    public interface Behavior{
        public void makeMovement(Node decision);
        public void generateChildStates(Node parent);
        public int checkWinState();
    }
}
