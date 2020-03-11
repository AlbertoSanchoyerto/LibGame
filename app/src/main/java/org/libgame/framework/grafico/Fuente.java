package org.libgame.framework.grafico;

public class Fuente
{

    public final Textura textura;
    public final int glifoAncho;
    public final int glifoAlto;
    public final TexturaRegion[] glifos = new TexturaRegion[96];   

    public Fuente(Textura textura, int offsetX, int offsetY, int glifosPorFila, int glifoAncho, int glifoAlto)
    {

		this.textura = textura;
		this.glifoAncho = glifoAncho;
		this.glifoAlto = glifoAlto;
		int x = offsetX;
		int y = offsetY;
		for (int i = 0; i < 96; i++)
		{

			glifos[i] = new TexturaRegion(textura, x, y, glifoAncho, glifoAlto);
			x += glifoAncho;
			if (x == offsetX + glifosPorFila * glifoAncho)
			{

				x = offsetX;
				y += glifoAlto;
			}
		}
    }

    public void dibujaTexto(GrupoImagen batcher, String texto, float x, float y)
    {

		int len = texto.length();
		for (int i = 0; i < len; i++)
		{

			int c = texto.charAt(i) - ' ';
			if (c < 0 || c > glifos.length - 1)
			{

				continue;
			}

			TexturaRegion glifo = glifos[c];
			batcher.dibujaSprite(x, y, glifoAncho, glifoAlto, glifo);
			x += glifoAncho;
		}
    }
}
