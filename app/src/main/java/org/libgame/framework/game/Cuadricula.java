package org.libgame.framework.game;

import java.util.ArrayList;
import java.util.List;

import org.libgame.framework.game.ObjetoGame;

import android.util.FloatMath;

public class Cuadricula
{
    List<ObjetoGame>[] dynamicCells;
    List<ObjetoGame>[] staticCells;
    int cellsPerRow;
    int cellsPerCol;
    float cellSize;
    int[] cellIds = new int[4];
    List<ObjetoGame> foundObjects;

    @SuppressWarnings("unchecked")
    public Cuadricula(float worldWidth, float worldHeight, float cellSize)
	{
        this.cellSize = cellSize;
        this.cellsPerRow = (int)Math.ceil(worldWidth / cellSize);
        this.cellsPerCol = (int)Math.ceil(worldHeight / cellSize);
        int numCells = cellsPerRow * cellsPerCol;
        dynamicCells = new List[numCells];
        staticCells = new List[numCells];
        for (int i = 0; i < numCells; i++)
		{
            dynamicCells[i] = new ArrayList<ObjetoGame>(10);
            staticCells[i] = new ArrayList<ObjetoGame>(10);
        }
        foundObjects = new ArrayList<ObjetoGame>(10);
    }

    public void insertStaticObject(ObjetoGame obj)
	{
        int[] cellIds = getCellIds(obj);
        int i = 0;
        int cellId = -1;
        while (i <= 3 && (cellId = cellIds[i++]) != -1)
		{
            staticCells[cellId].add(obj);
        }
    }

    public void insertDynamicObject(ObjetoGame obj)
	{
        int[] cellIds = getCellIds(obj);
        int i = 0;
        int cellId = -1;
        while (i <= 3 && (cellId = cellIds[i++]) != -1)
		{
            dynamicCells[cellId].add(obj);
        }
    }

    public void removeObject(ObjetoGame obj)
	{
        int[] cellIds = getCellIds(obj);
        int i = 0;
        int cellId = -1;
        while (i <= 3 && (cellId = cellIds[i++]) != -1)
		{
            dynamicCells[cellId].remove(obj);
            staticCells[cellId].remove(obj);
        }
    }

    public void clearDynamicCells(ObjetoGame obj)
	{
        int len = dynamicCells.length;
        for (int i = 0; i < len; i++)
		{
            dynamicCells[i].clear();
        }
    }

    public List<ObjetoGame> getPotentialColliders(ObjetoGame obj)
	{
        foundObjects.clear();
        int[] cellIds = getCellIds(obj);
        int i = 0;
        int cellId = -1;
        while (i <= 3 && (cellId = cellIds[i++]) != -1)
		{
            int len = dynamicCells[cellId].size();
            for (int j = 0; j < len; j++)
			{
                ObjetoGame collider = dynamicCells[cellId].get(j);
                if (!foundObjects.contains(collider))
                    foundObjects.add(collider);
            }

            len = staticCells[cellId].size();
            for (int j = 0; j < len; j++)
			{
                ObjetoGame collider = staticCells[cellId].get(j);
                if (!foundObjects.contains(collider))
                    foundObjects.add(collider);
            }
        }
        return foundObjects;
    }

    public int[] getCellIds(ObjetoGame obj)
	{

        int x1 = (int)Math.floor(obj.bounds.min.x / cellSize);
        int y1 = (int)Math.floor(obj.bounds.min.y / cellSize);
        int x2 = (int)Math.floor((obj.bounds.min.x + obj.bounds.ancho) / cellSize);
        int y2 = (int)Math.floor((obj.bounds.min.y + obj.bounds.alto) / cellSize);

        if (x1 == x2 && y1 == y2)
		{
            if (x1 >= 0 && x1 < cellsPerRow && y1 >= 0 && y1 < cellsPerCol)
                cellIds[0] = x1 + y1 * cellsPerRow;
            else
                cellIds[0] = -1;
            cellIds[1] = -1;
            cellIds[2] = -1;
            cellIds[3] = -1;
        }
        else if (x1 == x2)
		{
            int i = 0;
            if (x1 >= 0 && x1 < cellsPerRow)
			{
                if (y1 >= 0 && y1 < cellsPerCol)
                    cellIds[i++] = x1 + y1 * cellsPerRow;
                if (y2 >= 0 && y2 < cellsPerCol)
                    cellIds[i++] = x1 + y2 * cellsPerRow;
            }
            while (i <= 3) cellIds[i++] = -1;
        }
        else if (y1 == y2)
		{
            int i = 0;
            if (y1 >= 0 && y1 < cellsPerCol)
			{
                if (x1 >= 0 && x1 < cellsPerRow)
                    cellIds[i++] = x1 + y1 * cellsPerRow;
                if (x2 >= 0 && x2 < cellsPerRow)
                    cellIds[i++] = x2 + y1 * cellsPerRow;
            }
            while (i <= 3) cellIds[i++] = -1;                       
        }
        else
		{
            int i = 0;
            int y1CellsPerRow = y1 * cellsPerRow;
            int y2CellsPerRow = y2 * cellsPerRow;
            if (x1 >= 0 && x1 < cellsPerRow && y1 >= 0 && y1 < cellsPerCol)
                cellIds[i++] = x1 + y1CellsPerRow;
            if (x2 >= 0 && x2 < cellsPerRow && y1 >= 0 && y1 < cellsPerCol)
                cellIds[i++] = x2 + y1CellsPerRow;
            if (x2 >= 0 && x2 < cellsPerRow && y2 >= 0 && y2 < cellsPerCol)
                cellIds[i++] = x2 + y2CellsPerRow;
            if (x1 >= 0 && x1 < cellsPerRow && y2 >= 0 && y2 < cellsPerCol)
                cellIds[i++] = x1 + y2CellsPerRow;
            while (i <= 3) cellIds[i++] = -1;
        }
        return cellIds;
    }
}
