package com.bbva.pndf.lib.rc01.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The PNDFRC01Impl class...
 */
public class PNDFRC01Impl extends PNDFRC01Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PNDFRC01Impl.class);

	/**
	 * The execute method...
	 */
	@Override
	public  String executeGetCodio(String codigo) {
		// TODO - Implementation of business logic
		LOGGER.info("[PNDFRC01-executeGetCodio] entrada codigo: {} ",codigo);

		String codigoSalida;
		if(codigo.equals("1")) {
			LOGGER.info("[PNDFRC01-executeGetCodio] entro a la opcion 1 ");
			codigoSalida = this.applicationConfigurationService.getProperty("codigo");
			LOGGER.info("[PNDFRC01-executeGetCodio] entro1: {} ",codigoSalida);
		}
		else {
			LOGGER.info(" [PNDFRC01-executeGetCodio] entro a otras opciones ");
			codigoSalida = "0";
			LOGGER.info(" [PNDFRC01-executeGetCodio]entro2: {} ",codigoSalida);
		}

		return codigoSalida;
	}
}
