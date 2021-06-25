package jogo;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class SalvarJogo {
	
	private int pontuacao1, pontuacao2, pontuacao3;
	
	

	

	public void lerDados(String nome, int pontos) throws Exception{
		
		File file = new File("configuracoes/pontuacoes.txt");
		
		if(file.exists() == false){
			
			PrintWriter writer = new PrintWriter(file);
			
			writer.println(pontos);
			writer.println("0");
			writer.println("0");
			
			writer.close();	
		}else{
			

			Scanner scanner = new Scanner(file);
			
			
			int pontuacao1 = scanner.nextInt();
			int pontuacao2 = scanner.nextInt();
			int pontuacao3 = scanner.nextInt();
			
			salvarDados(pontuacao1, pontuacao2, pontuacao3, pontos);
			
			scanner.close();
			
	
			

		}
		
	}
	
	
	public void salvarDados(int pontuacao1, int pontuacao2, int pontuacao3, int pontos) throws Exception{
		
		
		this.pontuacao1 = pontuacao1;
		this.pontuacao2 = pontuacao2;
		this.pontuacao3 = pontuacao3;
		
		File file = new File("configuracoes/pontuacoes.txt");
		PrintWriter writer = new PrintWriter(file);
		
		
		if(pontos > pontuacao1){
			writer.println(pontos);
			writer.println(pontuacao1);
			writer.println(pontuacao2);
		}else if(pontos > pontuacao2){
			writer.println(pontuacao1);
			writer.println(pontos);
			writer.println(pontuacao2);			
		}else if(pontos > pontuacao3){
			writer.println(pontuacao1);
			writer.println(pontuacao2);
			writer.println(pontos);			
		}else{
			writer.println(pontuacao1);
			writer.println(pontuacao2);
			writer.println(pontuacao3);
		}
		
		writer.close();
		
	}
	
	public int getPosicao1(){
		return this.pontuacao1;
	}
	public int getPosicao2(){
		return this.pontuacao2;
	}
	public int getPosicao3(){
		return this.pontuacao3;
	}
	
}