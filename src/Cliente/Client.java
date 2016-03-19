package Cliente;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException, 
	ClassNotFoundException{


		// cria socket
		Socket clientSocket = new Socket("localhost",1313);

		String localDoArquivo = "/home/vinicius/Área de Trabalho/arquivo-cliente.txt";
		//instancia o arquivo
		File file = new File(localDoArquivo);

		String requisicao,resposta = null;


		try{
			//efetivamente cria o arquivo
			file.createNewFile();
		}catch( IOException e){
			e.printStackTrace();
		}

		// cria arquivo a ser serializado e mandado para o servidor
		FileInputStream arquivo = new FileInputStream(file);

		// Começar a tratar o arquivo a ser enviado
		ObjectOutputStream objetoParaOServidor = new ObjectOutputStream(clientSocket.getOutputStream());


		//começar a tratar o arquivo a ser recebido	
		ObjectInputStream objetoVindoDoServidor = new ObjectInputStream(clientSocket.getInputStream());

		byte [] buffer = new byte [4096];

		while(true){
			int len = arquivo.read(buffer);
			if(len == -1)
				break;
			objetoParaOServidor.write(buffer,0,len);
			objetoParaOServidor.flush();

		}





	}




}
