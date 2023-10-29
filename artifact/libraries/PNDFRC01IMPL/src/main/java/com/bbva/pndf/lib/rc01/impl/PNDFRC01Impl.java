package com.bbva.pndf.lib.rc01.impl;

import com.bbva.apx.exception.db.DataIntegrityViolationException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.pndf.dto.student.ResponseStudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The PNDFRC01Impl class...
 */
public class PNDFRC01Impl extends PNDFRC01Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PNDFRC01Impl.class);
	private static final String SELECT_ESTUDIANTE = "pndf.select.estudiante";
	private static final String UPDATE_ESTUDIANTE = "pndf.update.estudiante";
	private static final String ERR_PROCESS_DB = "No procesa query";
	private static final String ERR_NO_RESULTS = "No encuentra resultado";
	private static final String ID = "id";
	private static final String GRADO = "grado";
	private static final String ESTADO_NO_MATRICULADO = "No matriculado";

	@Override
	public ResponseStudentDTO executeGetStudent(String id, String grado) {
		ResponseStudentDTO alumno = null;
		LOGGER.info("[PNDFRC01-executeGetStudent] entrada id: {} ",id);
		LOGGER.info("[PNDFRC01-executeGetStudent] entrada grado: {} ",grado);

		Map<String, Object> request = new HashMap<>();
		request.put(ID, id);
		request.put(GRADO, grado);
		Map<String, Object> objResponseSQL;
		try {
			objResponseSQL = this.jdbcUtils.queryForMap(SELECT_ESTUDIANTE,request);
			LOGGER.info("[PNDFRC01] {} SELECT ok info un parametro", objResponseSQL);
			if(!objResponseSQL.isEmpty()){
				String estado_actual = (String) objResponseSQL.get("ESTADO_MATRICULA");
				if(estado_actual.equals(ESTADO_NO_MATRICULADO)){
					executeActualizarEstado(id);
				}
				alumno = new ResponseStudentDTO((String) objResponseSQL.get("ID"),
						(String) objResponseSQL.get("NOMBRE"),
						(String) objResponseSQL.get("GRADO"),
						(Date) objResponseSQL.get("FECHA_INSCRIPCION"));
			} else{
				LOGGER.info("[PNDFRC01] Error not found 1 filter");
				this.addAdvice(ERR_NO_RESULTS);
			}

		} catch (NoResultException noResultException) {
			LOGGER.info("Error select", noResultException.getMessage());
			this.addAdvice(ERR_PROCESS_DB);
		}
		return alumno;
	}

	@Override
	public boolean executeActualizarEstado(String id){
		boolean valor = false;
		Map<String, Object> request = new HashMap<>();
		request.put(ID, id);
		LOGGER.info("[PNDFRC01] Map: UPDATE {}", request);
		try {
			int updatesRows = this.jdbcUtils.update(UPDATE_ESTUDIANTE, request);
			LOGGER.info("[PNDFRC01] {} Registro name actualizado", updatesRows);
			return updatesRows > 0;
		} catch (NoResultException noResultException) {
			LOGGER.info(ERR_NO_RESULTS, noResultException.getMessage());
			this.addAdvice(ERR_PROCESS_DB);
		} catch (DataIntegrityViolationException dataIntegrityViolationException){
			LOGGER.info(ERR_PROCESS_DB, dataIntegrityViolationException.getMessage());
			this.addAdvice(ERR_PROCESS_DB);
		}
		return valor;
	}
}
