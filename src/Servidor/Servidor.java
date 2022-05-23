package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

//Clase servidor que atiende a un cliente para decirle si un número es par o es impar
public class Servidor {

	//Puerto por el que el servidor escuchará al cliente
	static final int PORT=2000;
	boolean acertado=false;
	
	
	//Método principal del servidor (lanzara a su propio constructor)
	public static void main(String[] args) throws IOException {
		new Servidor();
	}
	
	//Constructor del servidor
	public Servidor() throws IOException
	{
		

		//Creamos el socket por el que el servidor se mantendrá a la escucha de clientes
		ServerSocket skServidor = new ServerSocket(PORT);
		
		//Escribe por pantalla el puerto al que va a escuchar
		System.out.println("Puerto de escucha del servidor: "+PORT);
				
		//genro el numeor secreto
		Random randomGenerator = new Random();
		int numSecreto = randomGenerator.nextInt(100);
		System.out.println("Empieza el juego, el numero es "+numSecreto);
		
		//El servidor escuchará aquí a un potencial cliente
		Socket sCliente = skServidor.accept();
		
			//Cuando termine la escucha, lanzará un hilo para atenderlo
			new Hilo(sCliente,numSecreto).start();
		}
	}


