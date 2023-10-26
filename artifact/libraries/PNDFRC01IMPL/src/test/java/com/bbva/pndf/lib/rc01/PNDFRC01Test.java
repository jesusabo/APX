package com.bbva.pndf.lib.rc01;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/PNDFRC01-app.xml",
		"classpath:/META-INF/spring/PNDFRC01-app-test.xml",
		"classpath:/META-INF/spring/PNDFRC01-arc.xml",
		"classpath:/META-INF/spring/PNDFRC01-arc-test.xml" })
public class PNDFRC01Test {

	@Spy
	private Context context;

	@Resource(name = "pndfRC01")
	private PNDFRC01 pndfRC01;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.pndfRC01;
		if(this.pndfRC01 instanceof Advised){
			Advised advised = (Advised) this.pndfRC01;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
 	@Test
	public void executeTestNotNull(){
		when(applicationConfigurationService.getProperty("codigo")).thenReturn("1");

		String codigo = pndfRC01.executeGetCodio("1");

		Assert.assertEquals("1",codigo);
	}
	@Test
	public void executeTestDiferenteUno(){
		when(applicationConfigurationService.getProperty("codigo")).thenReturn("2");

		String codigo = pndfRC01.executeGetCodio("3");

		Assert.assertEquals("0",codigo);
	}
}
