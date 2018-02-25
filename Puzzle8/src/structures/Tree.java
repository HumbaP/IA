package structures;


import java.util.*;

public class Tree {
    private Node root;
    private String solve;



    public Tree(String firstState, String finalState){
        this.root=new Node(firstState);
        this.solve=finalState;
    }

    public Tree(Node root, String finalState){
        this.root=root;
        this.solve=finalState;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getSolve() {
        return solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;
    }

    //Primero en anchura (NORMAL)
    public void solveWithFIFO() throws InterruptedException {
        Collection<String>  visitedStates= new ArrayList<>();
        Queue<Node> statesNotVisited=new LinkedList();
        Node aux= this.root;
        System.out.println("Before While");
        while(!aux.getData().equals(solve)){
            if(aux.getDepth()==30)
            {
                System.out.println("MAX DEPTH REACHED!!!");
                return;
            }
            visitedStates.add(aux.getData());
            System.out.println("Searching....");
            Collection<String> children= aux.generateChildren();
            for (String status: children){
                if(!visitedStates.contains(status)){
                    //Create new node
                    Node nChild=new Node(status,aux);
                    statesNotVisited.add(nChild);
                }
            }
            aux=statesNotVisited.poll();
        }
        //solveWithFIFO(statesNotVisited,queue.dequeue(),0);
        System.out.println("RESOLVED IN "+aux.getDepth()+" MOVES!!!");
        printSolution(aux);
    }


    //Primero en profundidad (NORMAL)
    public void solveWithLIFO(){
        Collection<String>  visitedStates= new ArrayList<>();
        Stack<Node> statesNotVisited= new Stack<>();
        Node aux= this.root;
        System.out.println("Before While");
        while(!aux.getData().equals(solve)){
            if(aux.getDepth()==30)
            {
                System.out.println("MAX DEPTH REACHED!!!");
                continue;
            }
            visitedStates.add(aux.getData());
            System.out.println("Searching....");
            Collection<String> children= aux.generateChildren();
            for (String status: children){
                if(!visitedStates.contains(status)){
                    //Create new node
                    Node nChild=new Node(status,aux);
                    statesNotVisited.push(nChild);
                }
            }
            aux=statesNotVisited.pop();
        }
        //solveWithFIFO(statesNotVisited,queue.dequeue(),0);
        System.out.println("RESOLVED IN "+aux.getDepth()+" MOVES!!!");
        printSolution(aux);
    }

    private void printSolution(Node node){
        if(node==null){
            return;
        }
        printSolution(node.parent);
        System.out.println(node.toString());
    }
}
