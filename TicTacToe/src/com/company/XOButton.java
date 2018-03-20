/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

/**
 *
 * @author Chelopez
 */
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XOButton extends JButton {
	ImageIcon X,O;
	String mov=" ";
	/*
	0:nothing
	1:X
	2:O
	*/	
	public XOButton(){
		X=new ImageIcon(this.getClass().getResource("X.png"));
		O=new ImageIcon(this.getClass().getResource("O.png"));
		//this.addActionListener(this);                
	}
/*
	public void actionPerformed(ActionEvent e){
            
            setMov("O");        //turno del jugador               
                        
            Panel.getResult();
            Panel.win();
	}*/
        public void setMov(String m){
            if(m.equals("O"))
                setIcon(O);
            else 
                setIcon(X) ;
            this.setEnabled(false);
            this.mov=m;
        }
        public String getMov(){
            return mov;
        }
        
       
}
