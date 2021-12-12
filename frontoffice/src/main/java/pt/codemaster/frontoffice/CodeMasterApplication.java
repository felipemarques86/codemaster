package pt.codemaster.frontoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("pt.codemaster.adt")
@ComponentScan(basePackages = {"pt.codemaster.services", "pt.codemaster.rest", "pt.codemaster.config"})
@EnableJpaRepositories("pt.codemaster.repositories")
public class CodeMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeMasterApplication.class, args);
    }

}
