package com.github.ko2ic

import com.codahale.metrics.health.HealthCheckRegistry
import com.github.ko2ic.db.PersonRepository
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment
import io.dropwizard.setup.Environment
import org.hibernate.SessionFactory
import spock.lang.Specification;
import spock.lang.Unroll
import static org.mockito.Mockito.*;

@Unroll
class HelloWorldApplicationSpec extends Specification {
	
	def "register HelloWorldResource"() {
		given:
		Environment environment = mock(Environment)
//		HibernateBundle<HelloWorldConfiguration> bundle = mock(HibernateBundle)
//		SessionFactory sessionFactory = mock(SessionFactory)		
//		HealthCheckRegistry healthCheck = mock(HealthCheckRegistry)
		JerseyEnvironment jersey = mock(JerseyEnvironment)
		
		HelloWorldApplication fixture = new HelloWorldApplication()
		HelloWorldConfiguration config = new HelloWorldConfiguration("template","defaultName")

		when(environment.jersey()).thenReturn(jersey)		
//		when(bundle.sessionFactory).thenReturn(sessionFactory)
//		when(environment.healthChecks()).thenReturn(healthCheck)
		
		when:
		new HelloWorldApplication().run(config, environment)
	
		then:
		verify(jersey).register(any())
	}
}
