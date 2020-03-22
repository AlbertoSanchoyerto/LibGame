package org.libgame.framework.gl;

import javax.microedition.khronos.opengles.GL10;

import org.libgame.framework.matematica.Vector2;

/**
 * @class Camara2D
 * @brief clase Camara2D para graficos en 2D
 */
public class Camara2D
{
    public final Vector2 posicion;
    public float zoom;
    public final float troncoAncho;
    public final float troncoAlto;
    final GLGraficos glGraficos;

    public Camara2D(GLGraficos glGraficos, float troncoAncho, float troncoAlto)
    {
		this.glGraficos = glGraficos;
		this.troncoAncho = troncoAncho;
		this.troncoAlto = troncoAlto;
		this.posicion = new Vector2(troncoAncho / 2, troncoAlto / 2);
		this.zoom = 1.0f;
    }

    public void ponVentanaYMatrices()
    {
		GL10 gl = glGraficos.cogeGL();
		gl.glViewport(0, 0, glGraficos.cogeAncho(), glGraficos.cogeAlto());
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(posicion.x - troncoAncho * zoom / 2, posicion.x + troncoAncho * zoom / 2, posicion.y - troncoAlto * zoom / 2, posicion.y + troncoAlto * zoom / 2, 1, -1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
    }

    public void clickAMundo(Vector2 click)
    {
		click.x = (click.x / (float) glGraficos.cogeAncho()) * troncoAncho * zoom;
		click.y = (1 - click.y / (float) glGraficos.cogeAlto()) * troncoAlto * zoom;
		click.suma(posicion).resta(troncoAncho * zoom / 2, troncoAlto * zoom / 2);
    }
}
