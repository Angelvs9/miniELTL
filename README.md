# MiniELTL

> [!NOTE]
> En este repositorio hago un ejercicio de ETL: extraigo información de un CSV y la inserto en una base de datos, intentando acercarme lo máximo posible a un caso real (datos sucios o con caracteres extraños, restricciones de la BBDD, log errores). 

## Objetivo

Leer de archivos con datos que en una situación empresarial nos enviaría un equipo diferente y tendríamos transformarlo y cargarlo en una base de datos, sin que el proceso se detenga por culpa de filas con datos incompletos o mal formados y dejando constancia de qué filas fallaron y por qué.
<br>
### Errores o puntos que voy encontrando que son interesantes de comentar
<hr>

### 1. En el csv hay campos vacíos

<sub>No todas las filas traen el dato completo: hay teléfonos, emails, fechas de suscripción y nombres que faltan. Esto es habitual en un CSV real,así que el proceso tiene que decidir, campo a campo, qué hacer cuando no hay valor si la columna de la base de datos lo permite, se inserta como ausencia real de dato; si la columna es obligatoria (como nombre), la fila se descarta y se deja constancia en el log en lugar de forzar un valor inventado.</sub>


### 2. El csv (`customers-1000.csv`) viene con el campo index y no hace falta que en el base de datos esté el AUTO_INCREMENT
   
<sub>El CSV ya trae su propia columna `Index`, con un valor único y secuencial para cada fila. En vez de ignorarlo y dejar que la base de datos generase su propio identificador con `AUTO_INCREMENT`, se decidió reutilizar directamente ese Index como clave primaria (id) </sub>


### 3. Los campos de la base de datos se llaman diferente que los de la cabecera del CSV

<sub>La cabecera del CSV usa nombres en inglés y con mayúsculas/espacios (Customer Id, First Name, Subscription Date...), mientras que la tabla usa nombres en español y en snake_case (customer_id, nombre, fecha_suscripcion...). No hay ninguna correspondencia automática entre ambos: el mapeo está hecho a mano, por posición, dentro del código.</sub>

<sub>Esto es una decisión deliberada del ejercicio: en ningún momento se ha tocado la base de datos para adaptarla al CSV (ni renombrando columnas, ni cambiando tipos), y toda la traducción entre un mundo y el otro ocurre exclusivamente en el código. La idea es simular un escenario habitual en un entorno de trabajo real: muchas veces no se tiene permiso para modificar la estructura de una base de datos ya existente (es de otro equipo, está en producción, tiene otras aplicaciones dependiendo de ella...), así que el código es lo que se tiene que adaptar.</sub>


### 4. El campo del nombre del (`customers-1000.csv`) es obligatorio y hay filas que no lo tienen además de las filas que tenian comas dentro del mismo nombre
<sub>Estos han sido el principal problema de esta parte del ejercicio, algunas filas por ejemplo "Terry, Proctor and Lawrence". Un split(",") corriente no entiende de comillas: separa por cualquier coma que encuentre, así que ese único campo se parte en dos trozos ("Terry y Proctor and Lawrence").</sub>

<sub>El efecto no se queda ahí — como el array resultante tiene ahora una posición de más, todas las columnas siguientes de esa fila quedan desplazadas una posición. En la práctica, esto provocaba que el programa intentara convertir un email en una fecha (porque, tras el desplazamiento, en la posición donde se esperaba la fecha de suscripción aparecía el contenido de la columna email), lanzando una excepción de formato inválido en un campo que en realidad nunca tuvo ningún problema — el error real estaba varias columnas antes, en el campo con la coma sin escapar.</sub>

> [!IMPORTANT]
> La solución fue sustituir el split(",") simple por uno que respeta las comillas, usando una expresión regular que separa por comas excepto cuando están dentro de un número impar de comillas:
```java
linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)
```

<sub>El segundo parámetro, -1, es igual de importante y resuelve un problema distinto: por defecto, String.split(...) en Java elimina los elementos vacíos que queden al final del array resultante.Si la última columna de una fila (web, en este CSV) viene vacía, un split sin ese -1 devolvería un array más corto de lo esperado, y cualquier acceso a esa posición lanzaría `ArrayIndexOutOfBoundsException`. El -1 obliga a conservar siempre el número real de columnas, estén vacías o no.</sub>


### 5. Los campos vacíos no se insertaban como NULL, sino como el texto literal ""
> [!WARNING]
> Un campo vacío tras el split no es lo mismo que `null` en Java, y `NOT NULL` no bloquea cadenas vacías.

<sub>Una vez resuelto el problema del split, apareció uno distinto y más sutil: cuando un campo del CSV está vacío, el resultado del split para esa posición es un String de longitud cero ("") es decir, un valor real, aunque no tenga caracteres, no la ausencia de valor.</sub>

<sub>Para detectarlo y corregirlo de forma explícita antes de llegar al INSERT, se añadió un recorrido sobre el array de datos ya separado, que normaliza cualquier variante de "campo sin contenido real" a un null de Java de verdad (esto es lo que se me ocurrió, seguro que había alguna otra forma) </sub>

```java
for (int i = 0; i < datos.length; i++) {
    if (datos[i] == null || datos[i].trim().isEmpty() || datos[i].trim().equals("\"\"")) {
        datos[i] = null;
    }
}
```
Aquí hay 3 condiciones:

<sub>datos[i] == null — el propio elemento del array nunca llegó a asignarse (caso más teórico, de seguridad). </sub>

<sub>datos[i].trim().isEmpty() — el campo vino como cadena vacía tras quitar espacios en blanco ("", o "   " con solo espacios), que es el caso típico de una columna sin dato.</sub>

<sub>datos[i].trim().equals("\"\"") — comprueba si el campo es literalmente el texto "" (dos comillas dobles), no cuatro caracteres sueltos: \" es solo la forma en que Java escribe una comilla dentro de un String. Este caso aparece porque algunos campos vacíos del CSV no llegan como hueco en blanco, sino con el texto "" puesto explícitamente (típico de CSVs exportados desde hojas de cálculo). Sin esta comprobación, isEmpty() no lo detecta como vacío, y ese "" se inserta tal cual en la base de datos como si fuera un dato real.</sub>


Aquí si nos damos cuenta el 1 no aparece porque en el csv no tiene nombre, que en esta bd es obligatorio
<img width="1789" height="181" alt="imagen" src="https://github.com/user-attachments/assets/f7c92ccf-1d4b-45ef-b824-bb96fdfb7962" />

y aparece aquí en el log 
<img width="1791" height="174" alt="imagen" src="https://github.com/user-attachments/assets/95edb40a-9331-475c-9f5a-70034f44e420" />

