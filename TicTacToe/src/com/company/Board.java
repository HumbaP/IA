package com.company;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import artificial.botfactory.Charlie;
import artificial.botfactory.Charlie.Behavior;
import artificial.structures.Node;
import artificial.structures.Node.UtilityInformation;

public class Board implements Behavior, UtilityInformation {
	/*Tic tac toe board*/
	private Node<TicTacData> status;
	private int dSize;
	Charlie bot;
	private boolean botTurn;
	
	public Board(int size) {
		this.dSize=size;
		this.generateEmptyBoard();
		this.bot=new Charlie(this);
		
	}
	public Board(Node<TicTacData> status, int size) {
		this.dSize=size;
		this.status=status;
		this.bot=new Charlie(this);
	}

	public Node<TicTacData> getStatus() {
		return status;
	}

	public void setStatus(Node<TicTacData>  status) {
		this.status = status;
	}

	public int getdSize() {
		return dSize;
	}

	public void setdSize(int dSize) {
		this.dSize = dSize;
	}
        
        public void setbotTurn(boolean botTurn){
            this.botTurn=botTurn;
        }
        public boolean getBotTurn(){
            return this.botTurn;
        }
	
        public void updateMove(String status){
            //this.status=new Node<>(new TicTacData(status, false, this.status.getPosition()),this.status,this);
            this.botTurn=true;
        }
        
	private void generateEmptyBoard() {
		int siz= this.dSize*this.dSize;
		String s="";
		for(int j=0; j<siz;j++) {
			s+=" ";
		}
                //this.status=new Node<TicTacData>(new TicTacData(s, false, dSize), this);
		this.status.getState().setStatus(s);
	}
	
	public ArrayList<Integer> getPossibleMoves(){
		ArrayList<Integer> possibleMoves = new ArrayList<>();
		int index = this.status.getState().getStatus().indexOf(" ");
		while (index >= 0) {
		    possibleMoves.add(index);
		    index = this.status.getState().getStatus().indexOf(" ", index + 1);
		}
		return possibleMoves;
	}
	
	@Override
	public void makeMovement(Node decision) {
		// TODO Auto-generated method stub
                int posicion=decision.getPosition();
                Panel.buttons[posicion].setMov("X");
                this.botTurn=false;
                System.out.println("PPPPP "+((TicTacData)decision.getState()).getStatus());
                Board.win(decision);
	}
	
	@Override
	public void generateChildStates(Node parent) {
		// TODO Auto-generated method stub
        Node<TicTacData> auxParent=(Node<TicTacData>) parent;
		ArrayList<Integer> auxMoves=new ArrayList<>();
        TicTacData nextStep;
        StringBuilder nextState;
        for (int index : auxMoves) {
            nextState=new StringBuilder(auxParent.getState().getStatus());
            nextState.setCharAt(index,auxParent.getState().getNextMove());
            //nextStep=new TicTacData(nextState.toString(),!auxParent.getState().isBotLastMove(),index);
            //auxParent.getChildren().add(new Node<TicTacData>(nextStep,auxParent,index,this));
        }
	}
	@Override
	public int checkWinState() {
		// TODO Auto-generated method stub
		return getWinPlayer();
	}
	
	public byte getWinPlayer() {
		
		return 0;
	}
	
	//metodo para checar ganador
    public static boolean check(Node<TicTacData> status,int start,int increment,int limit){
        boolean win=false;
        while(start<limit){
            if(status.getState().getStatus().charAt(start)!=' ' && status.getState().getStatus().charAt(start)==status.getState().getStatus().charAt(start+increment)){
                win=true;
            }else{
                win=false; 
                break;
            }
            start=start+increment;
        }
        return win;
    }


    public static int checkTie(String status,int start,int increment,int limit){
        int Auxstart=start;
        int tie=0;
        while(start<=limit){
            if(status.charAt(start)=='X'){
                tie=tie+1;
                break;
            }
            start=start+increment;
        }
        while(Auxstart<=limit){
            if(status.charAt(Auxstart)=='O'){
                tie=tie+1;
                break;
            }
            Auxstart=Auxstart+increment;
        }
        return tie;
    }

