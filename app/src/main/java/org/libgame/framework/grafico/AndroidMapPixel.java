package org.libgame.framework.grafico;

import android.graphics.Bitmap;

import org.libgame.framework.Grafico.FotmatoPixmap;
import org.libgame.framework.MapPixel;

public class AndroidMapPixel implements MapPixel
{

    Bitmap bitmap;
    FotmatoPixmap formato;

    public AndroidMapPixel(Bitmap bitmap, FotmatoPixmap formato)
    {

		this.bitmap = bitmap;
		this.formato = formato;
    }

    @Override
    public int cogeAncho()
    {

		return bitmap.getWidth();
    }

    @Override
    public int cogeAlto()
    {

		return bitmap.getHeight();
    }

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
