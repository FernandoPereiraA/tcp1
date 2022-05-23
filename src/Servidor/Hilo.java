package Servidor;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//Clase hilo dónde el servidor atiende al cliente

public class Hilo extends Thread {
	
	//Socket por el que se está atendiendo al cliente
	Socket sCliente;
	//fin del bucle
	boolean acertado=false;
	int numSecreto;
	
	//Constructor de la clase
	public Hilo(Socket socket, int num)
	{
		sCliente=socket;
		numSecreto=num;
		
	}
	
	//Método run de la clase, dónde se atenderá al cliente
	public void run()
	{
		//numero enviado por el clietne
		int numCliente;
		//mesanje resultado
		String resultado;
		try {
			InputStream auxLeer = sCliente.getInputStream();
			DataInputStream flujoEntrada = new DataInputStream(auxLeer);
			
			OutputStream auxEscribir = sCliente.getOutputStream();
			DataOutputStream flujoSalida= new DataOutputStream(auxEscribir);
			
			while(!acertado)
			{
				numCliente=flujoEntrada.readInt();
				
				if(numCliente>numSecreto)
				{
					resultado="El numero que buscas es menor!";
				}
				else if(numCliente<numSecreto)
				{
					resultado="El numero que buscas es mayor!";
				}
				else
				{
					resultado="Has acertado!";
					acertado=true;
				}
				
				System.out.println("El cliente responde "+numCliente);
				
				flujoSalida.writeUTF(resultado);
			}
				
			sCliente.close();
			System.out.println("Conexion finalizada!");
		} 
		
		catch(Exception e)
		{
			System.out.println("Error al comunicarse con el cliente");
			
			//Cerramos el socket
			try {
				sCliente.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

}


