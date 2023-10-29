package com.bbva.pndf.dto.student;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The ResponseStudentDTO class...
 */
public class ResponseStudentDTO implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	private String id;
	private String nombre;
	private String grado;
	private Date fecha_inscripcion;

	public ResponseStudentDTO(){}

	public ResponseStudentDTO(String id, String nombre, String grado, Date fecha_inscripcion) {
		this.id = id;
		this.nombre = nombre;
		this.grado = grado;
		this.fecha_inscripcion = fecha_inscripcion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public Date getFecha_inscripcion() {
		return fecha_inscripcion;
	}

	public void setFecha_inscripcion(Date fecha_inscripcion) {
		this.fecha_inscripcion = fecha_inscripcion;
	}
}
