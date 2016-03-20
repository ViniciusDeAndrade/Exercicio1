package cliente;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		// Criando Classe cliente para receber arquivo
		Client cliente = new Client();

		// Solicitando arquivo
		cliente.getArquidoDoServidor();
	}

	private void getArquidoDoServidor() {
		Socket socket = null;
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;

		try {
			// Criando conexão com o servidor
			System.out.println("Conectando ao Servidor na 13267");
			socket = new Socket("127.0.0.1", 13267);
			inputStream = socket.getInputStream();

			// Cria arquivo local no cliente
			fileOutputStream = new FileOutputStream(new File("/home/vinicius/Área de Trabalho/arquivo-cliente.txt"));
			System.out.println("Arquivo Criado /home/vinicius/Área de Trabalho/arquivo-cliente.txt");

			// Prepara variaveis para transferencia
			byte[] buffer = new byte[1024];
			int bytesLidos;

			// Copia conteudo do canal
			System.out.println("Recebendo Arquivo...");
			while ((bytesLidos = inputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, bytesLidos);
				fileOutputStream.flush();
			}
			System.out.println("Arquivo Recebido!");
		} catch (Exception exception) {
			// Mostra erro no console
			exception.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
		}
	}
}