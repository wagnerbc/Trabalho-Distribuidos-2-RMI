package br.ifms.dsd.classes;

import java.io.Serializable;

public class Senha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7731192740353193521L;
	public String cliente;
	public int status;
	public int senha = 0;
	
	public Senha(String cliente, int status, int senha) {
		this.cliente = cliente;
		this.status = status;
		this.senha = senha;
	}

	public String getCliente() {
		return cliente;
	}

	public int getStatus() {
		return status;
	}

	public int getSenha() {
		return senha;
	}
	
	
	
	
	
}
