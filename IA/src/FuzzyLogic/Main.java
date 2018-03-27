package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
//import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;
import net.sourceforge.jFuzzyLogic.rule.Rule;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Main extends JFrame implements ActionListener {
	static double Score=0;
	static int CorrectOrIncorrect;
	static long TStart, TFinish, time;
	JLabel CoI;
	JLabel tm;
    JLabel problem;
    JLabel score2;
    JTextField answer; 
    JButton next;
    String p;
    double a;
    
	public Main() throws Exception {
        super();
        configureFrame();       
    }
	
	private void configureFrame() throws Exception{		
        this.setTitle("Math practice");                   
        this.setSize(600, 600);                                 
        this.setLocationRelativeTo(null);                       
        this.setLayout(null);                                  
        this.setResizable(false);                               
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel text= new JLabel("Solve:");    
        JLabel text2= new JLabel("Answer:");  
        p=Women.createProblem(Score);
        a=ExpressionParser.eval(p);
        CoI= new JLabel();
        tm = new JLabel();
        score2 = new JLabel();
        problem= new JLabel(p); 
        next = new JButton("Next");
        next.setEnabled(false);
        answer=new JTextField(8);                        
        answer.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                        	try {
                        	String ans=answer.getText();
                            if(Integer.parseInt(ans)==(int)a) {
                            		CorrectOrIncorrect=10;
                                	CoI.setText("Correct");
                                	CoI.setForeground(Color.GREEN);
                                	next.setEnabled(true);
                            }else {CoI.setText("Incorrect"); 
                            	   CoI.setForeground(Color.RED);CorrectOrIncorrect=0;
                            	   next.setEnabled(false);
                            	   }
                            TFinish = System.currentTimeMillis();
                            time = TFinish - TStart;
                            double time2= (double)time/1000;
                            fuzzifier(time2, CorrectOrIncorrect);                          
                            String tm2 = Double.toString(time2);
                            tm.setText("Time: " +tm2 +" seconds");
                            String xcor=Double.toString(Score);
                            score2.setText("Score: "+xcor+ "/100");
                        	}catch(NumberFormatException e2) {
                        		JOptionPane.showConfirmDialog(null,"Invalid! Please input an integer","Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            }
                        }});             
         
        text.setBounds(50,50,150,50);
        text.setFont(new java.awt.Font("Arial",1,40));
        text2.setBounds(50,200,150,50);
        text2.setFont(new java.awt.Font("Arial",1,30));
        CoI.setBounds(100,300,150,50);
        CoI.setFont(new java.awt.Font("Arial",1,30));
        tm.setBounds(100,350,300,50);
        tm.setFont(new java.awt.Font("Arial",1,20));
        score2.setBounds(100,400,300,50);
        score2.setFont(new java.awt.Font("Arial",1,20));
        problem.setBounds(200,50,400,100);
        problem.setFont(new java.awt.Font("Arial",1,100));
        answer.setBounds(200,200,70,40);
        answer.setFont(new java.awt.Font("Arial",1,30));
        next.setBounds(300,200,100,40);
        next.setFont(new java.awt.Font("Arial",1,20));
        next.addActionListener(this);
        this.add(text);
        this.add(text2);
        this.add(problem);
        this.add(answer);
        this.add(CoI);
        this.add(tm);
        this.add(next);
        this.add(score2);
    }
		
	public static void main(String[] args) throws Exception{
		//create the frame
		Main f = new Main();      
        f.setVisible(true);
        TStart = System.currentTimeMillis();		
	}
	
	public void fuzzifier(double t, int CorI) {
		// Load from 'FCL' file
        String fileName = "C:\\Users\\Chelopez\\eclipse-workspace\\IA\\src\\FuzzyLogic\\Math.fcl";
        FIS fis = FIS.load(fileName,true);
        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        // Show 
        //fis.chart();

        // Set inputs
        fis.setVariable("time", t);
        fis.setVariable("result",CorI);

        // Evaluate
        fis.evaluate();

        // Show output variable's chart 
        //fis.getVariable("tip").chartDefuzzifier(true);
        Score=fis.getVariable("level").getValue();
        System.out.println("Score:" + Score);
        
     // Show each rule (and degree of support)
        for( Rule r : fis.getFunctionBlock("Math").getFuzzyRuleBlock("No1").getRules() )
          System.out.println(r);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//clean labels  and textbox
		answer.setText("");
		CoI.setText("");
		tm.setText("");
		
		//new problem
		p=Women.createProblem(Score);
        a=ExpressionParser.eval(p);
		problem.setText(p);
		
		//restart start time
		TStart = System.currentTimeMillis();
	}
}
