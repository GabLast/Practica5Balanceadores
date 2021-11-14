package edu.pucmm.practica2webadvanced;


import edu.pucmm.practica2webadvanced.Repositories.UserRepository;
import edu.pucmm.practica2webadvanced.Services.DBData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Practica2WebAdvancedApplication implements CommandLineRunner {

    @Autowired
    private Environment environment;

    @Value("${server.port}")
    private static int port;

    public static void main(String[] args) {
//        SpringApplication.run(Practica2WebAdvancedApplication.class, args);

        if(port != 8080){
            try
            {
                Thread.sleep(10 * 1000);
                System.out.println("Delay to slow down the start up...");
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        ApplicationContext applicationContext = SpringApplication.run(Practica2WebAdvancedApplication.class, args);


//        String[] lista = applicationContext.getBeanDefinitionNames();
//        System.out.println("====== Beans Registrados =====");
//        for(String bean : lista){
//            System.out.println(""+bean);
//        }
//        System.out.println("====== FIN Beans Registrados =====");

        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
        if(userRepository.findAll().isEmpty() && port == 8080) {
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

}
