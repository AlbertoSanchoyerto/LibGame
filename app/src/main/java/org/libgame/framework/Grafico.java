package org.libgame.framework;

public interface Grafico
{

    public static enum FotmatoPixmap
    {
		
		ARGB8888, ARGB4444, RGB565
		}

    public MapPixel nuevoMapPixel(String nombreFichero, FotmatoPixmap formato);

    public void borra(int color);

    public void dibujaPixel(int x, int y, int color);

    public void dibujaLinea(int x, int y, int x2, int y2, int color);

    public void dibujaRectangulo(int x, int y, int ancho, int alto, int color);

    public void dibujaMapPixel(MapPixel mapPixel, int x, int y, int srcX, int srcY,
							   int srcAncho, int srcAlto);

    public void dibujaMapPixel(MapPixel mapPixel, int x, int y);

    public int cogeAncho();

    public int cogeAlto();
}
