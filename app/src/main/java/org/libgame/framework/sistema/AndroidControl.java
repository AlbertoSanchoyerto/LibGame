package org.libgame.framework.sistema;

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import org.libgame.framework.Control;
import org.libgame.framework.sistema.control.*;

/**
 * @class AndroidControl
 * @brief manejador del Control de sensores
 */
public class AndroidControl implements Control
{
    Acelerometro handlerAccel;
    Teclado handlerTecla;
    Click handlerClick;

    public AndroidControl(Context context, View view, float escalaX, float escalaY)
    {
        handlerAccel = new Acelerometro(context);
        handlerTecla = new Teclado(view);               
        if (Integer.parseInt(VERSION.SDK) < 5)
        { 
            handlerClick = new SimpleClick(view, escalaX, escalaY);
        }
        else
        {
            handlerClick = new MultiClick(view, escalaX, escalaY);
        }
    }

    /**
     * @fn esTeclaPulsada
     * @brief verifica si el codigoTecla se ha pulsado
     * @return boolean si se ha pulsado
     */
    @Override
    public boolean esTeclaPulsada(int codigoTecla)
    {
        return handlerTecla.esTeclaPulsada(codigoTecla);
    }

    /**
     * @fn esClickPulsado
     * @brief verifica si se ha hecho click
     * @return boolean si se ha hecho click
     */
    @Override
    public boolean esClickPulsado(int pos)
    {
        return handlerClick.esClickPulsado(pos);
    }

    /**
     * @fn cogeClickX
     * @brief obtiene la posicion X del click
     * @return int x
     */
    @Override
    public int cogeClickX(int pos)
    {
        return handlerClick.cogeClickX(pos);
    }

    /**
     * @fn cogeClickY
     * @brief obtiene la posicio Y del click
     * @return int y
     */
    @Override
    public int cogeClickY(int pos)
    {
        return handlerClick.cogeClickY(pos);
    }

    /**
     * @fn cogeAccelX
     * @brief obtiene la aceleracion X
     * @return int x
     */
    @Override
    public float cogeAccelX()
    {
        return handlerAccel.cogeAccelX();
    }

    /**
     * @fn cogeAccelY
     * @brief obtiene la aceleracion Y
     * @return int y
     */
    @Override
    public float cogeAccelY()
    {
        return handlerAccel.cogeAccelY();
    }

    /**
     * @fn cogeAccelZ
     * @brief obtiene la aceleracion Z
     * @return int z
     */
    @Override
    public float cogeAccelZ()
    {
        return handlerAccel.cogeAccelZ();
    }

    /**
     * @fn cogeEventosClick
     * @brief obtiene una lista de EventoClick
     * @return List<EventoClick> lista de EventoClick
     */
    @Override
    public List<EventoClick> cogeEventosClick()
    {
        return handlerClick.cogeEventosClick();
    }

    /**
     * @fn cogeEventosTecla
     * @brief obtiene una lista de EventoTecla
     * @return List<EventoTecla> lista de EventorTecla
     */
    @Override
    public List<EventoTecla> cogeEventosTecla()
    {
        return handlerTecla.cogeEventosTecla();
    }
}
