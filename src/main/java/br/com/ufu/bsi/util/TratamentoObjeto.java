package br.com.ufu.bsi.util;

public class TratamentoObjeto {

	private Object objectA;
	
	private Object objectB;

	public TratamentoObjeto(Object objectA, Object objectB) {
		super();
		this.objectA = objectA;
		this.objectB = objectB;
	}

	public Object getObjectA() {
		return objectA;
	}

	public void setObjectA(Object objectA) {
		this.objectA = objectA;
	}

	public Object getObjectB() {
		return objectB;
	}

	public void setObjectB(Object objectB) {
		this.objectB = objectB;
	}

}
