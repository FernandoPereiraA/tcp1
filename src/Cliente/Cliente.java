package Cliente;
import java.io.*;
import java.net.*;
import java.util.Random;

class Cliente 
{
	static final String HOST = "localhost";
	static final int Puerto=2000;
	boolean acertado=false;
	
	public Cliente() 
	{
		try
		{
			int numCliente=0;
			String respuestaServer="";
			
			Socket sCliente = new Socket( HOST , Puerto );
			InputStream auxLeer = sCliente.getInputStream();
			DataInputStream flujoEntrada = new DataInputStream(auxLeer);
			
			OutputStream auxEscribir = sCliente.getOutputStream();
			DataOutputStream flujoSalida= new DataOutputStream(auxEscribir);
			
			while(!acertado)
			{
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				
				System.out.print("Escriba el numero: ");
				numCliente=Integer.parseInt(reader.readLine());
				
				flujoSalida.writeInt(numCliente);
				
				respuestaServer=flujoEntrada.readUTF();
				System.out.println(respuestaServer);
				acertado=(respuestaServer.equals("Has acertado!"));
			}
			
			sCliente.close();
		} 
		catch( Exception e ) 
		{
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] arg) 
	{
		new Cliente();
	}
}