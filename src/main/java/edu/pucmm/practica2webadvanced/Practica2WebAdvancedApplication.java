package edu.pucmm.practica2webadvanced;


import com.hazelcast.config.Config;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import edu.pucmm.practica2webadvanced.Repositories.UserRepository;
import edu.pucmm.practica2webadvanced.Services.DBData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.session.hazelcast.HazelcastIndexedSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

@SpringBootApplication
@EnableHazelcastHttpSession
public class Practica2WebAdvancedApplication implements CommandLineRunner {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
//        SpringApplication.run(Practica2WebAdvancedApplication.class, args);
        ApplicationContext applicationContext = SpringApplication.run(Practica2WebAdvancedApplication.class, args);


//        String[] lista = applicationContext.getBeanDefinitionNames();
//        System.out.println("====== Beans Registrados =====");
//        for(String bean : lista){
//            System.out.println(""+bean);
//        }
//        System.out.println("====== FIN Beans Registrados =====");

        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
        if(userRepository.findAll().isEmpty()) {
            DBData dbData = (DBData) applicationContext.getBean("DBData");
            dbData.initDB();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        //leyendo la información de las variables.
        String db_nombre = environment.getProperty("NOMBRE_APP");
        String direccionDb = environment.getProperty("DB_HOST");
        System.out.println("Nombre de la Aplicación = "+db_nombre);
        System.out.println("Dirección de la Aplicación = "+direccionDb);
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        //Configuración basica.
        MapAttributeConfig attributeConfig = new MapAttributeConfig()
                .setName(HazelcastIndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
                .setExtractor(PrincipalNameExtractor.class.getName());

        Config config = new Config();

        config.getMapConfig(HazelcastIndexedSessionRepository.DEFAULT_SESSION_MAP_NAME)
                .addMapAttributeConfig(attributeConfig)
                .addMapIndexConfig(new MapIndexConfig(
                        HazelcastIndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));

        return Hazelcast.newHazelcastInstance(config);
    }

}
