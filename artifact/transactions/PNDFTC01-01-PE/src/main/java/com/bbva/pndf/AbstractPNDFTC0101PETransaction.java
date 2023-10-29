package com.bbva.pndf;

import com.bbva.elara.transaction.AbstractTransaction;
import com.bbva.pndf.dto.student.ResponseStudentDTO;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractPNDFTC0101PETransaction extends AbstractTransaction {

	public AbstractPNDFTC0101PETransaction(){
	}


	/**
	 * Return value for input parameter id
	 */
	protected String getId(){
		return (String)this.getParameter("id");
	}

	/**
	 * Return value for input parameter grado
	 */
	protected String getGrado(){
		return (String)this.getParameter("grado");
	}

	/**
	 * Set value for ResponseStudentDTO output parameter alumno
	 */
	protected void setAlumno(final ResponseStudentDTO field){
		this.addParameter("alumno", field);
	}
}
