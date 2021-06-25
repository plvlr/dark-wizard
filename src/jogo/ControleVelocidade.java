package jogo;

import java.awt.event.KeyEvent;

public class ControleVelocidade {
	
	private int velocidadeJogo;
	private boolean isRunning;
	
	ControleVelocidade(){
		velocidadeJogo = 1;
		isRunning = true;
	}
	
	public void setVelocidade(int quantidade){
		
		
		velocidadeJogo += quantidade;
		
		if(velocidadeJogo > 6){
			velocidadeJogo = 6;
		}
		
		if (velocidadeJogo < 1){
			velocidadeJogo = 1;
		}
	}
	
	public void sleep(){
		if(isRunning){
			 try {
				Thread.sleep(velocidadeJogo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  
		 
		}
	}
	
	public void keyReleased(KeyEvent tecla){
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_F1){
			setVelocidade(1);
		}
		
		if(codigo == KeyEvent.VK_F2){
			setVelocidade(-1);
		}

	}

}
