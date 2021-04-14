# Project Limb Rescue

Technical Team:
Michael Montelone,
Nathan Newcomer,
Daniel Simpkins,
Cole Swartz

Sponsors:
Lynne Brophy, MSN, RN-BC, ARPN-CNS, AOCN
Carlo Contreras, MD, FASC

## Project Mission

Gather data on the water content of a limb using non-invasive wearable devices to assist in an early-warning system for patients at risk of lymphedema.

## Application Components

### Database

The application stores reading data in an SQLite database using the [Room persistence library](https://developer.android.com/training/data-storage/room); the associated files can be found in the "db" folder. In total, the database manages four primary tables:

**Device**: A device is a type of smart technology, such as a smartwatch or smart scale, that contains one or more sensors. One ‘device’ entry refers to a type of device, rather than a single particular watch or scale, although it does specify a device placed on a specific limb or combination of limbs.

**Reading**: A reading is a single instance of raw data collected from a type of sensor, on a particular device, about a given limb, during a single session. It numerically represents some output recorded by the sensor.

**Sensor**: A sensor is a device that takes readings of health data. It is situated in devices and may produce readings. One ‘sensor’ entry refers to a type of sensor, rather than a single particular one on a device.

**Session**: A session is a continuous period of data collection begun and ended by user input. It is made up of readings, and may read from multiple sensors and/or multiple devices over the same time period.

There also exist three entities (**DeviceContainsSensor**, **SessionMeasuresSensor** **SessionReadsFromDevice**) that manage the many-to-many relationships between rows in the above tables (for instance, one device may contain many sensor, or one session may read from multiple devices).

Database information from these tables can be retrieved using the methods in their associated DAO (data access object) classes. These methods return Java objects corresponding to the retrieved data, in the form of Guava ListenableFutures (to maintain separation from the UI thread).

### Watch

The `wear` module in the project contains the app that goes on the WearOS watch. Currently only the Fossil Gen 5 is supported, however any watch with a PPG sensor can be supported trivially. The algorithm for `wear` app is as follows:

```
1. Wait for the start signal.
2. Begin reading at 30Hz.
3. Send the stored readings as a JSON object.
4. Repeat to step 1.
```

It should be noted that the PPG sensor uses an analog signal of 0-5V that is then digitialized into a floating point number so all numbers are relative to the sensor.

Additionally, the PPG sensor can be set to any frequency range as specified by the driver. However, it was seen in research that as the frequency of the sensor increased, the accuracy decreased and the DC channel of the sensor dropped out (around 50Hz).
This is a hardware limitation and cannot be solved by software. So, a compromise of 30Hz was made to get both frequency and accuracy.
