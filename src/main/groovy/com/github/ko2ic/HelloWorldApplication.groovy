package com.github.ko2ic

import io.dropwizard.Application
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.hibernate.HibernateBundle
import io.dropwizard.hibernate.SessionFactoryFactory
import io.dropwizard.java8.Java8Bundle
import io.dropwizard.java8.auth.basic.BasicAuthProvider
import io.dropwizard.jdbi.DBIFactory
import io.dropwizard.migrations.MigrationsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.views.ViewBundle

import org.skife.jdbi.v2.DBI

import com.github.ko2ic.auth.ExampleAuthenticator
import com.github.ko2ic.core.Person
import com.github.ko2ic.core.Template
import com.github.ko2ic.db.PersonJdbiRepository
import com.github.ko2ic.db.PersonRepository
import com.github.ko2ic.db.TempJdbiRepository
import com.github.ko2ic.health.TemplateHealthCheck
import com.github.ko2ic.resources.HelloWorldResource
import com.github.ko2ic.resources.PeopleJdbiResource
import com.github.ko2ic.resources.PeopleResource
import com.github.ko2ic.resources.ProtectedResource
import com.github.ko2ic.resources.TempJdbiResource
import com.github.ko2ic.resources.ViewResource

import com.google.common.collect.ImmutableList

class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    static void main(String[] args) {
        new HelloWorldApplication().run(args)
    }

    final HibernateBundle<HelloWorldConfiguration> hibernateBundle = new HibernateBundle<HelloWorldConfiguration>(ImmutableList.copyOf(Person),new SessionFactoryFactory()) {
        @Override
        DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
            return configuration.database
        }
    }

    @Override
    String getName() {
        return 'hello-world'
    }

    @Override
    void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // bootstrap.addCommand(new RenderCommand())
        bootstrap.addBundle(new Java8Bundle())
        bootstrap.addBundle(new AssetsBundle())
        bootstrap.addBundle(new MigrationsBundle<HelloWorldConfiguration>() {
            @Override
            DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return configuration.database
            }
        })
        bootstrap.addBundle(hibernateBundle)
        bootstrap.addBundle(new ViewBundle())
    }

    @Override
    void run(HelloWorldConfiguration configuration, Environment environment) throws ClassNotFoundException {
//        final def repository = new PersonRepository(hibernateBundle.sessionFactory)
        final def template = configuration.buildTemplate()
//
//        environment.healthChecks().register("template", new TemplateHealthCheck(template))
//
//        environment.jersey().register(new BasicAuthProvider<>(new ExampleAuthenticator(), "SUPER SECRET STUFF"))
        environment.jersey().register(new HelloWorldResource(template))
//        environment.jersey().register(new ViewResource())
//        environment.jersey().register(new ProtectedResource())
//        environment.jersey().register(new PeopleResource(repository))
//
//        final def factory = new DBIFactory()
//        final def jdbi = factory.build(environment, configuration.database, "jdbi")
//
//        def personJdbiRepository = jdbi.onDemand(PersonJdbiRepository.class)
//        environment.jersey().register(new PeopleJdbiResource(personJdbiRepository))
//
//        def tempJdbiRepository = jdbi.onDemand(TempJdbiRepository.class)
//        environment.jersey().register(new TempJdbiResource(tempJdbiRepository))
    }
}