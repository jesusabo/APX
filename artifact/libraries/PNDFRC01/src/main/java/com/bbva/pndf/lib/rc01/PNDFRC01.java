package com.bbva.pndf.lib.rc01;

import com.bbva.pndf.dto.student.ResponseStudentDTO;

/**
 * The  interface PNDFRC01 class...
 */
public interface PNDFRC01 {

	/**
	 * The execute method...
	 */
	public ResponseStudentDTO executeGetStudent(String id, String grado);

	public boolean executeActualizarEstado(String id);

}
