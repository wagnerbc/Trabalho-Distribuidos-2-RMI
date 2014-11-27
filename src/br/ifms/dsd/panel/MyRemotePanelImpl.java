package br.ifms.dsd.panel;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import br.ifms.dsd.classes.Senha;
import br.ifms.dsd.model.MyRemote;
import br.ifms.dsd.model.MyRemotePanel;
import br.ifms.dsd.server.MyRemoteImpl;

public class MyRemotePanelImpl extends UnicastRemoteObject implements
		MyRemotePanel {

	private static final long serialVersionUID = 3415481698937634599L;

	public MyRemotePanelImpl() throws RemoteException {
		
	}
	
	public void printSenha(Senha senha, String caixaAtendente){
		
		
		if(senha.getStatus() == 1){
			System.out.println("\nATENÇÃO\nCliente: " + senha.getCliente() + "\nStatus: Prioritário" + "\nSenha: " + senha.getSenha() + "\nAtendimento no " + caixaAtendente);
		}else{
			System.out.println("\nATENÇÃO\nCliente: " + senha.getCliente() + "\nStatus: Não prioritário" + "\nSenha: " + senha.getSenha() + "\nAtendimento no " + caixaAtendente);
		}
	}
	
	
	public static void main (String[] args){
		try {
			Registry registry = LocateRegistry.createRegistry(1100);			
			registry.rebind("panelMessage", new MyRemotePanelImpl());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("(PANEL) System is ready...");
	}

}
