# miniELTL

> [!NOTE]
> En este repositorio voy a hacer un ejercicio de ETL sacando información de un csv y insertandolo en una bd apegandome a un caso más real posible 

Errores que voy encontrando
1. En el csv hay campos vacios
2. El csv (de donde saco la informacion) viene con el campo index y no hace falta que en el bd esté el autoincrement 
3. Los campos de la BBDD se llaman diferente de los de la cabecera del csv y para este ejercicio voy a intentar hacerlo lo más real posible intentado no tocar nada en la bd y haciendolo todo desde el codigo porque en un entorno real puedo no tener acceso a la bd
4. Ademas el campo del nombre es obligatorio y hay filas que no lo tienen 
