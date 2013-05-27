Definición del lenguaje Mal
===========================

Categorías léxicas
------------------

[Identificador][Identificador] => L+(L|D)*  
[Cadena][Cadena]               => "( |L|D)*"  
[Int][Int]                     => D+  
[Flotante][Flotante]           => D+.D+  
EOL // Salto de línea  
,  
{  
}  
(  
)  
 =  
;  
.  
OperadorRelacional => :: | !: | > | :> | < | :<  
OperadorAritmetico => + | - | * | / | %  
OperadorBooleano   => && | ||  
Booleano           => true | false  
TipoDato           => int | double | string | void 
if  
else  
for  
@ // Concatenación.  


Gramáticas Independientes del Contexto (GIC)
--------------------------------------------

```
<UCompilacion>            ::= <ListaSentenciasPrograma>
<ListaSentenciasPrograma> ::= <SaltosLinea> <SentenciaPrograma> <SaltosLinea> <ListaSentenciasPrograma>
                            | <SentenciaPrograma> <SaltosLinea> <ListaSentenciasPrograma> 
                            | <SentenciaPrograma> <SaltosLinea> 
                            | <SentenciaPrograma>

<SentenciaPrograma>       ::= <DeclaracionMetodo> | <Sentencia>
```

De método
---------

```
<DeclaracionMetodo>       ::= TipoDato Identificador ( <ListaParametros> ) { EOL <ListaSentencias> <Expresion> <SaltosLinea> } 
                            | TipoDato Identificador ( <ListaParametros> ) { EOL <ListaSentencias> }

<ListaParametros>         ::= TipoDato Identificador, <ListaParametros> | TipoDato Identificador | e

<ListaSentencias>         ::= <SaltosLinea> <Sentencia> <SaltosLinea> <ListaSentencias>
                            | <Sentencia> <SaltosLinea> <ListaSentencias> | <Sentencia> <SaltosLinea> | <Sentencia>

<SaltosLinea>             ::= EOL <SaltosLinea> | EOL
```

De sentencia
------------

```
<Sentencia>               ::= <EstructuraControl> | <Declaracion> | <Asignacion> | <InvocacionMetodo>
<Declaracion>             ::= TipoDato <Asignacion> | TipoDato Identificador
<Asignacion>              ::= Identificador = <Expresion>

<InvocacionMetodo>        ::= Identificador.Identificador(<ListaArgumentos>) | Identificador(<ListaArgumentos>)
<ListaArgumentos>         ::= <Expresion>, <ListaArgumentos> | <Expresion> | e
```

De estructuras de control
-------------------------

```
<EstructuraControl>       ::= <If> | <For>
<If>                      ::= if ( <ExpresioBooleana> ) { EOL <ListaSentencias> } EOL else { EOL <ListaSentencias> } 
                            | if ( <ExpresioBooleana> ) { EOL <ListaSentencias> }

<For>                     ::= for(<Declaracion>; <ExpresionBooleana>; <Asignacion>) { EOL <ListaSentencias> }
```


De expresión
------------

```
<Expresion>               ::= <ExpresionAritmetica> | <ExpresionBooleana> | <ExpresionCadena>

<ExpresionAritmetica>     ::= <TerminoAritmetico> OperadorAritmetico <ExpresionAritmetica> | <TerminoAritmetico>
<TerminoAritmetico>       ::= ( <ExpresionAritmetica> ) | Identificador | Int | Double | <InvocacionMetodo>

<ExpresionBooleana>       ::= <TerminoBooleano> OperadorBooleano <ExpresionBooleana> | <TerminoBooleano> | ( <ExpresionBooleana> ) | 
<TerminoBooleano>         ::= <ExpresionRelacional> | <InvocacionMetodo> | Identificador | Booleano

<ExpresionRelacional>     ::= <ExpresionAritmetica> OperadorRelacional <ExpresionAritmetica>

<ExpresionCadena>         ::= <TerminoCadena> @ <TerminoCadena> | <TerminoCadena>
<TerminoCadena>           ::= Identificador | Cadena | <InvocacionMetodo>
```

[ChangeLog.md](ChangeLog.md)
------------

Semántica
---------

* No está soportada la sobrecarga de métodos.
* Las variables se definen de alcance de método o de alcance global.

Comentarios
------------

### Traducción tentativa en Java

```
clase
  declaración de variables estáticas
  declaración de métodos estáticos
  main
    sentencias e invocaciones a métodos
```

* La ultima expresión se toma como retorno para los métodos.

Código ejemplo
--------------

```
int a = 5
double b = 7

string c = "Algo"

string some(){
  c = c @ " mas otra cosa"
  if(c.length() > 3) {
    puts("De longitud mayor a tres")
  }

  c
}

string d = some()
puts(d)
```

TODO
----

- [x] Distinguir el operador de suma del de concatenación.
- [ ] Realizar una implementación en otro branch que mejore el performance del compilador.
- [ ] Corregir GIC de lista de parámetros.
- [ ] Refactorizar las gramáticas independientes del contexto. Ej.: Los bloques de código no deberían tener `Eol` después de las llaves. `{ EOL <ListaSentencias> }`
- [ ] Recuperación de errores semánticos (Modo pánico, producciones adicionales, recuperación a nivel de frase, etc.). 
- [ ] Ponerle scroll al JTree.
- [ ] Semántico: No reconocer variables en los métodos duplicados, las variables dentro de métodos duplicados están siendo reconocidos como variables de alcance global.
- [ ] Semántico: Implementar más reglas semánticas.
- [ ] Relacionar los tokens pertenecientes a los símbolos léxicos de tiras específicas.


[Identificador]: http://www.debuggex.com/r/el49ru8vsqfwPKj-
[Cadena]: http://www.debuggex.com/?re=%22%5B+%5Cw%5D%2A%22&flags=&str=%22Ejemplo+de+cadena%22
[Int]: http://www.debuggex.com/?re=%5Cd%2B&flags=&str=83498320
[Flotante]: http://www.debuggex.com/?re=%5Cd%2B%5C.%5Cd%2B&flags=&str=12323.98