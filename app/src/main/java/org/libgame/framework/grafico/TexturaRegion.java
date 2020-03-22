package org.libgame.framework.grafico;

/**
 * @class TexturaRegion
 * @brief clase TexturaRegion define un trozo de una Textura
 */
public class TexturaRegion
{
    public final float u1, v1;
    public final float u2, v2;
    public final Textura textura;

    public TexturaRegion(Textura textura, float x, float y, float ancho, float alto)
    {
		this.u1 = x / textura.ancho;
		this.v1 = y / textura.alto;
		this.u2 = this.u1 + ancho / textura.ancho;
		this.v2 = this.v1 + alto / textura.alto;        
		this.textura = textura;
    }
}
