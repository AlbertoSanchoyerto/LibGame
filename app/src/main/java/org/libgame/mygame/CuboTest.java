package org.libgame.mygame;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import org.libgame.framework.Game;
import org.libgame.framework.Pantalla;
import org.libgame.framework.grafico.Textura;
import org.libgame.framework.gl.Vertices3;
import org.libgame.framework.gl.GLGame;
import org.libgame.framework.gl.GLPantalla;
import org.libgame.framework.gl.*;

/**
 * @class CuboTest
 * @brief la clase CuboTest ejemplo secillo de 3D
 */
public class CuboTest extends GLGame
{

    @Override
    public Pantalla cogePantallaInicial()
	{    
        return new CubePantalla(this);
    }

    class CubePantalla extends GLPantalla
	{
		Vertices3 cubo;
        Textura textura;
        float angulo = 0;

        public CubePantalla(Game game)
		{
            super(game);

            cubo = crearCubo();
            textura = new Textura(glGame, "crate.png");
        }

        private Vertices3 crearCubo()
		{
            float[] vertices = { -0.5f, -0.5f,  0.5f, 0, 1,
				0.5f, -0.5f,  0.5f, 1, 1,
				0.5f,  0.5f,  0.5f, 1, 0,
				-0.5f,  0.5f,  0.5f, 0, 0,

				0.5f, -0.5f,  0.5f, 0, 1,
				0.5f, -0.5f, -0.5f, 1, 1,
				0.5f,  0.5f, -0.5f, 1, 0,
				0.5f,  0.5f,  0.5f, 0, 0,

				0.5f, -0.5f, -0.5f, 0, 1,
				-0.5f, -0.5f, -0.5f, 1, 1,
				-0.5f,  0.5f, -0.5f, 1, 0,
				0.5f,  0.5f, -0.5f, 0, 0,

				-0.5f, -0.5f, -0.5f, 0, 1, 
				-0.5f, -0.5f,  0.5f, 1, 1,
				-0.5f,  0.5f,  0.5f, 1, 0,
				-0.5f,  0.5f, -0.5f, 0, 0,

				-0.5f,  0.5f,  0.5f, 0, 1,
				0.5f,  0.5f,  0.5f, 1, 1,
				0.5f,  0.5f, -0.5f, 1, 0,
				-0.5f,  0.5f, -0.5f, 0, 0,

				-0.5f, -0.5f,  0.5f, 0, 1,
				0.5f, -0.5f,  0.5f, 1, 1,
				0.5f, -0.5f, -0.5f, 1, 0,
				-0.5f, -0.5f, -0.5f, 0, 0
            };             

            short[] indices = { 0, 1, 3, 1, 2, 3,
				4, 5, 7, 5, 6, 7,
				8, 9, 11, 9, 10, 11,
				12, 13, 15, 13, 14, 15,
				16, 17, 19, 17, 18, 19,
				20, 21, 23, 21, 22, 23,
            };

            Vertices3 cubo = new Vertices3(glGraficos, 24, 36, false, true, false);
            cubo.ponVertices(vertices, 0, vertices.length);
            cubo.ponIndices(indices, 0, indices.length);
            return cubo;
        }

        @Override
        public void resumen()
		{
            textura.recarga();
        }

        @Override
        public void actualiza(float deltaTime)
		{
            angulo += 45 * deltaTime;
        }

        @Override
        public void presenta(float deltaTime)
		{
            GL10 gl = glGraficos.cogeGL();
            gl.glViewport(0, 0, glGraficos.cogeAncho(), glGraficos.cogeAlto());
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            GLU.gluPerspective(gl, 67, 
                               glGraficos.cogeAncho() / (float) glGraficos.cogeAlto(),
                               0.1f, 10.0f);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();

            gl.glEnable(GL10.GL_DEPTH_TEST);
            gl.glEnable(GL10.GL_TEXTURE_2D);
            textura.une();
            cubo.une();            
            gl.glTranslatef(0, 0, -3);
            gl.glRotatef(angulo, 0, 1, 0);
            cubo.dibuja(GL10.GL_TRIANGLES, 0, 36);
            cubo.desune();            
            gl.glDisable(GL10.GL_TEXTURE_2D);
            gl.glDisable(GL10.GL_DEPTH_TEST);
        }

        @Override
        public void pausa()
		{
        }

        @Override
        public void libera()
		{
        }        
    }
}
