package org.libgame.framework.grafico;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import org.libgame.framework.FicheroIO;
import org.libgame.framework.gl.GLGame;
import org.libgame.framework.gl.GLGrafico;

/**
 * @class Textura
 * @brief clase Textura
 */
public class Textura
{
    GLGraficos glGraficos;
    FicheroIO ficheroIO;
    String nombreFichero;
    int texturaId;
    int minFiltro;
    int magFiltro;   
    public int ancho;
    public int alto;

    public Textura(GLGame glGame, String nombreFichero)
    {
        this.glGraficos = glGame.cogeGLGrafico();
        this.ficheroIO = glGame.cogeFicheroIO();
        this.nombreFichero = nombreFichero;
        carga();
    }

    private void carga()
    {
        GL10 gl = glGraficos.cogeGL();
        int[] texturaIds = new int[1];
        gl.glGenTextures(1, texturaIds, 0);
        texturaId = texturaIds[0];

        InputStream in = null;
        try
        {
            in = ficheroIO.leerAsset(nombreFichero);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaId);
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
            ponFiltros(GL10.GL_NEAREST, GL10.GL_NEAREST);            
            gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
            ancho = bitmap.getWidth();
            alto = bitmap.getHeight();
            bitmap.recycle();
        }
        catch (IOException e)
        {
        throw new RuntimeException("No se puede cargar textura '" + nombreFichero + "'", e);
        }
        finally
        {
            if (in != null)
            {
                try
                { in.close(); }
                catch (IOException e)
                {
                }
            }
        }
    }

    /**
     * @fn recarga
     * @brief realiza una nueva carga de la textura
     */
    public void recarga()
    {
        carga();
        une();
        ponFiltros(minFiltro, magFiltro);        
        glGraficos.cogeGL().glBindTexture(GL10.GL_TEXTURE_2D, 0);
    }

    /**
     * @fn ponFiltros
     * @brief configura los filtros de min y mag a la Textura
     */
    public void ponFiltros(int minFiltro, int magFiltro)
    {
        this.minFiltro = minFiltro;
        this.magFiltro = magFiltro;
        GL10 gl = glGraficos.cogeGL();
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, minFiltro);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, magFiltro);
    }    

    public void une()
    {
        GL10 gl = glGraficos.cogeGL();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaId);
    }

    public void libera()
    {
        GL10 gl = glGraficos.cogeGL();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texturaId);
        int[] textureIds = { texturaId };
        gl.glDeleteTextures(1, textureIds, 0);
    }
}
