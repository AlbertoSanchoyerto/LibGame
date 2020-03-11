package org.libgame.framework;

import org.libgame.framework.Grafico.FotmatoPixmap;

public interface MapPixel
{

    public int cogeAncho();

    public int cogeAlto();

    public FotmatoPixmap cogeFormato();

    public void libera();
}
