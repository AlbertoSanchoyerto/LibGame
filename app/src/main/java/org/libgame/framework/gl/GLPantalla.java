package org.libgame.framework.gl;

import org.libgame.framework.Game;
import org.libgame.framework.Pantalla;

public abstract class GLPantalla extends Pantalla
{

    protected final GLGrafico glGraficos;
    protected final GLGame glGame;

    /**
     * Crea una pantalla
     */
    public GLPantalla(Game game)
    {

		super(game);
		glGame = (GLGame)game;
		glGraficos = ((GLGame)game).cogeGLGrafico();
    }

}
