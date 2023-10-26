package com.bbva.pndf;

import com.bbva.elara.domain.transaction.Severity;
import com.bbva.elara.domain.transaction.response.HttpResponseCode;
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
		String codigoSalida = pndfRC01.executeGetCodio(this.getCodigo());

		this.setCodigo(codigoSalida);
		this.setSeverity(Severity.OK);
		this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_200, Severity.OK);

	}

}
