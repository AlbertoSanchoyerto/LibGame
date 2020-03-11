package org.libgame.framework.grafico;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import org.libgame.framework.Grafico;
import org.libgame.framework.MapPixel;

public class AndroidGraficos implements Grafico
{

    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraficos(AssetManager assets, Bitmap frameBuffer)
    {

		this.assets = assets;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
    }

    @Override
    public MapPixel nuevoMapPixel(String nombreFichero, FotmatoPixmap formato)
    {

		Config configuracion = null;
		if (formato == FotmatoPixmap.RGB565)
		{

			configuracion = Config.RGB_565;
		}
		else if (formato == FotmatoPixmap.ARGB4444)
		{

			configuracion = Config.ARGB_4444;
		}
		else
		{

			configuracion = Config.ARGB_8888;
		}

		Options opciones = new Options();
		opciones.inPreferredConfig = configuracion;

		InputStream in = null;
		Bitmap bitmap = null;

		try
		{

			in = assets.open(nombreFichero);
			bitmap = BitmapFactory.decodeStream(in);
			if (bitmap == null)
			{

				throw new RuntimeException("No puede cargar bitmat de asset '" + nombreFichero + "'");
			}
		}
		catch (IOException e)
		{

			throw new RuntimeException("No puede cargar bitmap de asset '" + nombreFichero + "'");
		}
		finally
		{

			if (in != null)
			{

				try
				{

					in.close();
				}
				catch (IOException e)
				{
				}
			}
		}

		if (bitmap.getConfig() == Config.RGB_565)
		{

			formato = FotmatoPixmap.RGB565;
		}
		else if (bitmap.getConfig() == Config.ARGB_4444)
		{

			formato = FotmatoPixmap.ARGB4444;
		}
		else
		{

			formato = FotmatoPixmap.ARGB8888;
		}

		return new AndroidMapPixel(bitmap, formato);
    }

    @Override
    public void borra(int color)
    {

		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    @Override
    public void dibujaPixel(int x, int y, int color)
    {

		paint.setColor(color);
		canvas.drawPoint(x, y, paint);
    }

    @Override
    public void dibujaLinea(int x, int y, int x2, int y2, int color)
    {

		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void dibujaRectangulo(int x, int y, int ancho, int alto, int color)
    {

		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, x + ancho - 1, y + alto - 1, paint);
    }

    @Override
    public void dibujaMapPixel(MapPixel mapPixel, int x, int y, int srcX, int srcY, int srcAncho, int srcAlto)
    {

		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right = srcX + srcAncho - 1;
		srcRect.bottom = srcY + srcAlto - 1;

		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x + srcAncho - 1;
		dstRect.bottom = y + srcAlto - 1;

		canvas.drawBitmap(((AndroidMapPixel) mapPixel).bitmap, srcRect, dstRect, null);
    }

    @Override
    public void dibujaMapPixel(MapPixel mapPixel, int x, int y)
    {

		canvas.drawBitmap(((AndroidMapPixel)mapPixel).bitmap, x, y, null);
    }

    @Override
    public int cogeAncho()
    {

		return frameBuffer.getWidth();
    }

    @Override
    public int cogeAlto()
    {

		return frameBuffer.getHeight();
    }
}
