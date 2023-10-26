package com.bbva.pndf;

import com.bbva.elara.transaction.AbstractTransaction;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractPNDFTC0101PETransaction extends AbstractTransaction {

	public AbstractPNDFTC0101PETransaction(){
	}


	/**
	 * Return value for input parameter codigo
	 */
	protected String getCodigo(){
		return (String)this.getParameter("codigo");
	}

	/**
	 * Set value for String output parameter codigo
	 */
	protected void setCodigo(final String field){
		this.addParameter("codigo", field);
	}
}
