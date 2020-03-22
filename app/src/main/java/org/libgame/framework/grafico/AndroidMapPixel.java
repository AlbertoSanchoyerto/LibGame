package org.libgame.framework.grafico;

import android.graphics.Bitmap;

import org.libgame.framework.Graficos.FotmatoPixmap;
import org.libgame.framework.MapPixel;

/**
 * @class AndroidMapPixel
 * @brief clase AndroidMapPixel clase que define un mapa de bits
 */
public class AndroidMapPixel implements MapPixel
{
    Bitmap bitmap;
    FotmatoPixmap formato;

    public AndroidMapPixel(Bitmap bitmap, FotmatoPixmap formato)
    {
        this.bitmap = bitmap;
        this.formato = formato;
    }

    /**
     * @fn cogeAncho
     * @return int ancho
     */
    @Override
    public int cogeAncho()
    {
        return bitmap.getWidth();
    }

    /**
     * @fn cogeAlto
     * @return int alto
     */
    @Override
    public int cogeAlto()
    {
        return bitmap.getHeight();
    }

    /**
     * @fn cogeFormato
     * @return FotmatoPixmap formato
     */
    @Override
    public FotmatoPixmap cogeFormato()
    {
        return formato;
    }

    @Override
    public void libera()
    {
        bitmap.recycle();
    }
}
