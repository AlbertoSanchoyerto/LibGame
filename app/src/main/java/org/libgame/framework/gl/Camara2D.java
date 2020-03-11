package org.libgame.framework.gl;

import javax.microedition.khronos.opengles.GL10;

import org.libgame.framework.matematica.Vector2;

public class Camara2D
{

    public final Vector2 posicion;
    public float zoom;
    public final float frustumAncho;
    public final float frustumAlto;
    final GLGrafico glGraficos;

    public Camara2D(GLGrafico glGraficos, float frustumAncho, float frustumAlto)
    {

		this.glGraficos = glGraficos;
		this.frustumAncho = frustumAncho;
		this.frustumAlto = frustumAlto;
		this.posicion = new Vector2(frustumAncho / 2, frustumAlto / 2);
		this.zoom = 1.0f;
    }

    public void ponVentanaYMatrices()
    {

		GL10 gl = glGraficos.cogeGL();
		gl.glViewport(0, 0, glGraficos.cogeAncho(), glGraficos.cogeAlto());
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(posicion.x - frustumAncho * zoom / 2, posicion.x + frustumAncho * zoom / 2, posicion.y - frustumAlto * zoom / 2, posicion.y + frustumAlto * zoom / 2, 1, -1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
    }

    public void clickAMundo(Vector2 click)
    {

		click.x = (click.x / (float) glGraficos.cogeAncho()) * frustumAncho * zoom;
		click.y = (1 - click.y / (float) glGraficos.cogeAlto()) * frustumAlto * zoom;
		click.suma(posicion).resta(frustumAncho * zoom / 2, frustumAlto * zoom / 2);
    }
}
