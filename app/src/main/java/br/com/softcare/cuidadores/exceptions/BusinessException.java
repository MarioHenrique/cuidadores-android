package br.com.softcare.cuidadores.exceptions;

public class BusinessException extends Exception{

	private static final long serialVersionUID = 8025936159584789848L;

	public BusinessException(String msg){
		super(msg);
	}
	
}