    public static int win(Node<TicTacData> status){
        if(check(status,0,1,4)==true){
           
            if( status.getState().getStatus().charAt(0)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(0)=='O'){
                return -10;
            }
        }
        if(check(status,5,1,9)==true){
            
            if( status.getState().getStatus().charAt(5)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(5)=='O'){
                return -10;
            }
        }
        if(check(status,10,1,14)==true){
          
            if( status.getState().getStatus().charAt(10)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(10)=='O'){
                return -10;
            }
        }
        if(check(status,15,1,19)==true){
           
            if( status.getState().getStatus().charAt(15)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(15)=='O'){
                return -10;
            }
        }
        if(check(status,20,1,24)==true){
           
            if( status.getState().getStatus().charAt(20)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(20)=='O'){
                return -10;
            }
        }
        if(check(status,0,5,20)==true){
           
            if( status.getState().getStatus().charAt(0)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(0)=='O'){
                return -10;
            }
        }
        if(check(status,1,5,21)==true){
           
            if( status.getState().getStatus().charAt(1)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(1)=='O'){
                return -10;
            }
        }
        if(check(status,2,5,22)==true){
            
            if( status.getState().getStatus().charAt(2)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(2)=='O'){
                return -10;
            }
        }
        if(check(status,3,5,23)==true){
            
            if( status.getState().getStatus().charAt(3)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(3)=='O'){
                return -10;
            }
        }
        if(check(status,4,5,24)==true){
           
            if( status.getState().getStatus().charAt(4)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(4)=='O'){
                return -10;
            }
        }
        if(check(status,0,6,24)==true){
           
            if( status.getState().getStatus().charAt(0)=='X'){
               return 10;
            }
            if( status.getState().getStatus().charAt(0)=='O'){
                return -10;
            }
        }
        if(check(status,4,4,20)==true){
            
            if( status.getState().getStatus().charAt(4)=='X'){
               return 10;
               
            }
            if( status.getState().getStatus().charAt(4)=='O'){
                return -10;

            }
        }
        if(checkTie(status.getState().getStatus(),4,4,20)==2 && checkTie(status.getState().getStatus(),0,6,24)==2 && checkTie(status.getState().getStatus(),4,5,24)==2 && checkTie(status.getState().getStatus(),3,5,23)==2 && checkTie(status.getState().getStatus(),2,5,22)==2 && checkTie(status.getState().getStatus(),1,5,21)==2 && checkTie(status.getState().getStatus(),0,5,20)==2 && checkTie(status.getState().getStatus(),20,1,24)==2 && checkTie(status.getState().getStatus(),15,1,19)==2 && checkTie(status.getState().getStatus(),10,1,14)==2 && checkTie(status.getState().getStatus(),5,1,9)==2 && checkTie(status.getState().getStatus(),0,1,4)==2){
            return 0; //empate
        }
        return 9999;
    }
    /*
    //metodo para checar ganador
    public boolean check(int start,int increment,int limit){
        boolean win=false;
        while(start<limit){
            if(status.charAt(start)!=' ' && status.charAt(start)==status.charAt(start+increment)){
                win=true;
            }else{
                win=false; 
                break;
            }
            start=start+increment;
        }
        return win;
    }
      public int win(){
        if(check(0,1,4)==true){
            if(!botTurn && status.charAt(0)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(0)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(0)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(0)=='O'){
                return 10;
            }
        }
        if(check(5,1,9)==true){
            if(!botTurn && status.charAt(5)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(5)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(5)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(5)=='O'){
                return 10;
            }
        }
        if(check(10,1,14)==true){
            if(!botTurn && status.charAt(10)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(10)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(10)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(10)=='O'){
                return 10;
            }
        }
        if(check(15,1,19)==true){
            if(!botTurn && status.charAt(15)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(15)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(15)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(15)=='O'){
                return 10;
            }
        }
        if(check(20,1,24)==true){
            if(!botTurn && status.charAt(20)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(20)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(20)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(20)=='O'){
                return 10;
            }
        }
        if(check(0,5,20)==true){
            if(!botTurn && status.charAt(0)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(0)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(0)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(0)=='O'){
                return 10;
            }
        }
        if(check(1,5,21)==true){
            if(!botTurn && status.charAt(1)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(1)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(1)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(1)=='O'){
                return 10;
            }
        }
        if(check(2,5,22)==true){
            if(!botTurn && status.charAt(2)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(2)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(2)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(2)=='O'){
                return 10;
            }
        }
        if(check(3,5,23)==true){
            if(!botTurn && status.charAt(3)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(3)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(3)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(3)=='O'){
                return 10;
            }
        }
        if(check(4,5,24)==true){
            if(!botTurn && status.charAt(4)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(4)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(4)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(4)=='O'){
                return 10;
            }
        }
        if(check(0,6,24)==true){
           if(!botTurn && status.charAt(0)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(0)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(0)=='X'){
               return -10;
            }
            if(botTurn && status.charAt(0)=='O'){
                return 10;
            }
        }
        if(check(4,4,20)==true){
            if(!botTurn && status.charAt(4)=='X'){
               return 10;
            }
            if(!botTurn && status.charAt(4)=='O'){
               return -10;
            }
            if(botTurn && status.charAt(4)=='X'){
               return -10;
               
            }
            if(botTurn && status.charAt(4)=='O'){
                return 10;

            }
        }
        if(check(4,4,20)==false && check(0,6,24)==false && check(4,5,24)==false && check(3,5,23)==false && check(2,5,22)==false && check(1,5,21)==false && check(0,5,20)==false && check(20,1,24)==false && check(15,1,19)==false && check(10,1,14)==false && check(5,1,9)==false && check(0,1,4)==false){
            return 0; //empate
        }
        return 9999;
    }
     */
	@Override
	public int calculateUtility(Node node) {
		// TODO Auto-generated method stub
		return 0;
	}
    
}
