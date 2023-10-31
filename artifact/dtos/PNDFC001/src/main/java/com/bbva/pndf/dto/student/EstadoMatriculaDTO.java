package com.bbva.pndf.dto.student;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The EstadoMatriculaDTO class...
 */
public class EstadoMatriculaDTO implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	private String estadoMatricula;

	public EstadoMatriculaDTO(String estadoMatricula) {
		this.estadoMatricula = estadoMatricula;
	}

	public String getEstadoMatricula() {
		return estadoMatricula;
	}

	public void setEstadoMatricula(String estadoMatricula) {
		this.estadoMatricula = estadoMatricula;
	}
}
