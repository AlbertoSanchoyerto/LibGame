package org.libgame.framework.util;

import java.util.ArrayList;
import java.util.List;

public class Pila<T>
{

    public interface FactoriaPilaObjetos<T>
    {

		public T crearObjeto();
    }

    private final List<T> objetosLibres;
    private final FactoriaPilaObjetos<T> factoria;
    private final int maxTam;

    public Pila(FactoriaPilaObjetos<T> factoria, int maxTam)
    {

		this.factoria = factoria;
		this.maxTam = maxTam;
		this.objetosLibres = new ArrayList<T>(maxTam);
    }

    public T nuevoObjeto()
    {

		T objeto = null;

		if (objetosLibres.size() == 0)
		{

			objeto = factoria.crearObjeto();
		}
		else
		{

			objeto = objetosLibres.remove(objetosLibres.size() - 1);
		}

		return objeto;
    }

    public void libera(T objeto)
    {

		if (objetosLibres.size() < maxTam)
		{

			objetosLibres.add(objeto);
		}
    }
}
