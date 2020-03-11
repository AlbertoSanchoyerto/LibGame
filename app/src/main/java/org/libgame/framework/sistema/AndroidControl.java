package org.libgame.framework.sistema;

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import org.libgame.framework.Control;
import org.libgame.framework.sistema.control.*;

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

    @Override
    public boolean esTeclaPulsada(int codigoTecla)
    {

	return handlerTecla.esTeclaPulsada(codigoTecla);
    }

    @Override
    public boolean esClickPulsado(int pos)
    {

	return handlerClick.esClickPulsado(pos);
    }

    @Override
    public int cogeClickX(int pos)
    {

	return handlerClick.cogeClickX(pos);
    }

    @Override
    public int cogeClickY(int pos)
    {

	return handlerClick.cogeClickY(pos);
    }

    @Override
    public float cogeAccelX()
    {

	return handlerAccel.cogeAccelX();
    }

    @Override
    public float cogeAccelY()
    {

	return handlerAccel.cogeAccelY();
    }

    @Override
    public float cogeAccelZ()
    {

	return handlerAccel.cogeAccelZ();
    }

    @Override
    public List<EventoClick> cogeEventosClick()
    {

	return handlerClick.cogeEventosClick();
    }

    @Override
    public List<EventoTecla> cogeEventosTecla()
    {

	return handlerTecla.cogeEventosTecla();
    }
}
