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

    public GL10 cogeGL()
    {
        return gl;
    }

    void ponGL(GL10 gl)
    {
        this.gl = gl;
    }

    public int cogeAncho()
    {
        return glVista.getWidth();
    }

    public int cogeAlto()
    {
        return glVista.getHeight();
    }
}
