package org.libgame.framework.game;

import java.util.ArrayList;
import java.util.List;

import org.libgame.framework.game.ObjetoGame;

import android.util.FloatMath;

/**
 * @class Cuadricula
 * @brief clase Cuadricula
 * crea y maneja una cuadriculad de elentos de juego con objetos estaticos y objetos dinamicos
 */
public class Cuadricula
{
    List<ObjetoGame>[] celdasDinamicas;
    List<ObjetoGame>[] celdasEstaticas;
    int celdasPorFila;
    int celdasPorColumnas;
    float tamCelda;
    int[] idsCeldas = new int[4];
    List<ObjetoGame> objetosEncontrados;

    @SuppressWarnings("unchecked")
    public Cuadricula(float anchoMundo, float altoMundo, float tamCelda)
    {
        this.tamCelda = tamCelda;
        this.celdasPorFila = (int)Math.ceil(anchoMundo / tamCelda);
        this.celdasPorColumnas = (int)Math.ceil(altoMundo / tamCelda);
        int numCeldas = celdasPorFila * celdasPorColumnas;
        celdasDinamicas = new List[numCeldas];
        celdasEstaticas = new List[numCeldas];
        for (int i = 0; i < numCeldas; i++)
        {
            celdasDinamicas[i] = new ArrayList<ObjetoGame>(10);
            celdasEstaticas[i] = new ArrayList<ObjetoGame>(10);
        }
        objetosEncontrados = new ArrayList<ObjetoGame>(10);
    }

	/**
	 * @fn insertaObjetoEstatico
	 * @param ObjetoGame obj
	 */
    public void insertaObjetoEstatico(ObjetoGame obj)
    {
        int[] idsCeldas = cogeIdsCeldas(obj);
        int i = 0;
        int idCelda = -1;
        while (i <= 3 && (idCelda = idsCeldas[i++]) != -1)
        {
            celdasEstaticas[idCelda].add(obj);
        }
    }

	/**
	 * @fn insertaObjetoDinamico
	 * @param ObjetoGame obj
	 */
    public void insertaObjetoDinamico(ObjetoGame obj)
    {
        int[] idsCeldas = cogeIdsCeldas(obj);
        int i = 0;
        int idCelda = -1;
        while (i <= 3 && (idCelda = idsCeldas[i++]) != -1)
        {
            celdasDinamicas[idCelda].add(obj);
        }
    }

	/**
	 * @fn quitaObjeto
	 * @param ObjetoGame obj
	 */
    public void quitaObjeto(ObjetoGame obj)
    {
        int[] idsCeldas = cogeIdsCeldas(obj);
        int i = 0;
        int idCelda = -1;
        while (i <= 3 && (idCelda = idsCeldas[i++]) != -1)
        {
            celdasDinamicas[idCelda].remove(obj);
            celdasEstaticas[idCelda].remove(obj);
        }
    }

	/**
	 * @fn limpiaCeldasDinamicas
	 * @param ObjetoGame obj
	 */
    public void limpiaCeldasDinamicas(ObjetoGame obj)
    {
        int len = celdasDinamicas.length;
        for (int i = 0; i < len; i++)
        {
            celdasDinamicas[i].clear();
        }
    }

	/**
	 * @fn cogeColisionadoresPontenciales
	 * @param ObjetoGame obj
	 * @return List<ObjetoGame> objetosEncontrados
	 */
    public List<ObjetoGame> cogeColisionadoresPontenciales(ObjetoGame obj)
    {
        objetosEncontrados.clear();
        int[] idsCeldas = cogeIdsCeldas(obj);
        int i = 0;
        int idCelda = -1;
        while (i <= 3 && (idCelda = idsCeldas[i++]) != -1)
        {
            int len = celdasDinamicas[idCelda].size();
            for (int j = 0; j < len; j++)
            {
                ObjetoGame collider = celdasDinamicas[idCelda].get(j);
                if (!objetosEncontrados.contains(collider))
                    objetosEncontrados.add(collider);
            }

            len = celdasEstaticas[idCelda].size();
            for (int j = 0; j < len; j++)
            {
                ObjetoGame collider = celdasEstaticas[idCelda].get(j);
                if (!objetosEncontrados.contains(collider))
                    objetosEncontrados.add(collider);
            }
        }
        return objetosEncontrados;
    }
	
    public int[] cogeIdsCeldas(ObjetoGame obj)
    {
        int x1 = (int)Math.floor(obj.limites.min.x / tamCelda);
        int y1 = (int)Math.floor(obj.limites.min.y / tamCelda);
        int x2 = (int)Math.floor((obj.limites.min.x + obj.limites.ancho) / tamCelda);
        int y2 = (int)Math.floor((obj.limites.min.y + obj.limites.alto) / tamCelda);

        if (x1 == x2 && y1 == y2)
        {
            if (x1 >= 0 && x1 < celdasPorFila && y1 >= 0 && y1 < celdasPorColumnas)
                idsCeldas[0] = x1 + y1 * celdasPorFila;
            else
                idsCeldas[0] = -1;
            idsCeldas[1] = -1;
            idsCeldas[2] = -1;
            idsCeldas[3] = -1;
        }
        else if (x1 == x2)
        {
            int i = 0;
            if (x1 >= 0 && x1 < celdasPorFila)
            {
                if (y1 >= 0 && y1 < celdasPorColumnas)
                    idsCeldas[i++] = x1 + y1 * celdasPorFila;
                if (y2 >= 0 && y2 < celdasPorColumnas)
                    idsCeldas[i++] = x1 + y2 * celdasPorFila;
            }
            while (i <= 3) idsCeldas[i++] = -1;
        }
        else if (y1 == y2)
        {
            int i = 0;
            if (y1 >= 0 && y1 < celdasPorColumnas)
            {
                if (x1 >= 0 && x1 < celdasPorFila)
                    idsCeldas[i++] = x1 + y1 * celdasPorFila;
                if (x2 >= 0 && x2 < celdasPorFila)
                    idsCeldas[i++] = x2 + y1 * celdasPorFila;
            }
            while (i <= 3) idsCeldas[i++] = -1;                       
        }
        else
        {
            int i = 0;
            int y1celdasPorFila = y1 * celdasPorFila;
            int y2celdasPorFila = y2 * celdasPorFila;
            if (x1 >= 0 && x1 < celdasPorFila && y1 >= 0 && y1 < celdasPorColumnas)
                idsCeldas[i++] = x1 + y1celdasPorFila;
            if (x2 >= 0 && x2 < celdasPorFila && y1 >= 0 && y1 < celdasPorColumnas)
                idsCeldas[i++] = x2 + y1celdasPorFila;
            if (x2 >= 0 && x2 < celdasPorFila && y2 >= 0 && y2 < celdasPorColumnas)
                idsCeldas[i++] = x2 + y2celdasPorFila;
            if (x1 >= 0 && x1 < celdasPorFila && y2 >= 0 && y2 < celdasPorColumnas)
                idsCeldas[i++] = x1 + y2celdasPorFila;
            while (i <= 3) idsCeldas[i++] = -1;
        }
        return idsCeldas;
    }
}
