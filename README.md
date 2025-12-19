Introducción

El presente proyecto consiste en el desarrollo de un sistema bancario básico con interfaz gráfica el cual permita a un usuario loguearse y realizar operaciones financieras como depósitos, retiros y transferencias.
El sistema fue diseñado con el objetivo de simular el funcionamiento de una banca digital, aplicando conceptos de bases de datos y persistencia de información.

Tecnologías utilizadas
•	Java como lenguaje de programación principal
•	Java Swing para la creación de la interfaz gráfica
•	MySQL como sistema gestor de base de datos
•	JDBC para la conexión entre Java y MySQL


B) Login
El sistema cuenta con una ventana de inicio de sesión Login que permite validar el acceso de los usuarios mediante credenciales almacenadas en la base de datos.
Funcionalidades del Login
•	Validación del usuario y contraseña directamente contra la base de datos MySQL.
•	Control de intentos fallidos para evitar accesos no autorizados.
•	Acceso permitido únicamente a usuarios activos registrados en el sistema.
•	En caso de credenciales incorrectas, el sistema muestra un mensaje de error informativo.
Una vez que el usuario ingresa correctamente sus datos, se habilita el acceso a la ventana principal del banco.
capturas	Explicación 
 	Si se ingresa mal el usuario y la contraseña nos dará un error y después de 3 intentos se saldrá del programa
 	Al ingresar correctamente el usuario y la contraseña se nos dará acceso al programa

<img width="792" height="464" alt="Captura de pantalla 2025-12-14 194004" src="https://github.com/user-attachments/assets/6ab22b38-0f31-4c5a-b2f0-14ab51b8ee68" />

C) Ventana Banco
Luego de un inicio de sesión exitoso, el sistema muestra la ventana principal de operaciones bancarias, donde el usuario puede visualizar y gestionar su cuenta.
Funcionalidades principales
•	Visualización del nombre del usuario autenticado.
•	Carga automática y visualización del saldo actual desde la base de datos.
•	Realización de las siguientes operaciones:
o	Depósito: incremento del saldo ingresando un monto válido.
o	Retiro: disminución del saldo verificando disponibilidad.
o	Transferencia: envío de un monto a otro usuario registrado.

<img width="786" height="642" alt="Captura de pantalla 2025-12-14 193948" src="https://github.com/user-attachments/assets/c47d1ca2-cdfa-4003-83fd-4f007d337eca" />
<img width="783" height="378" alt="Captura de pantalla 2025-12-14 193955" src="https://github.com/user-attachments/assets/53dad3e4-aa92-4e1d-ae4d-43aaaf8ee919" />


Validaciones
•	Verificación de que los valores ingresados sean numéricos.
•	Restricción de montos negativos o nulos.
•	Control de saldo suficiente antes de realizar retiros o transferencias.
Historial
•	El sistema muestra un historial de operaciones realizadas durante la sesión del usuario, permitiendo un seguimiento de sus movimientos.
 <img width="900" height="329" alt="image" src="https://github.com/user-attachments/assets/a8af4fed-2b36-4095-9675-f1911c9c3571" />



D) Persistencia de Datos
El sistema implementa persistencia de información, garantizando que los datos se conserven aun cuando la aplicación se cierre.



<img width="782" height="634" alt="Captura de pantalla 2025-12-14 193929" src="https://github.com/user-attachments/assets/a83f4bae-6c50-4fb8-9f14-98b1a4493b3d" />



Características
•	El saldo del usuario se actualiza automáticamente en la base de datos después de cada operación.
•	Al volver a iniciar sesión, el sistema recupera el último saldo registrado, asegurando la continuidad de la información.
•	La persistencia se maneja mediante sentencias SQL ejecutadas desde Java usando JDBC.
Esta funcionalidad asegura que el sistema mantenga la integridad y confiabilidad de los datos financieros.


Módulo de Administración de Usuarios (CRUD)<br>
El sistema incorpora un módulo de administración, destinado a la gestión de usuarios del banco. Este módulo permite realizar operaciones CRUD (Create, Read, Update, Delete lógico) sobre los usuarios registrados en la base de datos, garantizando un control adecuado de las cuentas del sistema.<br>
Funcionalidades del módulo administrador<br>
•	Crear usuarios con nombre de usuario, contraseña y saldo inicial.<br>
•	Listar usuarios registrados en la base de datos en una tabla gráfica.<br>
•	Actualizar datos del usuario, específicamente el saldo.<br>
•	Eliminar usuarios de forma lógica, desactivando su estado sin borrar el registro físicamente.<br>
•	Selección de usuarios desde una tabla, facilitando la edición de información.<br>
Este módulo fue desarrollado utilizando Java Swing para la interfaz gráfica y JDBC para la comunicación con la base de datos MySQL.<br>

Explicación del Código del Módulo Administrador<br>
1. Estructura general de la clasev
La clase Adminusuarios extiende de JFrame, lo que permite crear una ventana independiente para la administración del sistema.<br>
public class Adminusuarios extends JFrame<br>
Esta clase contiene:<br>
•	Componentes gráficos (JPanel, JTable, JTextField, JButton).<br>
•	Métodos para cada operación CRUD.<br>
•	Métodos auxiliares para validación y carga de datos.<br>

