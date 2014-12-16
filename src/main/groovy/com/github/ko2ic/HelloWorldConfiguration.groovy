package com.github.ko2ic;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ko2ic.core.Template;

public class HelloWorldConfiguration extends Configuration {
    @NotEmpty
    final String template;

    @NotEmpty
    String defaultName = "Stranger";

    @Valid
    @NotNull
	@JsonProperty("database")
    DataSourceFactory database = new DataSourceFactory();

    @JsonCreator
    HelloWorldConfiguration(@JsonProperty("template") String template, @JsonProperty("defaultName") String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
    }

    Template buildTemplate() {
        return new Template(template, defaultName);
    }
}
