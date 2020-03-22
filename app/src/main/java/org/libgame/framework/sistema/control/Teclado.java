package org.libgame.framework.sistema.control;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;

import org.libgame.framework.Control.EventoTecla;
import org.libgame.framework.util.Pila;
import org.libgame.framework.util.Pila.FactoriaPilaObjetos;

/**
 * @class Teclado
 * @brief clase Teclado
 */
public class Teclado implements OnKeyListener
{

    final static int MAX_TECLAS_NUM = 128;
    final static int MAX_PILA_TECLAS = 100;

    boolean[] teclasPulsadas = new boolean[MAX_TECLAS_NUM];
    Pila<EventoTecla> pilaEventosTecla;
    List<EventoTecla> bufferEventosTecla = new ArrayList<EventoTecla>();    
    List<EventoTecla> eventosTecla = new ArrayList<EventoTecla>();

    public Teclado(View view)
    {

        FactoriaPilaObjetos<EventoTecla> factory = new FactoriaPilaObjetos<EventoTecla>() {

            @Override
            public EventoTecla crearObjeto()
            {

                return new EventoTecla();
            }
        };

        pilaEventosTecla = new Pila<EventoTecla>(factory, MAX_PILA_TECLAS);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    @Override
    public boolean onKey(View view, int codigoTecla, KeyEvent evento)
    {

        if (evento.getAction() == KeyEvent.ACTION_MULTIPLE)
        {

            return false;
        }

        synchronized (this)
        {

            EventoTecla eventoTecla = pilaEventosTecla.nuevoObjeto();
            eventoTecla.codigoTecla = codigoTecla;
            eventoTecla.caracterTecla = (char) evento.getUnicodeChar();

            if (evento.getAction() == android.view.KeyEvent.ACTION_DOWN)
            {

                eventoTecla.tipo = EventoTecla.TECLA_ABAJO;
                if (codigoTecla > 0 && codigoTecla < 127)
                {

                    teclasPulsadas[codigoTecla] = true;
                }
            }

            if (evento.getAction() == android.view.KeyEvent.ACTION_UP)
            {

                eventoTecla.tipo = EventoTecla.TECLA_ARRIBA;

                if (codigoTecla > 0 && codigoTecla < 127)
                {

                    teclasPulsadas[codigoTecla] = false;
                }
            }

            bufferEventosTecla.add(eventoTecla);
        }

        return false;
    }

    public boolean esTeclaPulsada(int codigoTecla)
    {

        if (codigoTecla < 0 || codigoTecla > 127)
        {

            return false;
        }
        return teclasPulsadas[codigoTecla];
    }

    public List<EventoTecla> cogeEventosTecla()
    {

        synchronized (this)
        {

            int len = eventosTecla.size();

            for (int i = 0; i < len; i++)
            {
                pilaEventosTecla.libera(eventosTecla.get(i));
            }

            eventosTecla.clear();
            eventosTecla.addAll(bufferEventosTecla);
            bufferEventosTecla.clear();

            return eventosTecla;
        }
    }
}
