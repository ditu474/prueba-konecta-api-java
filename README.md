# prueba-konecta
 
La base de datos usada es MySQL, para usar la BD local se debe modificar la configuracion en src/main/resources/application.properties:

- spring.datasource.url=jdbc:mysql://localhost/NOMBRE-BD?useSSL=false
- spring.datasource.username=USUARIO-BD
- spring.datasource.password=CONTRASEÑA-BD

cambiar los datos que se encuentran en mayuscula!!!.

## script

El script para inicializar la BD se encuentra en src/main/resources/schema-mysql.sql

## FrontEnd

El repositorio del frontend (Creado con React) se puede encontrar en: https://github.com/ditu474/prueba-konecta-frontend

## ANTES DE EJECUTAR EL PROYECTO

1. Iniciar localmente una BD MySQL
2. Modificar el application.properties como se especifica en la parte superior de este README
3. Instalar las dependencias Maven
4. Ejecutar el proyecto (Si no cambia la propiedad del puerto en application.properties, se ejecutará el servidor en el puerto 4000)
