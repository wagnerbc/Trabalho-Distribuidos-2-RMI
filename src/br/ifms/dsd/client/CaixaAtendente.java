package br.ifms.dsd.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import br.ifms.dsd.classes.Senha;
import br.ifms.dsd.model.MyRemote;

public class CaixaAtendente {

	Scanner read = new Scanner(System.in);

	Registry myRegistry;
	MyRemote impl;
	
	String name;
	
	public CaixaAtendente() throws Exception {
		
		this.myRegistry = LocateRegistry.getRegistry("127.0.0.1",1099);
		this.impl = (MyRemote) myRegistry.lookup("myMessage");

		this.name = impl.setNameCaixaAtendente();
	}


	public void go(){
	
		String x;
		while(true){
			try {
				ArrayList<Senha> senhas = impl.getListaSenhas();
								
				System.out.println("Para iniciar novo atendimento digite ENTER");
				x = read.nextLine();
				
				Senha senhaAtendimento = impl.getSenha(name);
				
				if(senhaAtendimento != null){
					
					if(senhaAtendimento.status == 1){
						System.out.println("Próximo atendimento\nCliente: " + senhaAtendimento.cliente + "\nStatus: Prioritário\nSenha: " + senhaAtendimento.senha);
					}else{
						System.out.println("Próximo atendimento\nCliente: " + senhaAtendimento.cliente + "\nStatus: Comum\nSenha: " + senhaAtendimento.senha);
					}
					
				}else{
					System.out.println("Não existem clientes para serem atendidos.\n");
					
					while(senhas.size() == 0){
						senhas = impl.getListaSenhas();				
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main (String[] args) throws Exception{
		new CaixaAtendente().go();
	}
}

