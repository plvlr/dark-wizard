package jogo;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarregarOndas {
	
	protected List<Inimigo> inimigos;
	
	public void getOnda(int onda) throws Exception{
		
		inimigos = new ArrayList<Inimigo>();
		
		File file = new File("configuracoes/inimigos" + onda + ".txt");
		
		
		if(file.exists() == false){
			
			PrintWriter writer = new PrintWriter(file);
			
				writer.println("2000");
				writer.println("320");
				writer.println(onda);
				writer.println(onda);
				writer.println("LINHAS 1 : X / 2 : Y / 3 : TIPO (1-3) / 4 : VELOCIDADE");
				
				writer.close();	
			}
		
		LineNumberReader lnr = new LineNumberReader(new FileReader(file));
		lnr.skip(Long.MAX_VALUE);
		int retorno = lnr.getLineNumber();
		

		Scanner scanner = new Scanner(file);
			
			int coordenadas = retorno / 4;
			
			for(int i = 0; i < coordenadas; i++){
			
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int tipo = scanner.nextInt();
			int velocidade = scanner.nextInt();
			
			inimigos.add(new Inimigo(x, y, tipo, velocidade));
			
			}
			
			
			scanner.close();
	}
	

	public void inicializarOnda(int onda) {

			try {
				getOnda(onda);
			} catch (Exception e) {
				e.printStackTrace();
			
		}
	}
	
	

}
