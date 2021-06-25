package jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Magia {
	
	private Image imagem;
	private int x, y;
	private int tipo;
	private int largura, altura;
	private boolean isVisivel;	
	private static final int larguraTela = 1030;
	
	public Magia(int x, int y, int tipo){
		
		this.x = x;
		this.y = y;
		this.tipo = tipo;
		
		if(tipo == 1){
			
		ImageIcon referencia = new ImageIcon("res\\magia.png");
		imagem = referencia.getImage();
		
		}else if(tipo == 2){
			
			ImageIcon referencia = new ImageIcon("res\\magiaFinal.png");
			imagem = referencia.getImage();
			
		}else{
			ImageIcon referencia = new ImageIcon("res\\megaMagiaFinal.png");
			imagem = referencia.getImage();
		}
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
		isVisivel = true;
		
	}
	
	public void mover(){
		
		if(this.tipo == 1 || this.tipo == 3){
	
			this.x += 2;

		}else if(tipo == 2){
			this.x += 4;			
		}
		
		if(this.x > larguraTela){
			isVisivel = false;
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
	public int getTipo(){
		return this.tipo;
	}

	public static int getLarguraMagia() {
		return larguraTela;
	}
	
	public Rectangle getBounds(){ 
		return new Rectangle(x, y, largura, altura);
	}
	

}