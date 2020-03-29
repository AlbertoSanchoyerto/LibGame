package org.libgame.framework.gl;

import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView;

/**
 * @class GLGraficos
 * @brief clase GLGraficos graficos openGL
 */
public class GLGraficos
{
    GLSurfaceView glVista;
    GL10 gl;

    GLGraficos(GLSurfaceView glVista)
    {
        this.glVista = glVista;
    }

	/**
	 * @cogeGL
	 * @return GL10 gl
	 */
    public GL10 cogeGL()
    {
        return gl;
    }

	/**
	 * @ponGL
	 * @param GL10 gl
	 */
    void ponGL(GL10 gl)
    {
        this.gl = gl;
    }

	/**
	 * @fn cogeAncho
	 * @return int ancho
	 */
    public int cogeAncho()
    {
        return glVista.getWidth();
    }

	/**
	 * @fn cogeAlto
	 * @return in alto
	 */
    public int cogeAlto()
    {
        return glVista.getHeight();
    }
}
