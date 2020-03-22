package org.libgame.framework.gl;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.opengl.GLUtils;

import org.libgame.framework.FicheroIO;
import org.libgame.framework.gl.GLGame;
import org.libgame.framework.gl.GLGraficos;

/**
 * @class TexturaMipMapped
 * @brief clase TexturaMipMapped
 */
public class TexturaMipMapped
{
    GLGraficos glGraficos;
    FicheroIO ficheroIO;
    String nombreFichero;
    int idTextura;
    int filtroMin;
    int filtroMag;   
    public int ancho;
    public int alto;
    boolean mapeado;
    
    public TexturaMipMapped(GLGame glGame, String nombreFichero, boolean mapeado)
	{
        this.glGraficos = glGame.cogeGLGraficos();
        this.ficheroIO = glGame.cogeFicheroIO();
        this.nombreFichero = nombreFichero;
        this.mapeado = mapeado;
        cargar();
    }
    
    private void cargar()
	{
        GL10 gl = glGraficos.cogeGL();
        int[] idsTextura = new int[1];
        gl.glGenTextures(1, idsTextura, 0);
        idTextura = idsTextura[0];
        
        InputStream in = null;
        try 
		{
            in = ficheroIO.leerAsset(nombreFichero);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            if(mapeado)
			{
                crearMipmaps(gl, bitmap);
            } else
			{
                gl.glBindTexture(GL10.GL_TEXTURE_2D, idTextura);
                GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
                ponFiltros(GL10.GL_NEAREST, GL10.GL_NEAREST);            
                gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
                ancho = bitmap.getWidth();
                alto = bitmap.getHeight();
                bitmap.recycle();
            }
        } catch(IOException e)
		{
            throw new RuntimeException("No puedo cargar '" + nombreFichero +"'", e);
        } finally
		{
            if(in != null)
                try { in.close(); } catch (IOException e) { }
        }
    }
    
    private void crearMipmaps(GL10 gl, Bitmap bitmap)
	{
        gl.glBindTexture(GL10.GL_TEXTURE_2D, idTextura);
        ancho = bitmap.getWidth();
        alto = bitmap.getHeight();        
        ponFiltros(GL10.GL_LINEAR_MIPMAP_NEAREST, GL10.GL_LINEAR);
        
        int nivel = 0;
        int nuevoAncho = ancho;
        int nuevoAlto = alto;
        while(true)
		{
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, nivel, bitmap, 0);
            nuevoAncho = nuevoAncho / 2;
            nuevoAlto = nuevoAlto / 2;
            if(nuevoAncho <= 0)
                break;
            Bitmap newBitmap = Bitmap.createBitmap(nuevoAncho, nuevoAlto, bitmap.getConfig());
            Canvas canvas = new Canvas(newBitmap);
            canvas.drawBitmap(bitmap, 
                              new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                              new Rect(0, 0, nuevoAncho, nuevoAlto),
                              null);            
            bitmap.recycle();
            bitmap = newBitmap;
            nivel++;
        }
        
        gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);        
        bitmap.recycle();
    }
    
    public void recargar()
	{
        cargar();
        une();
        ponFiltros(filtroMin, filtroMag);        
        glGraficos.cogeGL().glBindTexture(GL10.GL_TEXTURE_2D, 0);
    }
    
    public void ponFiltros(int filtroMin, int filtroMag)
	{
        this.filtroMin = filtroMin;
        this.filtroMag = filtroMag;
        GL10 gl = glGraficos.cogeGL();
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, filtroMin);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, filtroMag);
    }    
    
    public void une()
	{
        GL10 gl = glGraficos.cogeGL();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, idTextura);
    }
    
    public void desune()
	{
        GL10 gl = glGraficos.cogeGL();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, idTextura);
        int[] idsTexturas = { idTextura };
        gl.glDeleteTextures(1, idsTexturas, 0);
    }
}
