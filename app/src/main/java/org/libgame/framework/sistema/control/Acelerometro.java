package org.libgame.framework.sistema.control;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Acelerometro implements SensorEventListener
{

    float accelX;
    float accelY;
    float accelZ;

    public Acelerometro(Context context)
    {

	SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
	if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0)
	{

	    Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
	    manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
	}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int precision)
    {
    }

    @Override
    public void onSensorChanged(SensorEvent evento)
    {

	accelX = evento.values[0];
	accelY = evento.values[1];
	accelZ = evento.values[2];
    }

    public float cogeAccelX()
    {

	return accelX;
    }

    public float cogeAccelY()
    {

	return accelY;
    }

    public float cogeAccelZ()
    {

	return accelZ;
    }
}
