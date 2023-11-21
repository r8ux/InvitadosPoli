
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

# Utiliza una imagen base con Java preinstalado
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo
WORKDIR /app
# Copia la carpeta desde tu sistema local al directorio de trabajo en el contenedor
COPY /lib /app/lib

# Copia tu aplicación Java (JAR) al contenedor
COPY InvitadosPolitenico.jar /app/InvitadosPolitenico.jar

# Comando de entrada para ejecutar la aplicación
CMD ["java", "-jar", "InvitadosPolitenico.jar"]