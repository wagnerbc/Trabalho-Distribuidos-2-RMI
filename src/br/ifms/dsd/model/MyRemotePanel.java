package br.ifms.dsd.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.ifms.dsd.classes.Senha;

public interface MyRemotePanel extends Remote {

	public void printSenha(Senha senha, String caixaAtendente) throws RemoteException;
	
}
