package org.libgame.framework.sistema.control;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;

import org.libgame.framework.Control.EventoClick;
import org.libgame.framework.util.Pila;
import org.libgame.framework.util.Pila.FactoriaPilaObjetos;
import java.util.*;

public class MultiClick implements Click
{
    final static int MAX_CLICKS = 20;
    final static int MAX_PILA_CLICKS = 100;

    boolean[] esClickPulsado = new boolean[MAX_CLICKS];
    int[] clickX = new int[MAX_CLICKS];
    int[] clickY = new int[MAX_CLICKS];
    Pila<EventoClick> pilaEventosClick;
    List<EventoClick> eventosClick = new ArrayList<EventoClick>();
    List<EventoClick> bufferEventosClick = new ArrayList<EventoClick>();
    float escalaX;
    float escalaY;

    public MultiClick(View view, float escalaX, float escalaY)
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
			int accion = evento.getAction() & MotionEvent.ACTION_MASK;
			int posIndex = (evento.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
			int posId = evento.getPointerId(posIndex);
			EventoClick eventoClick;

			switch (accion)
			{
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
					eventoClick = pilaEventosClick.nuevoObjeto();
					eventoClick.tipo = EventoClick.CLICK_ABAJO;
					eventoClick.pos = posId;
					eventoClick.x = clickX[posId] = (int)(evento.getX(posIndex) * escalaX);
					eventoClick.y = clickY[posId] = (int)(evento.getY(posIndex) * escalaY);
					esClickPulsado[posId] = true;
					bufferEventosClick.add(eventoClick);
					break;

				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				case MotionEvent.ACTION_CANCEL:
					eventoClick = pilaEventosClick.nuevoObjeto();
					eventoClick.tipo = EventoClick.CLICK_ARRIBA;
					eventoClick.pos = posId;
					eventoClick.x = clickX[posId] = (int)(evento.getX(posIndex) * escalaX);
					eventoClick.y = clickY[posId] = (int)(evento.getY(posIndex) * escalaY);
					esClickPulsado[posId] = false;
					bufferEventosClick.add(eventoClick);
					break;

				case MotionEvent.ACTION_MOVE:
					int num_punteros = evento.getPointerCount();
					for (int i = 0; i < num_punteros; i++)
					{
						posIndex = i;
						posId = evento.getPointerId(posIndex);

						eventoClick = pilaEventosClick.nuevoObjeto();
						eventoClick.tipo = EventoClick.CLICK_MOVIENDO;
						eventoClick.pos = posId;
						eventoClick.x = clickX[posId] = (int) (evento.getX(posIndex) * escalaX);
						eventoClick.y = clickY[posId] = (int) (evento.getY(posIndex) * escalaY);
						bufferEventosClick.add(eventoClick);
					}
					break;
			}

			return true;
		}
    }

    @Override
    public boolean esClickPulsado(int pos)
    {
		synchronized (this)
		{
			if (pos < 0 || pos >= 20)
			{
				return false;
			}
			else
			{
				return esClickPulsado[pos];
			}
		}
    }

    @Override
    public int cogeClickX(int pos)
    {
		synchronized (this)
		{
			if (pos < 0 || pos >= 20)
			{

				return 0;
			}
			else
			{

				return clickX[pos];
			}
		}
    }

    @Override
    public int cogeClickY(int pos)
    {
		synchronized (this)
		{
			if (pos < 0 || pos >= 20)
			{
				return 0;
			}
			else
			{
				return clickY[pos];
			}
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
