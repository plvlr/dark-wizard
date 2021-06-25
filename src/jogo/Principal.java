package jogo;

import javax.swing.JFrame;

public class Principal extends JFrame {
	public Principal(){
		
		
		add(new Fase());
		setTitle("Dark Wizard");
		setSize(1280,720);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) throws Exception{
		new Principal();
				
	}
	
	
}
