package org.openchain.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/**
 * www.spoilercup.com
 * User: Roman Mandeleil
 * Created on: 07/11/13 12:04
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="org.openchain")
@ImportResource("classpath:applicationContext.xml")
public class WebConfig extends WebMvcConfigurerAdapter {

    Logger log  = LoggerFactory.getLogger(getClass());


    @Autowired
    @Qualifier("systemProperties")
    public Properties systemProperties;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/css/**").
                addResourceLocations("/resources/css/").setCachePeriod(0);

        registry.addResourceHandler("/resources/scripts/page/**").
                addResourceLocations("/resources/scripts/page/").setCachePeriod(0);
        registry.addResourceHandler("/resources/scripts/components/**").
                addResourceLocations("/resources/scripts/components/").setCachePeriod(3600);
        registry.addResourceHandler("/resources/scripts/**").
                addResourceLocations("/resources/scripts/").setCachePeriod(3600);

        registry.addResourceHandler("/resources/img/**").
                addResourceLocations("/resources/img/").setCachePeriod(3600);

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("index");

    }


    @Bean(name="sessionFactory")
    public LocalSessionFactoryBean initDB(){

        String dbType = (String) systemProperties.get("db.type");
        String driver = "org.h2.Driver";
        String connectionURL = "jdbc:h2:./db/block_explorer_db;create=true;LOCK_TIMEOUT=60000";
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();


        log.info("*** DATABASE type ===> " + dbType);

        if (dbType != null && dbType.equals("embedded")){


            try {
                Class.forName(driver);
                log.info(driver + " loaded. ");
            } catch (java.lang.ClassNotFoundException e) {
                log.error("check CLASSPATH for H2 jar ", e);
            }


            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(connectionURL);

            sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            sessionFactory.setPackagesToScan("org.openchain.db");
            sessionFactory.getHibernateProperties().setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            sessionFactory.getHibernateProperties().setProperty("hibernate.show_sql","true");
            sessionFactory.getHibernateProperties().setProperty("hibernate.hbm2ddl.auto","update");

        }

        return sessionFactory;
    }



    @Bean
    public InternalResourceViewResolver viewResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
