package ru.c_energies.web.jte;

import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
public class JteConfiguration {
    private final Logger LOG = LogManager.getLogger(JteConfiguration.class);
    @Bean
    public ViewResolver jteViewResolve(TemplateEngine templateEngine) {
        return new JteViewResolver(templateEngine);
    }
    @Bean
    public TemplateEngine templateEngine() {
        String profile = System.getenv("SPRING_ENV");
        if ("prod".equals(profile)) {
            LOG.trace("profile = prod");
            // Templates will be compiled by the maven build task
            return TemplateEngine.createPrecompiled(ContentType.Html);
        } else {
            LOG.trace("profile != prod");
            // Here, a JTE file watcher will recompile the JTE templates upon file save (the web browser will auto-refresh)
            // If using IntelliJ, use Ctrl-F9 to trigger an auto-refresh when editing non-JTE files.
            CodeResolver codeResolver = new DirectoryCodeResolver(Path.of("web" ,"src", "main", "resources", "jte"));
            //TemplateEngine templateEngine = TemplateEngine.create(codeResolver, Paths.get("jte-classes"), ContentType.Html, getClass().getClassLoader());
            TemplateEngine templateEngine = TemplateEngine.create(codeResolver, Paths.get(Paths.get("").toAbsolutePath().toString()+"/web/target/jte-classes"), ContentType.Html, getClass().getClassLoader());
            templateEngine.setBinaryStaticContent(true);
            return templateEngine;
        }
    }
}
