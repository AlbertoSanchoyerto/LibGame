package org.libgame.framework;

import java.util.List;

public interface Control
{

    // Clase EventoTecla
    public static class EventoTecla
    {

	public static final int TECLA_ABAJO = 0;
	public static final int TECLA_ARRIBA = 1;

	public int tipo;
	public int codigoTecla;
	public char caracterTecla;

	public String toString()
	{

	    StringBuilder builder = new StringBuilder();

	    if (tipo == TECLA_ABAJO)
	    {

		builder.append("tecla pulsa, ");
	    }
	    else
	    {

		builder.append("tecla suelta, ");
	    }

	    builder.append(codigoTecla);
	    builder.append(",");
	    builder.append(caracterTecla);

	    return builder.toString();
	}
    }

    // Clase EventoClick
    public static class EventoClick
    {

	public static final int CLICK_ABAJO = 0;
	public static final int CLICK_ARRIBA = 1;
	public static final int CLICK_MOVIENDO = 2;

	public int tipo;
	public int x, y;
	public int pos;

	public String toString()
	{

	    StringBuilder builder = new StringBuilder();

	    if (tipo == CLICK_ABAJO)
	    {

		builder.append("Click abajo, ");
	    }
	    else if (tipo == CLICK_MOVIENDO)
	    {

		builder.append("click moviendo, ");
	    }
	    else
	    {
		builder.append("Click arriba, ");
	    }

	    builder.append(pos);
	    builder.append(",");
	    builder.append(x);
	    builder.append(",");
	    builder.append(y);

	    return builder.toString();
	}
    }

    public boolean esTeclaPulsada(int keyCode);

    public boolean esClickPulsado(int pointer);

    public int cogeClickX(int pointer);

    public int cogeClickY(int pointer);

    public float cogeAccelX();

    public float cogeAccelY();

    public float cogeAccelZ();

    public List<EventoTecla> cogeEventosTecla();

    public List<EventoClick> cogeEventosClick();
}
