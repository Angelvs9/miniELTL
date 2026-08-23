## miniELTL

> [!NOTE]
> En este repositorio hago un ejercicio de ETL: extraigo información de un CSV y la inserto en una base de datos, intentando acercarme lo máximo posible a un caso real (datos sucios o con caracteres extraños, restricciones de la BD, log errores). 

#Objetivo

Leer de archivos con datos que en una situacion empresarial nos enviaria un equipo diferente y tendriamos transformarlo y cargarlo en una base de datos, sin que el proceso se detenga por culpa de filas con datos incompletos o mal formados y dejando constancia de qué filas fallaron y por qué.


#Errores o puntos que voy encontrando que son interesantes de comentar

1. En el csv hay campos vacios
2. 
No todas las filas traen el dato completo: hay teléfonos, emails, fechas de suscripción y nombres que faltan. Esto es habitual en un CSV real,así que el proceso tiene que decidir, campo a campo, qué hacer cuando no hay valor si la columna de la base de datos lo permite, se inserta como ausencia real de dato; si la columna es obligatoria (como nombre), la fila se descarta y se deja constancia en el log en lugar de forzar un valor inventado.

3. El csv (de donde saco la informacion) viene con el campo index y no hace falta que en el bd esté el autoincrement



4. Los campos de la BBDD se llaman diferente de los de la cabecera del csv y para este ejercicio voy a intentar hacerlo lo más real posible intentado no tocar nada en la bd y haciendolo todo desde el codigo porque en un entorno real puedo no tener acceso a la bd
5. Ademas el campo del nombre es obligatorio y hay filas que no lo tienen
6. el problema principal de este ejercicio ha sido que había nombres que tenian tambien ',' dentro y entonces al separador ser la ',' habia problemas, y si faltaba algún dato el split devolvia menos campos y faltaba uno por rellenar
7. y el ultimo problema que tuve es que con el split usaba una expresión regular para que rellenase ese campo vacío con "" y luego eso es lo que se insertaba literalmente en la bd, al final tuve qur forzar que si encontraba "" que lo pusiera a null directamente

aquí si nos damos cuenta el 1 no aparece porque en el csv le quite el nombre, que en esta bd es obligatorio
<img width="1789" height="181" alt="imagen" src="https://github.com/user-attachments/assets/f7c92ccf-1d4b-45ef-b824-bb96fdfb7962" />

y sale aqui en el log 
<img width="1791" height="174" alt="imagen" src="https://github.com/user-attachments/assets/95edb40a-9331-475c-9f5a-70034f44e420" />

