package br.ifms.dsd.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import br.ifms.dsd.classes.Senha;
import br.ifms.dsd.model.MyRemote;
import br.ifms.dsd.model.MyRemotePanel;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

	private static final long serialVersionUID = 7302134094089606237L;
	ArrayList<Senha> senhas;
	
	int key = 0;
	int numCaixa = 0;
	
	Registry myRegistry;
	MyRemotePanel panel;
	
	
	public MyRemoteImpl() throws RemoteException, Exception {
		this.senhas = new ArrayList<Senha>();
		
		myRegistry = LocateRegistry.getRegistry("127.0.0.1",1100);
		panel = (MyRemotePanel) myRegistry.lookup("panelMessage");
	}
	
	public ArrayList<Senha> getListaSenhas(){
		return senhas;
	}
	

	public Senha gerarSenha(String cliente, int status){

		Senha senha = new Senha(cliente,status,++key);
		senhas.add(senha);
		
		return senha;
		
	}
	
	public Senha getSenha(String caixaAtendente){
		
		Senha temp = null;
				
		for(Senha senha : senhas){

			if(senha.getStatus() == 1){
				temp = senha;
				senhas.remove(senha);
				try {
					panel.printSenha(temp, caixaAtendente);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return temp;
			}
		}
		if(senhas.size() >= 1){
			temp = senhas.get(0);
			senhas.remove(senhas.get(0));
			
			try {
				panel.printSenha(temp, caixaAtendente);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return temp;
	}
	
	public String setNameCaixaAtendente(){
		
		String name = "Caixa 0" + (++numCaixa);
		
		return name;
	}
	
	
	public static void main (String[] args){
				
		try {
			
			new MyRemoteImpl();
			
			Registry registry = LocateRegistry.createRegistry(1099);			
			registry.rebind("myMessage", new MyRemoteImpl());
			
//			Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1",1100);
//			MyRemotePanel panel = (MyRemotePanel) myRegistry.lookup("panelMessage");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("(SERVER) System is ready...");
	}
}
