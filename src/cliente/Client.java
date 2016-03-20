package cliente;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import arquivo.Arquivo;

public class Client {
	public static void main(String[] args) throws IOException, 
	ClassNotFoundException{

		// cria socket e streams
		Socket clientSocket = new Socket("localhost",1313);
		ObjectOutputStream objetoParaOServidor = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream objetoVindoDoServidor = new ObjectInputStream(clientSocket.getInputStream());

		//cria e instancia o arquivo
		String localDoArquivo = "/home/vinicius/Área de Trabalho/arquivo-cliente.txt";
		Arquivo a = new Arquivo(localDoArquivo);
		File file = new File(a.getDiretorio());

		try{
			//efetivamente cria o arquivo
			file.createNewFile();
		}catch( IOException e){
			e.printStackTrace();
		}

		// transforma em bytes 
		FileInputStream arquivo = new FileInputStream(file);

		//manda para o servidor
		
		byte [] buffer = new byte[4096];
		while(true){
			int len = arquivo.read(buffer);
			if(len == -1)
				break;
			objetoParaOServidor.writeObject(arquivo);
			objetoParaOServidor.flush();
		}
		
		//recebe do servidor
		String resposta = null;
		resposta = (String) objetoVindoDoServidor.readObject();

		System.out.println("---> " + resposta );

		objetoParaOServidor.close();
		objetoVindoDoServidor.close();
		clientSocket.close();



		/**byte [] buffer = new byte [4096];

		while(true){
			int len = arquivo.read(buffer);
			if(len == -1)
				break;
			objetoParaOServidor.write(buffer,0,len);
			objetoParaOServidor.flush();
		}
		 **/
	}

}
