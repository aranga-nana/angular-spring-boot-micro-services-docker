package com.aranga.config;

import com.aranga.hbm.AppConfig;
import com.aranga.hbm.DataSourceDriver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;


/**
 * Created by Aranga on 03/02/2016
 */
@Configuration
@EnableTransactionManagement
public class JPAConfig
{
    private static final Logger LOG = Logger.getLogger(JPAConfig.class);

    @Autowired(required = false)
    private DataSourceDriver driver;

    @Autowired(required = false)
    private AppConfig dbconfig;

    public JPAConfig()
    {
        LOG.info("JPA Configuration loaded!");
    }

    @Bean(name = "coreEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        if (driver == null){
            throw new IllegalStateException("cannot find db.type property with valid value ( mysql,ms-sql)");
        }
        driver.create();
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(driver.create());
        em.setPackagesToScan(new String[]{"com"});
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(false);
        vendorAdapter.setGenerateDdl(false);

        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Autowired
    @Bean(name = "coreTxnManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.dialect", driver.getDialect());
        properties.setProperty("hibernate.show_sql",dbconfig.isShowSql());
        properties.setProperty("hibernate.format_sql","true");

        return properties;
    }


}
