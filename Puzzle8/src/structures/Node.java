package structures;

import sun.misc.Queue;

import java.util.*;

public class Node {

    //data example : "12345678 ";
    //"246781 35"
    private String data;
    private ArrayList<Node> children;
    Node parent=null;
    private int depth;

    public Node(String data){
        this.data=data;
        children=new ArrayList<>();
        depth=0;
    }
    public Node(String data, Node parent){
        this.data=data;
        children=new ArrayList<>();
        this.parent=parent;
        depth=parent.depth+1;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Collection<String> generateChildren(){
        int spaceIndex= this.data.indexOf(" ");
        ArrayList<String> children=new ArrayList<>();
        String auxStatus;
        /*
         * |0|1|2|
         * |3|4|5|
         * |6|7|8|
         * */
        switch (spaceIndex){
            case 0:
                //Index 0 only has 2 PM (change to 1 or change to 3)
                //First movement (change to 1)
                /*
                 * | |X|F|   -----       |X| |F|
                 * |Z|_|_|               |Z|_|_|
                 * |_|_|_|   -----/      |_|_|_|
                 * */
                //Create new node
                auxStatus= generateNewState(1,spaceIndex);
                children.add(auxStatus);

                //Second movement (change to 3)
                /*
                 * | |X|F|   -----       |Z|X|F|
                 * |Z|_|_|               | |_|_|
                 * |_|_|_|   -----/      |_|_|_|
                 * */
                auxStatus= generateNewState(3,spaceIndex);
                children.add(auxStatus);

                break;
            case 1:
                //Index 1 has 3 PM (change to 0, change to 2 or change to 4)
                //First movement (change to 0)
                /*
                 * |X| |F|   -----       | |X|F|
                 * |_|Z|_|               |_|Z|_|
                 * |_|_|_|   -----/      |_|_|_|
                 * */
                auxStatus= generateNewState(0,spaceIndex);
                //Create new node.children.addauxStatusd);

                //Index 1 has 3 PM (change to 0, change to 2 or change to 4)
                //First movement (change to 2)
                /*
                 * |X| |F|   -----       |X|F| |
                 * |_|Z|_|               |_|Z|_|
                 * |_|_|_|   -----/      |_|_|_|
                 * */
                auxStatus= generateNewState(2,spaceIndex);
                //Create new node.children.addauxStatusd);

                //Index 1 has 3 PM (change to 0, change to 2 or change to 4)
                //First movement (change to 4)
                /*
                 * |X| |F|   -----       |X|Z|F|
                 * |_|Z|_|               |_| |_|
                 * |_|_|_|   -----/      |_|_|_|
                 * */
                auxStatus= generateNewState(4,spaceIndex);
                children.add(auxStatus);
                break;
            case 2:
                //Index 2 has 2 PM (change to 1, change to 5)
                //First movement (change to 1)
                /*
                 * |_|F| |   -----       |_| |F|
                 * |_|Z|X|               |_|Z|X|
                 * |_|_|_|   -----/      |_|_|_|
                 * */
                auxStatus= generateNewState(1,spaceIndex);
                children.add(auxStatus);
                //First movement (change to 5)
                /*
                 * |_|F| |   -----       |_|F|X|
                 * |_|Z|X|               |_|Z| |
                 * |_|_|_|   -----/      |_|_|_|
                 * */
                auxStatus= generateNewState(5,spaceIndex);
                children.add(auxStatus);
                break;
            case 3:
                //Index 3 has 3 PM (change to 0, change to 4 and change to 6
                //First movement change to 0
                /*
                 * |X|_|_|   ------      | |_|_|
                 * | |F|_|               |X|F|_|
                 * |Z|_|_|   -------     |Z|_|_|
                 * */
                auxStatus= generateNewState(0,spaceIndex);
                children.add(auxStatus);
                //First movement change to 4
                /*
                 * |X|_|_|   ------      |X|_|_|
                 * | |F|_|               |F| |_|
                 * |Z|_|_|   -------     |Z|_|_|
                 * */
                auxStatus= generateNewState(4,spaceIndex);
                children.add(auxStatus);

                //First movement change to 6
                /*
                 * |X|_|_|   ------      |X|_|_|
                 * | |F|_|               |Z|F|_|
                 * |Z|_|_|   -------     | |_|_|
                 * */
                auxStatus= generateNewState(6,spaceIndex);
                children.add(auxStatus);
                break;
            case 4:
                //Index 3 has 4 PM (change to 1, change to 3 ,change to 5 and change to 7
                //First movement (change to 1)
                /*
                 * |_|F| |   -----       |_| |_|
                 * |Z| |X|               |Z|F|X|
                 * |_|Y|_|   -----/      |_|Y|_|
                 * */
                auxStatus= generateNewState(1,spaceIndex);
                children.add(auxStatus);
                //First movement (change to 3)
                /*
                 * |_|F| |   -----       |_|F|_|
                 * |Z| |X|               | |Z|X|
                 * |_|Y|_|   -----/      |_|Y|_|
                 * */
                auxStatus= generateNewState(3,spaceIndex);
                children.add(auxStatus);
                //First movement (change to 5)
                /*
                 * |_|F| |   -----       |_|F|_|
                 * |Z| |X|               |Z|X| |
                 * |_|Y|_|   -----/      |_|Y|_|
                 * */
                auxStatus= generateNewState(5,spaceIndex);
                children.add(auxStatus);
                //First movement (change to 7)
                /*
                 * |_|F| |   -----       |_|F|_|
                 * |Z| |X|               |Z|Y|X|
                 * |_|Y|_|   -----/      |_| |_|
                 * */
                auxStatus= generateNewState(7,spaceIndex);
                children.add(auxStatus);
                break;
            case 5:
                auxStatus= generateNewState(2,spaceIndex);
                children.add(auxStatus);

                auxStatus= generateNewState(4,spaceIndex);
                children.add(auxStatus);

                auxStatus= generateNewState(8,spaceIndex);
                children.add(auxStatus);
                break;
            case 6:
                auxStatus= generateNewState(3,spaceIndex);
                children.add(auxStatus);
                auxStatus= generateNewState(7,spaceIndex);
                children.add(auxStatus);
                break;
            case 7:
                auxStatus= generateNewState(4,spaceIndex);
                children.add(auxStatus);

                auxStatus= generateNewState(6,spaceIndex);
                children.add(auxStatus);

                auxStatus= generateNewState(8,spaceIndex);
                children.add(auxStatus);
                break;
            case 8:
                auxStatus= generateNewState(5,spaceIndex);
                children.add(auxStatus);

                auxStatus= generateNewState(7,spaceIndex);
                children.add(auxStatus);
                break;
            default:
                System.out.println("Something went wrong");
                break;
        }
        return children;
    }

    private String generateNewState(int indexToChange, int indexToSave){
        char auxChar;
        char [] arrAux;
        auxChar= this.data.charAt(indexToChange);
        arrAux=this.data.replace(auxChar, ' ').toCharArray();
        arrAux[indexToSave]= auxChar;
        return String.valueOf(arrAux);
    }

    @Override
    public String toString() {
        String output="";
        char[] aux= this.data.toCharArray();
        for (int j=0;j<aux.length; j++){
            output+="|"+aux[j];
            if(j%3==2)
                output+="|\n";
        }
        return output;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
