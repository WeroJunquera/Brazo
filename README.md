El propósito de esta práctica es desarrollar paso a paso un proyecto para controlar y programar un Brazo Robot, 
simulando las funciones básicas de un robot industrial. Programar: Registrar las posiciones de los brazos en tres 
dimensiones (cada registro es un "paso", un programa consiste en una serie de pasos). Ejecutar: Realice en secuencia 
las posiciones registradas en el "Programa". El robot ejecutará el programa hasta que se use el comando "ABORTAR". 
El proyecto se usa para controlar robots con 4 DOF ("Grados de libertad"). El robot se debe controlar en modo "REMOTO"
(a través de una programa en java por medio del puerto serial). La información para el usuario se podrá proporcionar a través
de LEDS de colores, una pantalla LCD de 2 líneas y/ó sonido (un zumbador). Debe de contener un botón de paro de emergencia
(Físico). Los brazos robóticos se pueden clasificar de acuerdo con el número de "articulaciones" o "Grados de libertad" (DOF)
que tienen. -La "Base", o "Cintura", por lo general puede girar el brazo 180o o 360o, dependiendo del tipo de Servo utilizado 
(aquí en este proyecto, se debe utilizar un motor a pasos para girar 360o) -El "Hombro" es el responsable de "levantar o bajar"
el brazo verticalmente 
-El "codo" hará que el brazo "avance o retroceda". -La "Garra" o "Pinza" funciona abriendo o cerrándose para "agarrar cosas".
