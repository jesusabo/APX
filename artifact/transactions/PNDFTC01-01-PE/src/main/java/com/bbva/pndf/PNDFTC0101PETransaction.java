package com.bbva.pndf;

import com.bbva.elara.domain.transaction.Severity;
import com.bbva.elara.domain.transaction.response.HttpResponseCode;
import com.bbva.pndf.dto.student.ResponseStudentDTO;
import com.bbva.pndf.lib.rc01.PNDFRC01;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MOSTRAR EL DOMINIO
 *
 */
public class PNDFTC0101PETransaction extends AbstractPNDFTC0101PETransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(PNDFTC0101PETransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		PNDFRC01 pndfRC01 = this.getServiceLibrary(PNDFRC01.class);
		String id = this.getId();
		String grado = this.getGrado();

		if(validarNullOrEmpty(id,grado)){
			ResponseStudentDTO responseStudentDTO = pndfRC01.executeGetStudent(id,grado);
			if(responseStudentDTO!=null){
				this.setAlumno(responseStudentDTO);
				this.setSeverity( Severity.OK);
				this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_200);
			} else {
				this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_400);
				this.setSeverity(Severity.ENR);
			}
		} else{
			this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_400);
			this.setSeverity(Severity.ENR);
		}

	}

	private boolean validarNullOrEmpty(String... datos){
		for(String dato : datos){
			if(dato==null || dato.isEmpty()){
				return false;
			}
		}
		return true;
	}

}
