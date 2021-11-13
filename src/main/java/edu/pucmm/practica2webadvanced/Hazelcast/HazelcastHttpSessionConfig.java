package edu.pucmm.practica2webadvanced.Hazelcast;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.session.MapSession;
import org.springframework.session.hazelcast.Hazelcast4IndexedSessionRepository;
import org.springframework.session.hazelcast.Hazelcast4PrincipalNameExtractor;
import org.springframework.session.hazelcast.HazelcastSessionSerializer;
import org.springframework.session.hazelcast.config.annotation.SpringSessionHazelcastInstance;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;
import org.springframework.session.hazelcast.config.annotation.web.http.HazelcastHttpSessionConfiguration;

//https://guides.hazelcast.org/spring-session-hazelcast/
//https://github.com/hazelcast-guides/spring-session-hazelcast
@Configuration
@EnableHazelcastHttpSession
public class HazelcastHttpSessionConfig {

    private final String SESSIONS_MAP_NAME = "spring-session-map-name";

    @Bean
    @SpringSessionHazelcastInstance
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        config.setClusterName("spring-session-cluster");

        // Add this attribute to be able to query sessions by their PRINCIPAL_NAME_ATTRIBUTE's
        AttributeConfig attributeConfig = new AttributeConfig()
                .setName(Hazelcast4IndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
                .setExtractorClassName(Hazelcast4PrincipalNameExtractor.class.getName());

        // Configure the sessions map
        config.getMapConfig(SESSIONS_MAP_NAME)
                .addAttributeConfig(attributeConfig).addIndexConfig(
                        new IndexConfig(IndexType.HASH, Hazelcast4IndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE));

        // Use custom serializer to de/serialize sessions faster. This is optional.
        SerializerConfig serializerConfig = new SerializerConfig();
        serializerConfig.setImplementation(new HazelcastSessionSerializer()).setTypeClass(MapSession.class);
        config.getSerializationConfig().addSerializerConfig(serializerConfig);

        return Hazelcast.newHazelcastInstance(config);
    }

//    @Bean
//    public HazelcastInstance hazelcastInstance() {
//        //Configuración basica.
//        MapAttributeConfig attributeConfig = new MapAttributeConfig()
//                .setName(HazelcastIndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
//                .setExtractor(PrincipalNameExtractor.class.getName());
//
//        Config config = new Config();
//
//        config.getMapConfig(HazelcastIndexedSessionRepository.DEFAULT_SESSION_MAP_NAME)
//                .addMapAttributeConfig(attributeConfig)
//                .addMapIndexConfig(new MapIndexConfig(
//                        HazelcastIndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));
//
//        return Hazelcast.newHazelcastInstance(config);
//    }
}
