# MiniELTL

> [!NOTE]
> En este repositorio hago un ejercicio de ETL: extraigo información de un CSV y la inserto en una base de datos, intentando acercarme lo máximo posible a un caso real (datos sucios o con caracteres extraños, restricciones de la BBDD, log errores). 

## Objetivo

Leer de archivos con datos que en una situación empresarial nos enviaría un equipo diferente y tendríamos transformarlo y cargarlo en una base de datos, sin que el proceso se detenga por culpa de filas con datos incompletos o mal formados y dejando constancia de qué filas fallaron y por qué.


## Errores o puntos que voy encontrando que son interesantes de comentar

### 1. En el csv hay campos vacíos

<sub>No todas las filas traen el dato completo: hay teléfonos, emails, fechas de suscripción y nombres que faltan. Esto es habitual en un CSV real,así que el proceso tiene que decidir, campo a campo, qué hacer cuando no hay valor si la columna de la base de datos lo permite, se inserta como ausencia real de dato; si la columna es obligatoria (como nombre), la fila se descarta y se deja constancia en el log en lugar de forzar un valor inventado.</sub>

### 2. El csv (customers-1000.csv) viene con el campo index y no hace falta que en el base de datos esté el AUTO_INCREMENT
   
<sub>El CSV ya trae su propia columna Index, con un valor único y secuencial para cada fila. En vez de ignorarlo y dejar que la base de datos generase su propio identificador con AUTO_INCREMENT, se decidió reutilizar directamente ese Index como clave primaria (id)</sub>


### 3. Los campos de la base de datos se llaman diferente que los de la cabecera del CSV

La cabecera del CSV usa nombres en inglés y con mayúsculas/espacios (Customer Id, First Name, Subscription Date...), mientras que la tabla usa nombres en español y en snake_case (customer_id, nombre, fecha_suscripcion...). No hay ninguna correspondencia automática entre ambos: el mapeo está hecho a mano, por posición, dentro del código.

Esto es una decisión deliberada del ejercicio: en ningún momento se ha tocado la base de datos para adaptarla al CSV (ni renombrando columnas, ni cambiando tipos), y toda la traducción entre un mundo y el otro ocurre exclusivamente en el código. La idea es simular un escenario habitual en un entorno de trabajo real: muchas veces no se tiene permiso para modificar la estructura de una base de datos ya existente (es de otro equipo, está en producción, tiene otras aplicaciones dependiendo de ella...), así que el código es lo que se tiene que adaptar.


   
6. Ademas el campo del nombre es obligatorio y hay filas que no lo tienen
7. el problema principal de este ejercicio ha sido que había nombres que tenian tambien ',' dentro y entonces al separador ser la ',' había problemas, y si faltaba algún dato el split devolvia menos campos y faltaba uno por rellenar
8. y el ultimo problema que tuve es que con el split usaba una expresión regular para que rellenase ese campo vacío con "" y luego eso es lo que se insertaba literalmente en la bd, al final tuve qur forzar que si encontraba "" que lo pusiera a null directamente

aquí si nos damos cuenta el 1 no aparece porque en el csv le quite el nombre, que en esta bd es obligatorio
<img width="1789" height="181" alt="imagen" src="https://github.com/user-attachments/assets/f7c92ccf-1d4b-45ef-b824-bb96fdfb7962" />

y sale aqui en el log 
<img width="1791" height="174" alt="imagen" src="https://github.com/user-attachments/assets/95edb40a-9331-475c-9f5a-70034f44e420" />

