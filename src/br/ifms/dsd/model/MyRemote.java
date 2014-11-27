package br.ifms.dsd.model;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import br.ifms.dsd.classes.Senha;


public interface MyRemote extends Remote {
	
	public Senha gerarSenha(String cliente, int status) throws RemoteException;

	public Senha getSenha(String caixaAtendente) throws RemoteException;
	
	public String setNameCaixaAtendente() throws RemoteException;
	
	public ArrayList<Senha> getListaSenhas() throws RemoteException;
	
}
