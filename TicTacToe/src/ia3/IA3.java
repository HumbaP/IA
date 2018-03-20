/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia3;

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
        static int whowin; // 0== empate, +10= gana humano ,-10= gana IA
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
            for(int i=0;i<25;i++){
                buttons[i]=new XOButton();
                add(buttons[i]);
            }
            
            //sacar el string resultante
            //getStatus();
            
        }        
        
        //metodo para imprimir string despues de cada movimiento
        public static String getStatus(){
            String result="";
            for(int i=0;i<25;i++){
                result=result+buttons[i].getMov();
            }
            r=result;
            return r;
        }
        
        //metodo para cambiar de jugador
         public static int it(){
            i++;
            int a=(i%2)+1;
            return a;       
         }
         
         //metodo para checar ganador
         public static boolean check(String status,int start,int increment,int limit){
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
         
         public static int win(){
             String state= getStatus();
             if(check(state,0,1,4)==true){
                 if(seleccion==0 && state.charAt(0)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(0)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(0)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(0)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,5,1,9)==true){
                 if(seleccion==0 && state.charAt(5)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(5)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(5)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(5)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,10,1,14)==true){
                 if(seleccion==0 && state.charAt(10)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(10)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(10)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(10)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,15,1,19)==true){
                 if(seleccion==0 && state.charAt(15)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(15)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(15)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(15)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,20,1,24)==true){
                 if(seleccion==0 && state.charAt(20)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(20)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(20)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(20)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,0,5,20)==true){
                 if(seleccion==0 && state.charAt(0)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(0)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(0)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(0)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,1,5,21)==true){
                 if(seleccion==0 && state.charAt(1)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(1)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(1)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(1)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,2,5,22)==true){
                 if(seleccion==0 && state.charAt(2)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(2)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(2)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(2)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,3,5,23)==true){
                 if(seleccion==0 && state.charAt(3)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(3)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(3)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(3)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,4,5,24)==true){
                 if(seleccion==0 && state.charAt(4)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(4)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(4)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(4)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,0,6,24)==true){
                if(seleccion==0 && state.charAt(0)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(0)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(0)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(0)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,4,4,20)==true){
                 if(seleccion==0 && state.charAt(4)=='X'){
                    whowin=10;
                    JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
                 if(seleccion==0 && state.charAt(4)=='O'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(4)=='X'){
                    whowin=-10;
                    JOptionPane.showMessageDialog(null, "AI win");
                 }
                 if(seleccion==1 && state.charAt(4)=='O'){
                     whowin=10;
                     JOptionPane.showMessageDialog(null, "Congratz you win");
                 }
             }
             if(check(state,4,4,20)==false && check(state,0,6,24)==false && check(state,4,5,24)==false && check(state,3,5,23)==false && check(state,2,5,22)==false && check(state,1,5,21)==false && check(state,0,5,20)==false && check(state,20,1,24)==false && check(state,15,1,19)==false && check(state,10,1,14)==false && check(state,5,1,9)==false && check(state,0,1,4)==false){
                 whowin=0; //empate
             }
             return whowin;
         }
    }
    
