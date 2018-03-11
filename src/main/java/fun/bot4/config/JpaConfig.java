package fun.bot4.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = Application.class)
public class JpaConfig implements TransactionManagementConfigurer {

  @Value("${dataSource.driverClassName}")
  private String driver;
  @Value("${dataSource.url}")
  private String url;
  @Value("${dataSource.username}")
  private String username;
  @Value("${dataSource.password}")
  private String password;
  @Value("${hibernate.dialect}")
  private String dialect;
  @Value("${hibernate.hbm2ddl.auto}")
  private String hbm2ddlAuto;
  @Value("${hibernate.show_sql}")
  private String show_sql;
  @Value("${hibernate.jdbc.lob.non_contextual_creation}")
  private String lob_non_contextual_creation;

  @Bean
  public DataSource configureDataSource() {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName(driver);
    config.setJdbcUrl(url);
    config.setUsername(username);
    config.setPassword(password);

    return new HikariDataSource(config);
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(configureDataSource());
    entityManagerFactoryBean.setPackagesToScan("fun.bot4.model");
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

    Properties jpaProperties = new Properties();
    jpaProperties.put(Environment.DIALECT, dialect);
    jpaProperties.put(Environment.HBM2DDL_AUTO, hbm2ddlAuto);
    jpaProperties.put(Environment.SHOW_SQL, show_sql);
    jpaProperties.put(Environment.NON_CONTEXTUAL_LOB_CREATION, lob_non_contextual_creation);
    entityManagerFactoryBean.setJpaProperties(jpaProperties);

    return entityManagerFactoryBean;
  }

  @Bean
  public PlatformTransactionManager annotationDrivenTransactionManager() {
    return new JpaTransactionManager();
  }
}