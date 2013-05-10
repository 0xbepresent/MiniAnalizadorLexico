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
TipoDato           => int | double | string  
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

<For>                     ::= for(TipoDato <Asignacion>; <ExpresionBooleana>; <Asignacion>) { EOL <ListaSentencias> }
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

- [ ] Distinguir el operador de suma del de concatenación.
- [ ] Realizar una implementación en un branch que mejore el performance del compilador.

[Identificador]: http://www.debuggex.com/?re=%5Ba-zA-Z%5D%2B%5Cd%2A&flags=&str=identificador15
[Cadena]: http://www.debuggex.com/?re=%22%5B+%5Cw%5D%2A%22&flags=&str=%22Ejemplo+de+cadena%22
[Int]: http://www.debuggex.com/?re=%5Cd%2B&flags=&str=83498320
[Flotante]: http://www.debuggex.com/?re=%5Cd%2B%5C.%5Cd%2B&flags=&str=12323.98