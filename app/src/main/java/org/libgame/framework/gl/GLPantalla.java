package org.libgame.framework.gl;

import org.libgame.framework.Game;
import org.libgame.framework.Pantalla;

/**
 * @class GLPantalla
 * @brief clase GLPantalla
 */
public abstract class GLPantalla extends Pantalla
{
    protected final GLGraficos glGraficos;
    protected final GLGame glGame;

    public GLPantalla(Game game)
    {
        super(game);
        glGame = (GLGame)game;
        glGraficos = ((GLGame)game).cogeGLGraficos();
    }
}
