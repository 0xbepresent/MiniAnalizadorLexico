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

* Representación visual del arbol de derivación por medio de `JTree`.
* Conteo de tokens en la clase de `Derivación`.
* Refactor de la implementación de la carga de archivos por medio de la más actual API `java.nio`.
* El `toString` de `NodoImplementado` usa el método de conteo de la clase `Derivacion`. Se rebaja
la indentación de a dos espacios.