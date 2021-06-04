# prueba-konecta
 
La base de datos usada es MySQL, para usar la BD local se debe modificar la configuracion en src/main/resources/application.properties:

- spring.datasource.url=jdbc:mysql://localhost/NOMBRE-BD?useSSL=false
- spring.datasource.username=USUARIO-BD
- spring.datasource.password=CONTRASEÑA-BD

cambiar los datos que se encuentran en mayuscula.

## script

El script para inicializar la BD se encuentra en src/main/resources/schema-mysql.sql