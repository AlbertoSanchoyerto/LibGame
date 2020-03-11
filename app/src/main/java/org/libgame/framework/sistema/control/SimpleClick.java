package org.libgame.framework.sistema.control;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;

import org.libgame.framework.util.Pila;
import org.libgame.framework.Control.EventoClick;
import org.libgame.framework.util.Pila.FactoriaPilaObjetos;

public class SimpleClick implements Click
{
    final static int MAX_PILA_CLICKS = 100;
	
    boolean esClickPulsado;
    int clickX;
    int clickY;
    Pila<EventoClick> pilaEventosClick;
    List<EventoClick> eventosClick = new ArrayList<EventoClick>();
    List<EventoClick> bufferEventosClick = new ArrayList<EventoClick>();
    float escalaX;
    float escalaY;

    public SimpleClick(View view, float escalaX, float escalaY)
    {	
		FactoriaPilaObjetos<EventoClick> factoria = new FactoriaPilaObjetos<EventoClick>() {

			@Override
			public EventoClick crearObjeto()
			{	
				return new EventoClick();
			}            
		};

		pilaEventosClick = new Pila<EventoClick>(factoria, MAX_PILA_CLICKS);
		view.setOnTouchListener(this);

		this.escalaX = escalaX;
		this.escalaY = escalaY;
    }

    @Override
    public boolean onTouch(View view, MotionEvent evento)
    {

		synchronized (this)
		{

			EventoClick eventoClick = pilaEventosClick.nuevoObjeto();
			switch (evento.getAction())
			{

				case MotionEvent.ACTION_DOWN:
					eventoClick.tipo = EventoClick.CLICK_ABAJO;
					esClickPulsado = true;
					break;

				case MotionEvent.ACTION_MOVE:
					eventoClick.tipo = EventoClick.CLICK_MOVIENDO;
					esClickPulsado = true;
					break;

				case MotionEvent.ACTION_CANCEL:                
				case MotionEvent.ACTION_UP:
					eventoClick.tipo = EventoClick.CLICK_ARRIBA;
					esClickPulsado = false;
					break;
			}

			eventoClick.x = clickX = (int)(evento.getX() * escalaX);
			eventoClick.y = clickY = (int)(evento.getY() * escalaY);
			bufferEventosClick.add(eventoClick);                        

			return true;
		}
    }

    @Override
    public boolean esClickPulsado(int pos)
    {

		synchronized (this)
		{

			if (pos == 0)
			{

				return esClickPulsado;
			}
			else
			{

				return false;
			}
		}
    }

    @Override
    public int cogeClickX(int pos)
    {

		synchronized (this)
		{

			return clickX;
		}
    }

    @Override
    public int cogeClickY(int pos)
    {
		synchronized (this)
		{

			return clickY;
		}
    }

    @Override
    public List<EventoClick> cogeEventosClick()
    {

		synchronized (this)
		{

			int len = eventosClick.size();
			for (int i = 0; i < len; i++)
			{

				pilaEventosClick.libera(eventosClick.get(i));
			}

			eventosClick.clear();
			eventosClick.addAll(bufferEventosClick);
			bufferEventosClick.clear();

			return eventosClick;
		}
    }
}
