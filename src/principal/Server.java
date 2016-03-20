package principal;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import arquivo.Arquivo;

import java.net.ServerSocket;

//classe arquivo

public class Server {
	public static void main(String[] args)throws IOException {


		ServerSocket welcomeSocket = new ServerSocket(1313);
		System.out.println("Servidor on!");
		Socket socket = welcomeSocket.accept();
		ObjectInputStream objetoVindoDoCliente = new ObjectInputStream(socket.getInputStream());
		ObjectOutputStream objetoQueVaiProCliente = new ObjectOutputStream(socket.getOutputStream());
		
		
		
		//FileOutputStream arquivo = new FileOutputStream("/home/vinicius/Área de Trabalho/arquivo-cliente.txt");
		//receba do cliente
		FileInputStream arquivoEmBytes = null;
		try {
			arquivoEmBytes = (FileInputStream) objetoVindoDoCliente.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//processamento da mensagem
		byte []buf = new byte[4096];
		while(true){
			int len = arquivoEmBytes.read(buf);
			if(len==-1)
				break;
			objetoQueVaiProCliente.write(buf,0,len);
		}		
		
		//responde pro cliente
				
		
		objetoQueVaiProCliente.close();
		welcomeSocket.close();
		socket.close();



		/**
		while(true){
			int len = objetoSerializado.read(buf);
			if(len == -1 )
				break;
			arquivo.write(buf,0, len);
		}
		 **/



	}	


}