2. Constructor de la clase<br>

<img width="581" height="414" alt="image" src="https://github.com/user-attachments/assets/879e9673-9020-480d-a328-1846d4cb454a" />
 
Funciones principales del constructor:<br>
•	Configura el título, tamaño y posición de la ventana.<br>
•	Asigna el panel principal diseñado en Swing.<br>
•	Conecta los botones con sus acciones.<br>
•	Habilita la selección de filas en la tabla.<br>
•	Hace visible la ventana.<br>

3. Asociación de eventos a botones<br>
   
<img width="732" height="260" alt="image" src="https://github.com/user-attachments/assets/00f9ec4f-13fe-4585-86de-bb035d120782" />

Este método conecta cada botón con su respectiva funcionalidad, permitiendo que el sistema responda a las acciones del usuario de forma interactiva.<br>

4. Crear usuario (CREATE)<br>
String sql = "INSERT INTO usuarios(username, password, saldo, activo) VALUES (?, ?, ?, true)"; <br>
•	Se obtienen los datos desde los campos de texto.<br>
•	Se valida que el saldo sea numérico.<br>
•	Se ejecuta una sentencia SQL INSERT usando PreparedStatement.<br>
•	Se actualiza automáticamente la tabla después de la inserción.<br>
Esto evita inyecciones SQL y asegura una correcta persistencia de datos.<br>

5. Listar usuarios (READ)<br>
String sql = "SELECT id, username, saldo, activo FROM usuarios";<br>
•	Se crea un DefaultTableModel para la tabla.<br>
•	Se recorren los resultados obtenidos de la base de datos.<br>
•	Se muestran los usuarios con su estado (Activo/Inactivo).<br>
Esta funcionalidad permite visualizar de forma clara todos los registros almacenados.<br>

6. Actualizar usuario (UPDATE)<br>
String sql = "UPDATE usuarios SET saldo = ? WHERE id = ?";<br>
•	Se obtiene el usuario seleccionado en la tabla.<br>
•	Se valida el nuevo saldo ingresado.<br>
•	Se actualiza únicamente el saldo del usuario.<br>
•	Se refresca la tabla para reflejar los cambios.<br>

7. Eliminación lógica de usuarios (DELETE lógico)<br>
String sql = "UPDATE usuarios SET activo = false WHERE id = ?";<br>
En lugar de eliminar físicamente el registro:<br>
•	Se cambia el estado del usuario a inactivo.<br>
•	Esto permite mantener la integridad histórica de los datos.<br>
•	Los usuarios inactivos no pueden iniciar sesión.<br>

8. Selección de datos desde la tabla<br>
tablaUsuarios.getSelectionModel().addListSelectionListener(...)<br>
Cuando el administrador selecciona un usuario:<br>
•	El nombre de usuario y el saldo se cargan automáticamente en los campos de texto.<br>
•	Facilita la edición y actualización de información.<br>
________________________________________
F) Estructura del Proyecto<br>
La estructura del proyecto sigue una organización modular, facilitando el mantenimiento y la escalabilidad del sistema.<br>
/src<br>
 ├── Conexion.java<br>
 ├── Login.java<br>
 ├── Banco.java<br>
 ├── Adminusuarios.java<br>
 └── Main.java<br>
Descripción de archivos<br>
•	Conexion.java: Maneja la conexión con la base de datos MySQL mediante JDBC.<br>
•	Login.java: Controla el acceso de usuarios al sistema.<br>
•	Banco.java: Contiene la lógica de operaciones bancarias.<br>
•	Adminusuarios.java: Gestión administrativa de usuarios (CRUD).<br>
•	Main.java: Punto de inicio de la aplicación.<br>

G) Proceso Completo de Desarrollo<br>
1. Análisis<br>
•	Identificación de requerimientos funcionales.<br>
•	Definición de roles (usuario y administrador).<br>
•	Diseño de operaciones bancarias básicas.<br>
2. Diseño<br>
•	Diseño de la base de datos en MySQL.<br>
•	Creación de interfaces gráficas con Java Swing.<br>
•	Definición de flujos de navegación entre ventanas.<br>
3. Implementación<br>
•	Programación en Java orientado a objetos.<br>
•	Uso de JDBC para ejecutar sentencias SQL.<br>
•	Implementación del CRUD y validaciones de datos.<br>
4. Pruebas<br>
•	Pruebas de login con credenciales válidas e inválidas.<br>
•	Pruebas de operaciones bancarias.<br>
•	Verificación de persistencia de datos.<br>
•	Validación del módulo administrador.<br>
5. Despliegue<br>
•	Compilación del proyecto en formato JAR.<br>
•	Ejecución del sistema en entorno local.<br>
•	Conexión a base de datos MySQL funcional.<br>

Conclusión<br>
El sistema bancario desarrollado cumple con los objetivos planteados, permitiendo la gestión segura de usuarios y operaciones financieras mediante una interfaz gráfica intuitiva. La integración de Java, Java Swing y MySQL garantiza una correcta persistencia de datos, seguridad en el acceso y una experiencia de usuario eficiente. El uso de operaciones CRUD y eliminación lógica refuerza la integridad de la información, demostrando una aplicación práctica de los conceptos aprendidos durante el desarrollo del proyecto.


