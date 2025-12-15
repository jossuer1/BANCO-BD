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
