package br.ifms.dsd.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import br.ifms.dsd.classes.Senha;
import br.ifms.dsd.model.MyRemote;

public class MyRemoteClient {
	
	Scanner read = new Scanner(System.in);

	public static void main (String[] args){
			new MyRemoteClient().go();
	}
	
	public void go(){
		
		int status;
		String cliente;
		
		while(true){
			try {
				Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1",1099);
				MyRemote impl = (MyRemote) myRegistry.lookup("myMessage");
				
				System.out.println("\n************************************************\nInforme o nome do cliente: ");
				cliente = read.nextLine();
								
				System.out.println("Tipo de senha?\n1 - Prioritário\n2 - Comum");
				status = read.nextInt();
				read.nextLine();
				
				Senha senha = impl.gerarSenha(cliente, status);
				
				if(status == 1){
					System.out.println("************************************************"
							+ "\n*********Senha gerada*********\nCliente: " + senha.getCliente() + "\nStatus: Prioritário" + "\nSenha: " + senha.getSenha());
				}else{
					System.out.println("\nSenha gerada\nCliente: " + senha.getCliente() + "\nStatus: Não prioritário" + "\nSenha: " + senha.getSenha());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	

	
}
