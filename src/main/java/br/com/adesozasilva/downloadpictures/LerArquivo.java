package br.com.adesozasilva.downloadpictures;

import java.io.FileReader;
import java.util.Scanner;

public class LerArquivo {


	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileReader("C:\\Users\\asouzsil\\IdeaProjects\\download-pictures\\src\\main\\resources\\urls.txt"))
                .useDelimiter("\\||\\n");
		while(sc.hasNextLine()) {
			String linha = sc.nextLine();
			String fileName = linha.substring(linha.length()- 13, linha.length());
			PegarFotos.get(linha, fileName);
			
		}
		sc.close();
	}



}
