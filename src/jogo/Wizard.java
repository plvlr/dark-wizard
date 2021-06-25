package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Wizard {

	private int x, y;
	private int dx, dy;
	private int vida;
	public int estamina, contEstamina;
	public int pontuacao;
	public int acumulos;
	private int onda;
	private int altura, largura;
	private boolean isVisible;
	
	
	private Image imagem;
	private List<Magia> magias; 
	
	public Wizard(){
		ImageIcon referencia = new ImageIcon("res\\Wizard.png");
		imagem = referencia.getImage();
		
		altura = imagem.getHeight(null); 
		largura = imagem.getWidth(null); 
		
		magias = new ArrayList<Magia>();
		
		this.vida = 100;
		this.estamina = 50;
		this.contEstamina = -300;
		this.pontuacao = 0;
		this.onda = 1;
		
		
		
		
		this.x = 100;
		this.y = 300;
		
	}
	
	public void setOnda(int onda){
		this.onda = onda;
	}
	
	public int getOnda(){
		return this.onda;
	}
	
	public int vida(){
		return this.vida;
	}
	
	public void getDano(int dano){
		this.vida -= dano;
		
		if(this.vida < 0){
			this.vida = 0;
		}
	}
	
	public int getAcumulos(){
		
		int acumulos = 0;
		
		
		if(this.acumulos > 2 && this.acumulos < 15){
			acumulos = 1;
		}else if(this.acumulos >= 15 && this.acumulos < 40){
			acumulos = 2;
		}else if(this.acumulos >= 40 && this.acumulos < 60){
			acumulos = 3;
		}else if(this.acumulos >= 60 && this.acumulos < 130){
			acumulos = 4;
		}else if(this.acumulos >= 130 && this.acumulos < 200){
			acumulos = 5;
		}else if(this.acumulos >= 200){
			acumulos = 6;
		}
		
		return acumulos;
	} 
	
	public int estamina(){
		
		this.contEstamina++;
		 
		if(this.contEstamina == 18   ){
			this.estamina++;
			this.contEstamina = 0;
		}
		

		if(this.estamina <= 0){			
			this.estamina = 0;
		}
		
		
		if(this.estamina >= 100){			
			this.estamina = 100;
		}
		
		return this.estamina;
	}
	
	public void usarEstamina(int quantidade){
		this.estamina -= quantidade;
	}
	

	public int barraVida(){
		int larguraBarra = this.vida * 3;
		return larguraBarra;
	}
	
	public int pontuacao(){
		return this.pontuacao;
	}	
	
	public void setPontos(int quantidade){
		this.pontuacao += quantidade;
		
		if(this.pontuacao <= 0){
			this.pontuacao = 0;
		}
	}
	

	public void mover(){
		x += dx;
		y += dy;
		
		//System.out.println(x + "//" + y);
		
		if(x < 110){
			x = 110;
		}
		if(x > 913){
			x = 913;
		}
		if(y < 97){
			y = 97;
		}
		if(y > 535){
			y = 535;
		}
	}
	
	public List<Magia> getMagias() {
		return magias;
	}

	public int getX() {
		return x;
	}
	
	
	public int getY() {
		return y;
	}
	public Image getImagem() {
		return imagem;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public void acumulador(){
		this.acumulos++;
	}
	
	
	public void atira(int tipo){
		
		if(this.estamina >= 15){
		
			
			if(tipo == 1 || tipo == 2){
			this.magias.add(new Magia(x + largura - 30, y + altura - 105, tipo));
			}else{
				this.magias.add(new Magia(x + largura - 30, y + altura - 130, tipo));	
			}
				
				
			if(tipo == 1){
				this.estamina -= 15;
				
				}else{
					
					this.estamina -= 100;
					
				}
			
			}


	}
	
	public Rectangle getBounds(){ 
		return new Rectangle(x, y, largura, altura);
	}
	
	public void keyPressed(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_SPACE){
			
			
			if(this.estamina > 90){
				acumulador();
			}
			
		}
		
		if(codigo == KeyEvent.VK_UP){
			dy = -2;
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			dy = 2;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = -2;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 2;
		}
	}
	
	public void keyReleased(KeyEvent tecla){
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_SPACE){
			
			if(this.acumulos >= 200){
				atira(3);
				this.acumulos = 0;
			}
		else if(this.acumulos >= 40){
				atira(2);
				this.acumulos = 0;
			}else{
			atira(1);
			this.acumulos = 0;
			}
		}
		
		if(codigo == KeyEvent.VK_UP){
			dy = 0;
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			dy = 0;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = 0;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		
	}
	
}