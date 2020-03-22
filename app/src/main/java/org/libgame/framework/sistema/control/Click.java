package org.libgame.framework.sistema.control;

import java.util.List;

import android.view.View.OnTouchListener;

import org.libgame.framework.Control.EventoClick;

/**
 * @interface Click
 * @brief interface Click
 */
public interface Click extends OnTouchListener
{
    public boolean esClickPulsado(int pos);

    public int cogeClickX(int pos);

    public int cogeClickY(int pos);

    public List<EventoClick> cogeEventosClick();
}
