package command.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"command.repository"})
@EntityScan(basePackages = {"command.model"})
@ComponentScan(basePackages = {"command.repository", "command.model", "command.service", "command.rest.controller"})
public class Springbootrunner {

    public static void main(String[] args){
        SpringApplication.run(Springbootrunner.class);
    }
}
