package br.com.sicredi.simulacao.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.com.sicredi.simulacao.core.BaseTest;
import br.com.sicredi.simulacao.tests.RestricaoTest;
import br.com.sicredi.simulacao.tests.SimulacaoTest;


@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	RestricaoTest.class,
	SimulacaoTest.class
})

public class SuiteTest extends BaseTest {

}
