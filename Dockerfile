# Archivo que representa la información para la creación de
# una imagen para Docker, indicando todos los parametros necesarios.

# Probando el concepto de Multi-stage.
# Instalando Gradle para compilar al aplicación y luego lo necesario a una imagen completa.
FROM gradle:7.2.0-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

# El comando FROM indica la imagen base. Usar imagenes ligeras
FROM openjdk:11.0.12-jre-slim-buster

# Quien mantiene la versión.
LABEL maintainer="Gabriel Marte <20170388@ce.pucmm.edu.do>"

# Indicando variable de ambiente para pasar el nombre
# de la base de datos.
ENV NOMBRE_APP = 'clone-mocky'
ENV DB_NAME='clone-mocky'
ENV spring.datasource.username='root'
ENV spring.datasource.password='12345678'
ENV DB_HOST=base-datos-app-web
ENV DB_PORT=3306
ENV DB_USER=root
ENV DB_PASSWORD=12345678
# ENV spring.datasource.url='jdbc:mysql://192.168.77.10:3306/dbname'


# Añadiendo el punto de montaje en el host
# Por defecto Tomcat crea los archivo temporales en esa ruta,
# lo habilitamos para ver los log, no es necesario para nuestro ejemplo.
VOLUME /tmp

# Puertos que estarán disponibles de nuestra aplicación.
EXPOSE 8080

# Copiando el archivo jar generado luego de la ejecución del comando
# gradle task bootjar, se crean el jar y se copia a la imagen.
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

#Comando que se ejecuta una vez es iniciada la aplicación.
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]

# Para subir al repositorio realizo el push
# debo logearme primero
# docker login -u usuario
# se etiqueta la imagen y luego hacemos el push
# sudo docker build -t nombre-imagen .
# sudo docker tag nombre_imagen_local id_usuario/nombre_a_subir
# sudo docker push id_usuario/nombre_a_subir
# Ejemplo:
# sudo docker build -t javalin-demo .
# sudo docker tag javalin-demo vacax/javalin-demo
# sudo docker push vacax/javalin-demo
