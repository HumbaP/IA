/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia3;

/**
 *
 * @author Chelopez
 */
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XOButton extends JButton implements ActionListener{
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
		this.addActionListener(this);                
	}
	
	public void actionPerformed(ActionEvent e){
            
            int value= Panel.it();     
		switch(value){
                    
                    case 1:
                        setMov("X");
			setIcon(X);
                        setEnabled(false);                       
			break;
                    case 2:
                        setMov("O");
			setIcon(O);
                        setEnabled(false);                        
                        break;
                    default:
			setIcon(null);   
                        setMov(" ");
			break;
		}
          
                Panel.win();
	}
        public void setMov(String m){
            this.mov=m;
        }
        public String getMov(){
            return mov;
        }
       
}
