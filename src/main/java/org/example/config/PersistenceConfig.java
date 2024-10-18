//package org.example.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//public class PersistenceConfig {
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("org.example.entity");
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return em;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/CCH");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("User_password");
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);
//        return transactionManager;
//    }
//}
//
//
//
//







//
//
//
//
//
//package org.example.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableJpaRepositories(basePackages = "org.example.repository")
//@ComponentScan("org.example")
//@EnableTransactionManagement
//public class PersistenceConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource ds = new HikariDataSource();
//        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/CCH");
//        ds.setUsername("postgres");
//        ds.setPassword("User_password");
//        return ds;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//
//        factoryBean.setJpaVendorAdapter(vendorAdapter);
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setPackagesToScan("org.example.entity");
//
//        // Additional properties
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
//        jpaProperties.put("hibernate.show_sql", "true");
//        jpaProperties.put("hibernate.format_sql", "true");
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//
//        factoryBean.setJpaProperties(jpaProperties);
//
//        return factoryBean;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager tx = new JpaTransactionManager();
//        tx.setEntityManagerFactory(entityManagerFactory);
//        return tx;
//    }
//}