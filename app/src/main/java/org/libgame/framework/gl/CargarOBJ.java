package org.libgame.framework.gl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.libgame.framework.gl.GLGame;

/**
 * @class CargarOBJ
 * @brief clase CargarOBJ
 */
public class CargarOBJ
{
    /**
     * @fn leer
     * @brief lee un fichero OBJ (objeto 3D) de assets
     * @param GLGame game
     * @param String nombreFichero
     */
    public static Vertices3 leer(GLGame game, String nombreFichero)
    {
        InputStream in = null;
        try
        {
            in = game.cogeFicheroIO().leerAsset(nombreFichero);
            List<String> lineas = leerlineaas(in);

            float[] vertices = new float[lineas.size() * 3];
            float[] normales = new float[lineas.size() * 3];
            float[] uv = new float[lineas.size() * 2];

            int numVertices = 0;
            int numNormales = 0;
            int numUV = 0;
            int numCaras = 0;

            int[] carasVerts = new int[lineas.size() * 3];
            int[] carasNormales = new int[lineas.size() * 3];
            int[] carasUV = new int[lineas.size() * 3];
            int IndexVertex = 0;
            int IndexNormales = 0;
            int indexUV = 0;
            int indexCara = 0;

            for (int i = 0; i < lineas.size(); i++)
            {
                String linea = lineas.get(i);
                if (linea.startsWith("v "))
                {
                    String[] tokens = linea.split("[ ]+");
                    vertices[IndexVertex] = Float.parseFloat(tokens[1]);
                    vertices[IndexVertex + 1] = Float.parseFloat(tokens[2]);
                    vertices[IndexVertex + 2] = Float.parseFloat(tokens[3]);
                    IndexVertex += 3;
                    numVertices++;
                    continue;
                }

                if (linea.startsWith("vn "))
                {
                    String[] tokens = linea.split("[ ]+");
                    normales[IndexNormales] = Float.parseFloat(tokens[1]);
                    normales[IndexNormales + 1] = Float.parseFloat(tokens[2]);
                    normales[IndexNormales + 2] = Float.parseFloat(tokens[3]);
                    IndexNormales += 3;
                    numNormales++;
                    continue;
                }

                if (linea.startsWith("vt"))
                {
                    String[] tokens = linea.split("[ ]+");
                    uv[indexUV] = Float.parseFloat(tokens[1]);
                    uv[indexUV + 1] = Float.parseFloat(tokens[2]);
                    indexUV += 2;
                    numUV++;
                    continue;
                }

                if (linea.startsWith("f "))
                {
                    String[] tokens = linea.split("[ ]+");

                    String[] partes = tokens[1].split("/");
                    carasVerts[indexCara] = cogeIndex(partes[0], numVertices);
                    if (partes.length > 2)
                        carasNormales[indexCara] = cogeIndex(partes[2], numNormales);
                    if (partes.length > 1)
                        carasUV[indexCara] = cogeIndex(partes[1], numUV);
                    indexCara++;

                    partes = tokens[2].split("/");
                    carasVerts[indexCara] = cogeIndex(partes[0], numVertices);
                    if (partes.length > 2)
                        carasNormales[indexCara] = cogeIndex(partes[2], numNormales);
                    if (partes.length > 1)
                        carasUV[indexCara] = cogeIndex(partes[1], numUV);
                    indexCara++;

                    partes = tokens[3].split("/");
                    carasVerts[indexCara] = cogeIndex(partes[0], numVertices);
                    if (partes.length > 2)
                        carasNormales[indexCara] = cogeIndex(partes[2], numNormales);
                    if (partes.length > 1)
                        carasUV[indexCara] = cogeIndex(partes[1], numUV);
                    indexCara++;
                    numCaras++;
                    continue;
                }
            }

            float[] verts = new float[(numCaras * 3)
                * (3 + (numNormales > 0 ? 3 : 0) + (numUV > 0 ? 2 : 0))];

            for (int i = 0, vi = 0; i < numCaras * 3; i++)
            {
                int idxVertex = carasVerts[i] * 3;
                verts[vi++] = vertices[idxVertex];
                verts[vi++] = vertices[idxVertex + 1];
                verts[vi++] = vertices[idxVertex + 2];

                if (numUV > 0)
                {
                    int uvIdx = carasUV[i] * 2;
                    verts[vi++] = uv[uvIdx];
                    verts[vi++] = 1 - uv[uvIdx + 1];
                }

                if (numNormales > 0)
                {
                    int normalIdx = carasNormales[i] * 3;
                    verts[vi++] = normales[normalIdx];
                    verts[vi++] = normales[normalIdx + 1];
                    verts[vi++] = normales[normalIdx + 2];
                }
            }

            Vertices3 modelo = new Vertices3(game.cogeGLGraficos(), numCaras * 3,
                                             0, false, numUV > 0, numNormales > 0);
            modelo.ponVertices(verts, 0, verts.length);
            return modelo;
        }
        catch (Exception ex)
        {
            throw new RuntimeException("no puedo cargar '" + nombreFichero + "'", ex);
        }
        finally
        {
            if (in != null)
                try
                {
                    in.close();
                }
                catch (Exception ex)
                {

                }
        }
    }

    static int cogeIndex(String index, int tam)
    {
        int idx = Integer.parseInt(index);
        if (idx < 0)
            return tam + idx;
        else
            return idx - 1;
    }

    static List<String> leerlineaas(InputStream in) throws IOException
    {
        List<String> lineas = new ArrayList<String>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String linea = null;
        while ((linea = reader.readLine()) != null)
            lineas.add(linea);
        return lineas;
    }
}
