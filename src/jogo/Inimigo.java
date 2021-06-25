package jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {

	private Image imagem;
	private int x, y, tipo, iniY, iniContador;
	private int somador = 1;
	private boolean limite = false;
	private int largura, altura;
	private boolean isVisivel;
	private int larguraTela = 1480;
	private int velocidade;

	public Inimigo(int x, int y, int tipo, int velocidade) {

		this.x = x;
		this.y = y;
		this.iniY = y;
		this.tipo = tipo;
		this.velocidade = velocidade;

		
			ImageIcon referencia;
			referencia = new ImageIcon("res\\inimigo" + this.tipo + ".png");
			imagem = referencia.getImage();
			this.largura = imagem.getWidth(null);
			this.altura = imagem.getHeight(null);

		
	
		isVisivel = true;
	}
	
	public void mover(){
		

		if(this.x < 0){
			this.x = larguraTela;
		} else {
		
		if(this.tipo == 1){	

			this.x -= velocidade;
			
		}
		
		if(this.tipo == 2){
			
			
				this.x -= velocidade;
				
				if(this.limite == false){
					this.y+= 2;
	
				}
				if(this.limite == true){
					this.y-= 2;
	
				}
				
				if(this.y == iniY + 130){
					limite = true;
				}
				
				if(this.y == iniY - 130){
					limite = false;
				}
				

			}
		
		if(this.tipo == 3){
			
			this.x -= somador;
			iniContador++;
			if(iniContador == 45){
						
				somador++;
				iniContador = 1;
				
				if(somador == 4 + velocidade){
					somador = -1;
				}
					
				}
		
			
		}
		
		
		}
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	
}
