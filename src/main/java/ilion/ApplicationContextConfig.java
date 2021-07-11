package ilion;

import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("ilion")
@EnableTransactionManagement
public class ApplicationContextConfig {

  private ResourceBundle _resourceBundle;

  private ResourceBundle getJDBCProps() {
    if (_resourceBundle == null) {
      _resourceBundle = ResourceBundle.getBundle("jdbc");
    }

    return _resourceBundle;
  }

  @Bean(name = "dataSource")
  public DataSource getDataSource() {

    ResourceBundle resourceBundle = getJDBCProps();

    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(resourceBundle.getString("jdbc.driverClassName"));
    dataSource.setUrl(resourceBundle.getString("jdbc.url"));
    dataSource.setUsername(resourceBundle.getString("jdbc.user"));
    dataSource.setPassword(resourceBundle.getString("jdbc.pass"));

    return dataSource;
  }

  @Autowired
  @Bean(name = "sessionFactory")
  public SessionFactory getSessionFactory(DataSource dataSource) {

    ResourceBundle resourceBundle = getJDBCProps();

    LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource);

    sessionFactory.setProperty("hibernate.dialect", resourceBundle.getString("hibernate.dialect"));
    sessionFactory.setProperty("hibernate.show_sql", resourceBundle.getString("hibernate.show_sql"));
    sessionFactory.setProperty("hibernate.hbm2ddl.auto", resourceBundle.getString("hibernate.hbm2ddl.auto"));

    sessionFactory.addAnnotatedClass(ilion.admin.negocio.Prop.class);

    sessionFactory.addAnnotatedClass(ilion.admin.negocio.Cidade.class);

    sessionFactory.addAnnotatedClass(ilion.admin.negocio.Perfil.class);
    sessionFactory.addAnnotatedClass(ilion.admin.negocio.Usuario.class);

    sessionFactory.addAnnotatedClass(ilion.arquivo.negocio.Arquivo.class);

    sessionFactory.addAnnotatedClass(ilion.contato.negocio.Contato.class);
    sessionFactory.addAnnotatedClass(ilion.contato.negocio.ContatoGrupo.class);
    sessionFactory.addAnnotatedClass(ilion.contato.negocio.ContatoXGrupo.class);
    sessionFactory.addAnnotatedClass(ilion.contato.negocio.ContatoComentario.class);

    sessionFactory.addAnnotatedClass(ilion.gc.categoria.negocio.CategoriaArtigo.class);
    sessionFactory.addAnnotatedClass(ilion.gc.negocio.SubCategoriaArtigo.class);
    sessionFactory.addAnnotatedClass(ilion.gc.negocio.Artigo.class);
    sessionFactory.addAnnotatedClass(ilion.gc.negocio.ArtigoConteudo.class);
    sessionFactory.addAnnotatedClass(ilion.gc.negocio.Enquete.class);
    sessionFactory.addAnnotatedClass(ilion.gc.negocio.Comentario.class);
    sessionFactory.addAnnotatedClass(ilion.gc.topico.negocio.Topico.class);

    sessionFactory.addAnnotatedClass(ilion.email.negocio.EmailSender.class);
    sessionFactory.addAnnotatedClass(ilion.email.negocio.Email.class);
    sessionFactory.addAnnotatedClass(ilion.vitazure.model.Pessoa.class);
    
    return sessionFactory.buildSessionFactory();
  }
}
