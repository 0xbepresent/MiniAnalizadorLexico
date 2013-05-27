Del proyecto
------------

* Se agregan TODOs al README.
* Se actualiza el código fuente de ejemplo con pruebas para la deteccción de errores semánticos implementados.


Cambios en el funcionamiento
----------------------------

* Se implementan dos reglas del analizador semántico. Se verifica que no haya duplicación de símbolos para:
  * Declaraciones de métodos.
  * Declaraciones de variables (Se definen de alcance de método o de alcance global).
* La tabla de símbolos implementa la detección de símbolos duplicados.
* Se postergo la creación de símbolos léxicos para los identificadores en el analizador semántico. En el análizador léxico solo se crean símbolos para los literales.
* Se agrego línea y columna para los tokens reconocidos en el análisis léxico.
* Se cambió el atributo `valor` de `Token` por una referencia directa a un símbolo léxico.
* Se cambió la implementación de la tabla de símbolos (de mapa a lista), además, los tokens pueden tener una referencia directa al símbolo que representan.
* Se agregan métodos para recorrer el árbol.
* Se crean las clases `SimboloMetodo` y `SimboloVariable`.

Corrección de bugs
------------------

* Se agrega el tipo de dato void a la definición del lenguaje.
* Se implementa correctamente la gramática `For`.
* Se corrige la implementación de la categoría léxica `Identificador.
* Se corrige la ER de identificador en debuggex.com.
* Se eliminó la creación de símbolo léxicos para las `CategoriaLexicaPalabras` y `CategoriaLexicaUnCaracter`, estos símbolo se deben instalar en como tiras específicas en la creación de la tabla de símbolos.
* Se elimina un código de depuración de `ListaSentencias`.

Mejoras
-------

Ninguna.

d16c8dd1fb3678b0cc792e29863b15dad8dad932
========================================

Del proyecto
------------

* Se agrega archivo README con la definición del lenguaje.
  * ER en debuggex.com
  * Definición de las GIC básicas del lenguaje.
  * Código de ejemplo que puede derivarse completamente por el analizador sintáctico.
  * Comentarios a tener en cuenta en las siguientes fases del desarrollo del compilador.
  * Lista de TODOs.

* Se agrega el archivo de `ChangeLog.md`.

Cambios en el funcionamiento
----------------------------

* Se estable `UnidadCompilacion.class` como la raiz de derivación y se implementan todas las categorías sintácticas necesarias.
* Se agrega el tipo de dato `void` a la categoría léxica `TipoDato`.
* Se implementa un nuevo modo (`MAYOR_CONSUMO_TOKENS`) para la aceptación de derivaciones sintáticas.
  * El modo permite tratar de derivar todas las posibles producciones de una GIC y quedarse con la
  que más tokens haya consumido.
  * Se agrega un atributo para establecer el modo de derivación de las gramáticas (por defecto el 
  modo de derivación es el de `PRIORIDAD_ORDEN_PRODUCCIONES`).
* Se soporta la producción `epsilon` para que se pueda escribir como `null`.
Ej.: Identificador | e ==>`producciones = { { Identificador.class }, { null } };`

Corrección de bugs
------------------

* Se cambia el nombre de la clase para la categoría léxica de Doble a Flotante, para evitar
la confusión con la clase `java.lang.Double`.
* Correción en la definición de la gramática para `ExpresionCadena`.

Mejoras
-------

* Representación visual del árbol de derivación por medio de `JTree`.
* Conteo de tokens en la clase de `Derivación`.
* Refactor de la implementación de la carga de archivos por medio de la más actual API `java.nio`.
* El `toString` de `NodoImplementado` usa el método de conteo de la clase `Derivacion`. Se rebaja
la indentación de a dos espacios.