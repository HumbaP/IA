/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import artificial.structures.Node;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Chelopez
 */
public class IA3{
    public static void main(String[] args){
		JFrame Window = new JFrame("Tic Tac Toe");
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Panel panel = new Panel();
		Window.add(panel);

		Window.setSize(500, 500);
		Window.setVisible(true);
	}
}

    class Panel extends JPanel{
        static int i=1;
        static String r;
        static XOButton buttons[]=new XOButton[25];
        static Board board;
        ImageIcon icon =new ImageIcon(this.getClass().getResource("tictactoe.png"));
        static int seleccion;
        public Panel(){
            //Hacer el 5x5
            GridLayout scheme; scheme = new GridLayout(5,5);
            setLayout(scheme);
            
            //preguntar quién empieza
            String[] options = {"Human", "AI"};
            seleccion = JOptionPane.showOptionDialog(null, "Who starts?", "Tic Tac Toe", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,icon, options, options[0]);

            //crear los botones 
            listener Listener = new listener();
            for(int i=0;i<25;i++){
                buttons[i]=new XOButton();
                buttons[i].addActionListener(Listener);
                add(buttons[i]);
            }
            board=new Board(5);
            //sacar el string resultante
            getResult();
            
        }        
        
        //metodo para imprimir string despues de cada movimiento
        public static String getResult(){
            String result="";
            for(int i=0;i<25;i++){
                result=result+buttons[i].getMov();
            }
            r=result;
            System.out.println(r);
            return r;
        }
         
         //metodo para checar ganador
         public static boolean check(int start,int increment,int limit){
             boolean win=false;
             while(start<limit){
                 if(buttons[start].getMov()!=" " && buttons[start].getMov().equals(buttons[start+increment].getMov())){
                     win=true;
                 }else{
                     win=false; 
                     break;
                 }
                 start=start+increment;
             }
             return win;
         }
         
         public static void win(){
             if(check(0,1,4)==true){
                 if(seleccion==0 && buttons[0].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[0].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[0].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[0].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(5,1,9)==true){
                 if(seleccion==0 && buttons[5].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[5].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[5].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[5].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(10,1,14)==true){
                 if(seleccion==0 && buttons[10].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[10].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[10].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[10].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(15,1,19)==true){
                 if(seleccion==0 && buttons[15].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[15].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[15].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[15].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(20,1,24)==true){
                 if(seleccion==0 && buttons[20].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[20].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[20].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[20].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(0,5,20)==true){
                 if(seleccion==0 && buttons[0].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[0].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[0].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[0].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(1,5,21)==true){
                 if(seleccion==0 && buttons[1].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[1].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[1].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[1].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(2,5,22)==true){
                 if(seleccion==0 && buttons[2].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[2].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[2].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[2].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(3,5,23)==true){
                 if(seleccion==0 && buttons[3].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[3].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[3].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[3].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(4,5,24)==true){
                 if(seleccion==0 && buttons[4].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[4].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[4].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[4].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(0,6,24)==true){
                 if(seleccion==0 && buttons[0].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[0].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[0].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[0].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(4,4,20)==true){
                 if(seleccion==0 && buttons[4].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && buttons[4].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[4].getMov().equals("X")){
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && buttons[4].getMov().equals("O")){
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
         }
        class listener implements ActionListener{
            public void actionPerformed( ActionEvent e ){
                ((XOButton)e.getSource()).setMov("O");        //turno del jugador 
                board.updateMove(getResult());
                board.setbotTurn(true);
                board.bot.minimaX(board.getStatus());
                Panel.getResult();
                Board.win(board.getStatus());
            }
         }
    }
    
