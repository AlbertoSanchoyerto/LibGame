# Un FrameWork llamado LibGame

Este FrameWork se divide por el momento en las siguientes secciones:

* Audio. Lleva el cometido principal de la reproducción de musica y sonidos.
* Game. Lleva los elementos basicos de un Juego.
* GL. Lleva los elementos que operan con OpenGL.
* Grafico. Junto con GL lleva todo el cometido con los graficos junto con GL.
* Matematica. Lleva los calculos principales.
* Util. Lleva las herramientas utiles varias.
* Sistema. Lleva el control con los elemento de hardware, clicks, teclado, sensores, ficheros...

## 1. Game/GLGame

GLGame es derivada de Game, s núcleo principal de la vida del Juego, contenedor de las partes básicas como (Controles, Ficheros, Graficos, Audios).
En esta tenemos los estados de vida del Juego que son:

* Inicializando
* Corriendo
* Pausado
* Terminado
* Ocioso

### -Incializando

Coge la pantalla inicial y pasa al estado _corriendo_.

### -Corriendo

actualiza y presenta la *Pantalla*.

### -Pausado

Se pausa la *Pantalla* y se pasa al estado _Ocioso_.

### -Terminado

Se pausa y se libera la *Pantalla* y se pasa al estado _Ocioso_.

### -Ocioso

No hace nada.

## 2. Pantalla/GLPantalla

GLPantalla es derivada de Pantalla, La GLPantalla es donde se involucran todos los demás elementos de Juego. La Pantalla tiene las siguientes acciones que se pueden programar.
Esta son:

* actualiza
* presenta
* pausa
* resumen
* libera

### actualiza y presenta

Son dos principales acciones que se realizan en el transcurso del juego, de forma constante y repetitiva.

En la accion de *actualiza* se utiliza para activar o desactivar audios, recoger los eventos de control (clicks, teclado, sensores), actualiza las posiciones de objetos, estados, puntuaciones.

Y en la accion de *presenta* es la que se utiliza para el pintado o representacion de los objetos y graficos del juego.

### pausa y resumen

En esta accion ponemos los elementos del juego en pausa y retenemos el estado en el que se encontraba.

En esta accion recuperamoa es estado del juego despues de una pausa.

### libera

Es esta accion es cuando se termina definitivamente con la pantalla, es cuando eliminamos, y liberamos todos los estados de forma irrecuperable.

## 3. Grafico/GLGrafico/MapPixel

GLGame es derivada de Grafico. Pantalla tiene asignado un grafico, este se puede utilizar para pintar sobre el o como un elemento a pintar sobre otro grafico.

En un grafico se pueden pintar lineas, puntos y figuras. y tambien pintar Mapas de bits (MapPixel), de hecho puede ser un Mapa de pixels.

## 4. ObjetoGame/ObjetoDinamicoGame

ObjetoDinamicoGame es derivado de ObjetoGame. 

## 5. Textura/TexturaRegion

### GrupoImagenes/Animacion

### Fuente

## 5. Audio

### Sonido

### Musica

## 6. Matematica

### TesteoColision

## 7. Sistema

### FicheroIO

### Control/Click/Teclado/Acelerometro

## 8. Util

### Color

### Pila

