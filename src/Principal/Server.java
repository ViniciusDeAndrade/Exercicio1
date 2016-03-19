package Principal;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;

//classe arquivo

public class Server {
	public static void main(String[] args)throws IOException {


		ServerSocket welcomeSocket = new ServerSocket(1313);
		Socket socket = welcomeSocket.accept();


		ObjectInputStream objetoSerializado = new ObjectInputStream(socket.getInputStream());
		FileOutputStream arquivo = new FileOutputStream("/home/vinicius/Área de Trabalho/arquivo-cliente.txt");

		byte []buf = new byte[4096];

		while(true){
			int len = objetoSerializado.read(buf);
			if(len == -1 )
				break;
			arquivo.write(buf,0, len);
		}




	}	


}
