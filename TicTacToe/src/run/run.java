package run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import javax.swing.text.Position;

import com.company.TicTacData;

import artificial.structures.Node;
import artificial.structures.Node.UtilityInformation;
import utils.StringUtils;

public class run {
	public static void main(String[] args) {
		String state="XXXOXO XO";
		TicTacData data=new TicTacData(state, true, 8,3);
		Node<TicTacData> node=new Node<TicTacData>(data,new UtilityInformation() {
			HashMap<String,Vector<Integer>>  des=new HashMap<>();
			@Override
			public int calculateUtility(Node node) {
			    Node<TicTacData> status=node;
                ArrayList<String> possibleDesitions=possibleFinals(status);//new ArrayList<>();
                int utility=0;
                System.out.println(possibleDesitions.size());
                for(String st: possibleDesitions){
                    utility+=getPossibleUtility(st,status.getState().getSize());
                }
                return utility;
			}


            private ArrayList<String> possibleFinals(Node<TicTacData> status){
				ArrayList<String> possibleFinals =new ArrayList<>();
				String estPos=possible(0,status.getState().getSize()+1, status.getState());
                System.out.println(estPos);
				possibleFinals.add(estPos);//Me quede bien jarcor aquí :'v
				estPos=possible(status.getState().getSize()-1,status.getState().getSize()-1,status.getState());
				possibleFinals.add(estPos);
                System.out.println(estPos);
				for(int i=0;i<status.getState().getSize();i++){
					estPos=possible(i*status.getState().getSize(),1,status.getState());//Linea horizontal
					possibleFinals.add(estPos);
                    System.out.println(estPos);
					estPos=possible(i,status.getState().getSize(),status.getState());//Linea horizontal
					possibleFinals.add(estPos);
                    System.out.println(estPos);
				}
				return possibleFinals;
			}

			private String possible(int start, int increment,TicTacData state){
				String est="";
				Vector<Integer> p=new Vector<>();
				int size=0;
				for(int i=start; i<state.getStatus().length() && size<state.getSize(); i+=increment) {
					est+=state.getStatus().charAt(i);
				    p.add(i);
				    size++;
				}
				des.put(est,p);
				return est;
			}

            private int getPossibleUtility(String finalStatus,int size){
                int x=getAllIndexesFromString('X',finalStatus);
                int o=getAllIndexesFromString('O',finalStatus);
                if(x==size-1)//Está a punto de ganar la IA
                    return size*4;
                if (o==size-1)//Está a punto de perder la IA
                    return size*2;
                if(o==size)//Ha perdido la IA
                    return -size*8;
                if(x==size)//Ha ganado la IA
                    return size*8;
                return (x*1)-(o*1);//No ha ganado nadie
            }

            private int getAllIndexesFromString(char find, String string){
			    int found=0;
			    for(char c: string.toCharArray()){
                    if(c==find)
                        found++;
                }
			    return found;
            }
		});
		System.out.println(node.getUtility());
	}

}
