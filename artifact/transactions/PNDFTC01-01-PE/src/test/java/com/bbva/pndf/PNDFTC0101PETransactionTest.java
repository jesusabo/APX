package com.bbva.pndf;

import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.elara.domain.transaction.TransactionParameter;
import com.bbva.elara.domain.transaction.request.TransactionRequest;
import com.bbva.elara.domain.transaction.request.body.CommonRequestBody;
import com.bbva.elara.domain.transaction.request.header.CommonRequestHeader;
import com.bbva.elara.domain.transaction.response.HttpResponseCode;
import com.bbva.elara.test.osgi.DummyBundleContext;

import java.util.*;
import javax.annotation.Resource;

import com.bbva.pndf.dto.student.ResponseStudentDTO;
import com.bbva.pndf.lib.rc01.PNDFRC01;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test for transaction PNDFTC0101PETransaction
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/elara-test.xml",
		"classpath:/META-INF/spring/PNDFTC0101PETest.xml" })
public class PNDFTC0101PETransactionTest {

	@Autowired
	private PNDFTC0101PETransaction transaction;

	@Resource(name = "dummyBundleContext")
	private DummyBundleContext bundleContext;

	@Mock
	private CommonRequestHeader header;

	@Mock
	private TransactionRequest transactionRequest;

	private final HttpResponseCode[] finalResponseCode = {null};
	private final Severity[] finalSeverity= {null};
	@Mock
	private PNDFRC01 libInt;
	@Spy
	private Context context;

	@Before
	public void initializeClass() throws Exception {
		// Initializing mocks
		MockitoAnnotations.initMocks(this);
		// Start BundleContext
		this.transaction.start(bundleContext);
		// Setting Context
		this.transaction.setContext(context);
		this.transaction.getContext().setLanguageCode("ES");

		transaction = new PNDFTC0101PETransaction() {
			@Override
			protected <T> T getServiceLibrary(Class<T> serviceInterface) {
				return (T) libInt;
			}

			@Override
			protected void setHttpResponseCode(HttpResponseCode httpStatusCode, Severity severity) {
				finalResponseCode[0] = httpStatusCode;
				finalSeverity[0] = severity;
			}

			@Override
			protected Object getParameter(String field) {
				return field.equals("accessParameters") ? new ArrayList<>() : super.getParameter(field);
			}
		};
		// Set Body
		transactionRequest = Mockito.mock(TransactionRequest.class);
		CommonRequestBody commonRequestBody = new CommonRequestBody();
		commonRequestBody.setTransactionParameters(new ArrayList<TransactionParameter>());
		this.transactionRequest.setBody(commonRequestBody);
		Mockito.when(transactionRequest.getRestfulMethod()).thenReturn(null);
		// Set Header Mock
		this.transactionRequest.setHeader(header);
		// Set TransactionRequest
		this.transaction.getContext().setTransactionRequest(transactionRequest);
	}

	@Test
	public void testNotNull(){
		String id = "id";
		String grado = "grado";
		this.addParameter("id", id);
		this.addParameter("grado", grado);
		Mockito.when(libInt.executeGetStudent(id,grado)).thenReturn(new ResponseStudentDTO("x","x","x",new Date()));
		this.transaction.execute();
		Assert.assertEquals(0, this.transaction.getContext().getAdviceList().size());

	}

	@Test
	public void testNotNull2(){
		String id = "id";
		String grado = "grado";
		this.addParameter("id", id);
		this.addParameter("grado", grado);
		Mockito.when(libInt.executeGetStudent(id,grado)).thenReturn(new ResponseStudentDTO("x","x","x",new Date()));
		this.transaction.execute();
		Assert.assertEquals(0, this.transaction.getContext().getAdviceList().size());

	}

	@Test
	public void testNull(){
		String id = null;
		String grado = null;
		this.addParameter("id", id);
		this.addParameter("grado", grado);
		Mockito.when(libInt.executeGetStudent(id,grado)).thenReturn(null);
		ResponseStudentDTO responseStudentDTO = libInt.executeGetStudent(id,grado);
		this.transaction.execute();
		Assert.assertNull(responseStudentDTO);
		Assert.assertEquals(Severity.ENR, this.transaction.getSeverity());
	}

	@Test
	public void testNull2(){
		String id = "id";
		String grado = "grado";
		this.addParameter("id", id);
		this.addParameter("grado", grado);
		Mockito.when(libInt.executeGetStudent(id,grado)).thenReturn(null);
		ResponseStudentDTO responseStudentDTO = libInt.executeGetStudent(id,grado);
		this.transaction.execute();
		Assert.assertNull(responseStudentDTO);
		Assert.assertEquals(Severity.ENR, this.transaction.getSeverity());
	}

	// Add Parameter to Transaction
	private void addParameter(final String parameter, final Object value) {
		final TransactionParameter tParameter = new TransactionParameter(parameter, value);
		transaction.getContext().getParameterList().put(parameter, tParameter);
	}

	// Get Parameter from Transaction
	private Object getParameter(final String parameter) {
		final TransactionParameter param = transaction.getContext().getParameterList().get(parameter);
		return param != null ? param.getValue() : null;
	}
}
