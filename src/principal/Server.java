package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		// Criando servidor
		Server server = new Server();

		// Aguardar conexao de cliente para transferia
		server.esperaCliente();
	}

	public void esperaCliente() {
		// Checa se a transferencia foi completada com sucesso
		OutputStream outputStream = null;
		ServerSocket serverSocket = null;
		FileInputStream fileInputStream = null;

		try {
			// Abrindo porta para conexao de clients
			serverSocket = new ServerSocket(13267);
			System.out.println("Porta de conexao aberta 13267");

			// Cliente conectado
			Socket socket = serverSocket.accept();
			System.out.println("Conexao recebida pelo cliente");

			// Criando tamanho de leitura
			byte[] buffer = new byte[1024];
			int bytesLidos;

			// Criando arquivo que sera transferido pelo servidor
			File file = new File("/home/vinicius/Área de Trabalho/arquivo-servidor.txt");
			fileInputStream = new FileInputStream(file);
			System.out.println("Lendo arquivo /home/vinicius/Área de Trabalho/arquivo-servidor.txt");
			
			
			// Criando canal de transferencia
			outputStream = socket.getOutputStream();

			// Lendo arquivo criado e enviado para o canal de transferencia
			System.out.println("Enviando Arquivo...");
			while ((bytesLidos = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesLidos);
				outputStream.flush();
			}
			System.out.println("Arquivo Enviado!");
		} catch (Exception exception) {
			// Mostra erro no console
			exception.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
		}
	}
}