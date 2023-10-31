package com.bbva.pndf.lib.rc01;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;
import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.DataIntegrityViolationException;
import com.bbva.apx.exception.db.DuplicateKeyException;
import com.bbva.apx.exception.db.NoResultException;


import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pndf.dto.student.ResponseStudentDTO;
import com.bbva.pndf.lib.rc01.impl.PNDFRC01Impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

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

	@InjectMocks
	private PNDFRC01Impl pndfRC01Impl;
	@Mock
	private JdbcUtils jdbcUtils;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}

	private Object getObjectIntrospection() throws Exception {
		Object result = this.pndfRC01;
		if (this.pndfRC01 instanceof Advised) {
			Advised advised = (Advised) this.pndfRC01;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
 	@Test
	public void executeTestGetStudentOK(){
		ResponseStudentDTO dummyMap;
		String id="prueba";
		String grado="prueba";
		Map<String, Object> request = new HashMap<>();
		request.put("ID",id);
		request.put("GRADO",grado);
		request.put("ESTADO_MATRICULA", "No matriculado");
		Mockito.when(jdbcUtils.queryForMap(Matchers.matches("pndf.select.estudiante"), Matchers.anyMap())).thenReturn(request);
		dummyMap = this.pndfRC01Impl.executeGetStudent(id,grado);
		Assert.assertNotNull(dummyMap);
		Assert.assertEquals("prueba", dummyMap.getId());
	}

	@Test
	public void executeTestGetStudentOK2(){
		ResponseStudentDTO dummyMap;
		String id="prueba";
		String grado="prueba";
		Map<String, Object> request = new HashMap<>();
		request.put("ID",id);
		request.put("GRADO",grado);
		request.put("ESTADO_MATRICULA", "Matriculado");
		Mockito.when(jdbcUtils.queryForMap(Matchers.matches("pndf.select.estudiante"), Matchers.anyMap())).thenReturn(request);
		dummyMap = this.pndfRC01Impl.executeGetStudent(id,grado);
		Assert.assertNotNull(dummyMap);
		Assert.assertEquals("prueba", dummyMap.getId());
	}

	@Test
	public void executeTestGetStudentOK3(){
		ResponseStudentDTO dummyMap;
		String id="";
		String grado="";
		Map<String, Object> request = new HashMap<>();
		request.put("ID",id);
		request.put("GRADO",grado);
		request.put("ESTADO_MATRICULA", "No matriculado");
		Mockito.when(jdbcUtils.queryForMap(Matchers.matches("pndf.select.estudiante"), Matchers.anyMap())).thenReturn(new HashMap<>());
		dummyMap = this.pndfRC01Impl.executeGetStudent(id,grado);
		Assert.assertNull(dummyMap);
		//Assert.assertEquals("", dummyMap.getId());
	}

	@Test
	public void executeTestGetStudentNO(){

		Mockito.when(jdbcUtils.queryForMap(Matchers.matches("pndf.select.estudiante"), Matchers.anyMap())).thenThrow(new NoResultException("Error"));
		ResponseStudentDTO dummyMap = this.pndfRC01Impl.executeGetStudent("","");
		Assert.assertNull(dummyMap);
		//Assert.assertEquals(1, context.getAdviceList().size());
	}
	@Test
	public void executeTestUpdateStudentOK(){
		boolean dummy;
		String id="prueba";
		Map<String, Object> request = new HashMap<>();
		request.put("ESTADO_MATRICULA", "Matriculado");
		request.put("ID",id);
		Mockito.when(jdbcUtils.update(Matchers.matches("pndf.update.estudiante"), Matchers.anyMap())).thenReturn(1);
		dummy = this.pndfRC01Impl.executeActualizarEstado(id);
		Assert.assertNotNull(dummy);
		Assert.assertEquals(true, dummy);
	}

	@Test
	public void executeTestUpdateStudentNO(){
		boolean dummy;
		String id=null;
		Mockito.when(jdbcUtils.update(Matchers.matches("pndf.update.estudiante"), Matchers.anyMap())).thenThrow(NoResultException.class);
		dummy = this.pndfRC01Impl.executeActualizarEstado(id);
		Assert.assertNotNull(dummy);
		//Assert.assertEquals(false, dummy);
		Assert.assertEquals(1, context.getAdviceList().size());
	}

	@Test
	public void executeTestUpdateStudentNOData(){
		boolean dummy;
		String id=null;
		Mockito.when(jdbcUtils.update(Matchers.matches("pndf.update.estudiante"), Matchers.anyMap())).thenThrow(DataIntegrityViolationException.class);
		dummy = this.pndfRC01Impl.executeActualizarEstado(id);
		Assert.assertNotNull(dummy);
		//Assert.assertEquals(false, dummy);
		Assert.assertEquals(1, context.getAdviceList().size());
	}
}
