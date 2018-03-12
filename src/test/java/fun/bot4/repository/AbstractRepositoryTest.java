package fun.bot4.repository;

import fun.bot4.Application;
import javax.persistence.EntityManager;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
//@DataJpaTest
//@ContextConfiguration({"classpath:spring/app-config.xml","classpath:spring/db-config.xml"})
@Sql(scripts = "classpath:db/db_populate.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract public class AbstractRepositoryTest {

  @Autowired
  protected EntityManager entityManager;


}
