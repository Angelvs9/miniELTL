# miniELTL

> [!NOTE]
> En este repositorio voy a hacer un ejercicio de ETL sacando información de un csv y insertandolo en una bd apegandome a un caso más real posible 

Errores que voy encontrando
1. En el csv hay campos vacios
2. El csv (de donde saco la informacion) viene con el campo index y no hace falta que en el bd esté el autoincrement 
3. Los campos de la BBDD se llaman diferente de los de la cabecera del csv y para este ejercicio voy a intentar hacerlo lo más real posible intentado no tocar nada en la bd y haciendolo todo desde el codigo porque en un entorno real puedo no tener acceso a la bd
4. Ademas el campo del nombre es obligatorio y hay filas que no lo tienen
5. el problema principal de este ejercicio ha sido que había nombres que tenian tambien ',' dentro y entonces al separador ser la ',' habia problemas, y si faltaba algún dato el split devolvia menos campos y faltaba uno por rellenar
6. y el ultimo problema que tuve es que con el split usaba una expresión regular para que rellenase ese campo vacío con "" y luego eso es lo que se insertaba literalmente en la bd, al final tuve qur forzar que si encontraba "" que lo pusiera a null directamente

aquí si nos damos cuenta el 1 no aparece porque en el csv le quite el nombre, que en esta bd es obligatorio
<img width="1789" height="181" alt="imagen" src="https://github.com/user-attachments/assets/f7c92ccf-1d4b-45ef-b824-bb96fdfb7962" />

y sale aqui en el log 
<img width="1791" height="174" alt="imagen" src="https://github.com/user-attachments/assets/95edb40a-9331-475c-9f5a-70034f44e420" />

