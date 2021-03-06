package com.example.projectlimbrescue.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.projectlimbrescue.db.device.Device;
import com.example.projectlimbrescue.db.device.DeviceContainsSensor;
import com.example.projectlimbrescue.db.device.DeviceContainsSensorDao;
import com.example.projectlimbrescue.db.device.DeviceDao;
import com.example.projectlimbrescue.db.reading.Reading;
import com.example.projectlimbrescue.db.reading.ReadingDao;
import com.example.projectlimbrescue.db.sensor.Sensor;
import com.example.projectlimbrescue.db.sensor.SensorDao;
import com.example.projectlimbrescue.db.session.Session;
import com.example.projectlimbrescue.db.session.SessionDao;
import com.example.projectlimbrescue.db.session.SessionMeasuresSensor;
import com.example.projectlimbrescue.db.session.SessionMeasuresSensorDao;
import com.example.projectlimbrescue.db.session.SessionReadsFromDevice;
import com.example.projectlimbrescue.db.session.SessionReadsFromDeviceDao;

@Database(
        entities = {
                Device.class,
                DeviceContainsSensor.class,
                Reading.class,
                Sensor.class,
                Session.class,
                SessionMeasuresSensor.class,
                SessionReadsFromDevice.class
        },
        version = 1,
        exportSchema = false
)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DeviceDao deviceDao();

    public abstract DeviceContainsSensorDao deviceContainsSensorDao();

    public abstract ReadingDao readingDao();

    public abstract SensorDao sensorDao();

    public abstract SessionDao sessionDao();

    public abstract SessionMeasuresSensorDao sessionMeasuresSensorDao();

    public abstract SessionReadsFromDeviceDao sessionReadsFromDeviceDao();
}
