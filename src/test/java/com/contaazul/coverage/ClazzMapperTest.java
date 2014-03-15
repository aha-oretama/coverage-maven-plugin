package com.contaazul.coverage;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.contaazul.coverage.cobertura.ClazzMapper;
import com.contaazul.coverage.cobertura.ClazzMapperImpl;
import com.contaazul.coverage.cobertura.Parser;
import com.contaazul.coverage.cobertura.entity.Clazz;
import com.contaazul.coverage.cobertura.entity.Coverage;

public class ClazzMapperTest {
	private Coverage coverage;
	private ClazzMapper mapper;

	@Before
	public void init() {
		coverage = new Parser().parse( new File( "src/test/resources/coverage.xml" ) );
		mapper = new ClazzMapperImpl( coverage, "src/main/java" );
	}

	@Test
	public void testGetClazz() throws Exception {
		Clazz clazz = mapper.getClazz( "src/main/java/com/contaazul/coverage/CoveragePullRequestMojo" );
		assertTrue( clazz instanceof Clazz );
		assertTrue( clazz.getName().equals( "com.contaazul.coverage.CoveragePullRequestMojo" ) );
	}

	@Test
	public void testGetClazzDotJava() throws Exception {
		Clazz clazz = mapper.getClazz( "src/main/java/com/contaazul/coverage/CoveragePullRequestMojo.java" );
		assertTrue( clazz instanceof Clazz );
		assertTrue( clazz.getName().equals( "com.contaazul.coverage.CoveragePullRequestMojo" ) );
	}

	@Test
	public void getInvalidClazzPath() throws Exception {
		Clazz clazz = mapper.getClazz( "xsrc/main/java/com/contaazul/coverage/CoveragePullRequestMojo" );
		assertNull( clazz );
	}

	@Test
	public void testWrongPackageName() throws Exception {
		Clazz clazz = mapper.getClazz( "src/main/java/com/contaazul/coveragersrs/CoveragePullRequestMojo" );
		assertNull( clazz );
	}

	@Test
	public void getInvalizClazzName() throws Exception {
		Clazz clazz = mapper.getClazz( "src/main/java/com/contaazul/coverage/CoveragePullRequestMojozz" );
		assertNull( clazz );
	}
}
