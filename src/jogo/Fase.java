package jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image fundo, sobre1, backBarra, barraVida, barraEstamina, acumulos1, acumulos2, acumulos3, acumulos4, acumulos5, acumulos6;
	private Wizard wizard;
	private Timer timer;
	private int status;
	private int timerOndas;
	private int tremorX;
	private SalvarJogo salvarJogo;
	private ControleVelocidade velocidadeJogo;
	boolean salvar;
	boolean tremer;
	int limite;
	private CarregarOndas carregarOndas;

	
	
	public Fase() {

		setFocusable(true);
		addKeyListener(new TecladoAdapter());
		
		
		ImageIcon referencia = new ImageIcon("res\\fundo.jpg");
		fundo = referencia.getImage();
		
		ImageIcon referencia1 = new ImageIcon("res\\subfase1.jpg");
		sobre1 = referencia1.getImage();
		
		ImageIcon backBarra1 = new ImageIcon("res\\backBarra.png");	
		backBarra = backBarra1.getImage();
		
		ImageIcon barraVida1 = new ImageIcon("res\\barraVida.jpg");	
		barraVida = barraVida1.getImage();
		
		ImageIcon barraEstamina1 = new ImageIcon("res\\barraEstamina.jpg");	
		barraEstamina = barraEstamina1.getImage();
		
		ImageIcon acumulos11 = new ImageIcon("res\\acumulos1.png");	
		acumulos1 = acumulos11.getImage();
		
		ImageIcon acumulos21 = new ImageIcon("res\\acumulos2.png");	
		acumulos2 = acumulos21.getImage();
		
		ImageIcon acumulos31 = new ImageIcon("res\\acumulos3.png");	
		acumulos3 = acumulos31.getImage();
		
		ImageIcon acumulos41 = new ImageIcon("res\\acumulos4.png");	
		acumulos4 = acumulos41.getImage();
		
		ImageIcon acumulos51 = new ImageIcon("res\\acumulos5.png");	
		acumulos5 = acumulos51.getImage();
		
		ImageIcon acumulos61 = new ImageIcon("res\\acumulos6.png");	
		acumulos6 = acumulos61.getImage();
		

		wizard = new Wizard();
		
		carregarOndas = new CarregarOndas();
		
		salvarJogo = new SalvarJogo();
		
		velocidadeJogo = new ControleVelocidade();
		
		salvar = true;
		status = 1;
		this.limite = 0;
		
		carregarOndas.inicializarOnda(1);

			timer = new Timer(0, this);
			timer.start();

	}
	
	
	public void paint(Graphics g) { 
		

		Graphics2D graficos = (Graphics2D) g;
		
		
		if(status == 1){
		ImageIcon inicio = new ImageIcon("res\\inicio.jpg");
		graficos.drawImage(inicio.getImage(), 0, 0, null);
		
		graficos.setColor(Color.WHITE);
		graficos.setFont(new Font("Arial", Font.PLAIN, 80));
		graficos.drawString("DARK WIZARD", 80, 180);
		graficos.setFont(new Font("Arial", Font.PLAIN, 30));
		graficos.drawString("Use as setas para mover o personagem e espaço para usar magias.", 80, 280);
		graficos.drawString("Com a estamina cheia, mantenha pressionado espaço para usar magias mais fortes.", 80, 340);
		graficos.drawString("No total existem 3 tipos de magias, que dependem do tempo de acumulo.", 80, 400);
		graficos.drawString("Só é possível acumular magia com o personagem parado, faça isso com sabedoria.", 80, 460);
		graficos.setFont(new Font("Arial", Font.PLAIN, 30));
		graficos.drawString("Pressione Enter para jogar", 80, 565);
		
		}
		

		if (status == 2) {
			
			graficos.drawImage(fundo, this.tremorX, 0, null);

			graficos.drawImage(wizard.getImagem(), wizard.getX(), wizard.getY(), this);

			List<Magia> magias = wizard.getMagias();

			for (int i = 0; i < magias.size(); i++) {

				Magia m = (Magia) magias.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

			}

			for (int i = 0; i < carregarOndas.inimigos.size(); i++) {

				Inimigo in = carregarOndas.inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

			}
			
			graficos.drawImage(sobre1, 1045 + this.tremorX, 0, null);
			graficos.drawImage(backBarra, 19, -5, null);
			
			if(wizard.getAcumulos() == 1){
			graficos.drawImage(acumulos1, 32, 9, null);
			}
			if(wizard.getAcumulos() == 2){
			graficos.drawImage(acumulos2, 32, 9, null);
			}
			if(wizard.getAcumulos() == 3){
			graficos.drawImage(acumulos3, 32, 9, null);
			}
			if(wizard.getAcumulos() == 4){
			graficos.drawImage(acumulos4, 32, 9, null);
			}
			if(wizard.getAcumulos() == 5){
			graficos.drawImage(acumulos5, 32, 9, null);
			}
			if(wizard.getAcumulos() == 6){
			graficos.drawImage(acumulos6, 32, 9, null);
			}
			
			graficos.setColor(Color.WHITE);
			graficos.setFont(new Font("Arial", Font.PLAIN, 40));

			graficos.drawImage(barraVida, 111, 35, wizard.barraVida(), 14, null);
			graficos.drawImage(barraEstamina, 111, 62, wizard.estamina() * 2, 14, null);
			graficos.drawString("0"+ wizard.getOnda(), 54, 70);
			graficos.setFont(new Font("Arial", Font.PLAIN, 16));
			graficos.drawString("PONTUAÇÃO: " + wizard.pontuacao(), 35, 678);
			
			
			
			if(carregarOndas.inimigos.size() == 0){
				
				graficos.setColor(Color.RED);
				graficos.setFont(new Font("Arial", Font.PLAIN, 20));
				
				
				if(timerOndas < 800){
				graficos.drawString("PRIMEIRA ONDA DE INIMIGOS DERROTADA!", 450, 48);	
				timerOndas++;
				}
				
				if(timerOndas >= 800 && timerOndas < 1400){
					graficos.drawString("PREPARE-SE PARA A SEGUNDA ONDA!", 450, 48);	
					timerOndas++;
					
					if(timerOndas == 1400){
						wizard.setOnda(2);
						carregarOndas.inicializarOnda(2);
						timerOndas = 1405;
					}
				}
				
				if(carregarOndas.inimigos.size() == 0){
					if(timerOndas >= 1405 && timerOndas < 2400){
						graficos.drawString("SEGUNDA ONDA DE INIMIGOS DERROTADA!", 450, 48);	
						timerOndas++;
				}
					
					
					if(timerOndas >= 2400 && timerOndas < 3000){
						graficos.drawString("PREPARE-SE PARA A ÚLTIMA ONDA!", 450, 48);	
						timerOndas++;
						
						if(timerOndas == 3000){
							wizard.setOnda(3);
							carregarOndas.inicializarOnda(3);
							timerOndas = 3005;
						}
					}
					
				
				}
			}				
			
			
		}
		
		if(status == 3){
			
			ImageIcon fimJogo = new ImageIcon("res\\gameover.jpg");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			graficos.setColor(Color.WHITE);
			graficos.setFont(new Font("Arial", Font.PLAIN, 85));
			graficos.drawString("FIM DE JOGO", 100, 130);
			graficos.setFont(new Font("Arial", Font.PLAIN, 45));
			graficos.drawString("Sua Pontuação: " + wizard.pontuacao(), 100, 235);
			graficos.drawString("Melhores Pontuações:", 100, 340);
			graficos.drawString("" + salvarJogo.getPosicao1(), 100, 420);
			graficos.drawString("" + salvarJogo.getPosicao2(), 100, 470);
			graficos.drawString("" + salvarJogo.getPosicao3(), 100, 520);
			graficos.drawString("Pressione Enter para continuar", 630, 615);
			
			
			
			
		}

		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	
		if (wizard.getOnda() == 3 && carregarOndas.inimigos.size() == 0 || wizard.vida() == 0) {
			
			wizard.setVisible(false);
			status = 3;
			
			if(this.salvar == true){
			try {
				salvarJogo.lerDados("Paulo", wizard.pontuacao());
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.salvar = false;
			}
		}

		List<Magia> magias = wizard.getMagias();

		for (int i = 0; i < magias.size(); i++) {

			Magia m = (Magia) magias.get(i);

			if (m.isVisivel()) {
				m.mover();

			} else {
				magias.remove(i);
			}

		}

		for (int i = 0; i < carregarOndas.inimigos.size(); i++) {

			Inimigo in = carregarOndas.inimigos.get(i);

			if (in.isVisivel()) {
				in.mover();

			} else {
				carregarOndas.inimigos.remove(i);
			}

		}

		wizard.mover();
		checarColisoes();
		terremoto();
		repaint();
		velocidadeJogo.sleep();
	}
	
	public void checarColisoes() {
		
		if(status == 2){
		Rectangle formaWizard = wizard.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMagia;

		for (int i = 0; i < carregarOndas.inimigos.size(); i++) {

			Inimigo tempInimigo = carregarOndas.inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();

			if (formaWizard.intersects(formaInimigo)) {
				

				wizard.getDano(22);
				tempInimigo.setVisivel(false);
					wizard.setPontos(-100);
				
			}

		}

		List<Magia> magias = wizard.getMagias();

		for (int i = 0; i < magias.size(); i++) {
			Magia tempMagia = magias.get(i);
			formaMagia = tempMagia.getBounds();
			
			
			if(tempMagia.getTipo() == 3 && tempMagia.getX() == Magia.getLarguraMagia()){
				tremer = true;
			}			
			

			for (int j = 0; j < carregarOndas.inimigos.size(); j++) {

				Inimigo tempInimigo = carregarOndas.inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();

				if (formaMagia.intersects(formaInimigo)) {

					if(tempMagia.getTipo() == 2 || tempMagia.getTipo() == 3){
						tempInimigo.setVisivel(false);
					}else{
						tempInimigo.setVisivel(false);
						tempMagia.setVisivel(false);
					}					

					wizard.setPontos(125);

					}				
				
				}

			}
		
		}
	}
	
	public void terremoto(){
	
		
			if(tremer == true){
			this.tremorX+=10;
			if(tremorX == 40){
				this.tremorX = -40;
				this.limite++;
			}
			
			if(this.limite == 5){
				this.tremorX = 0;
				this.limite = 0;
				tremer = false;			
			}

		}
	}
	
	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				timerOndas = 0;
				salvar = true;
				status = 2;
				wizard = new Wizard();
				carregarOndas.inicializarOnda(1);
				
			}
			wizard.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			wizard.keyReleased(e);
			velocidadeJogo.keyReleased(e);
		}

	}

}