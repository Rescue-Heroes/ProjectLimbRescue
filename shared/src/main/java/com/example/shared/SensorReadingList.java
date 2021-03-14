package com.example.shared;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A JSON friendly representation of a grouping of readings from a sensor. Each sensor is identified
 * by the type of data is collects (i.e. "PPG", "Bioimpedance", etc.). JSON strings from sensors
 * have two fields: "desc" being the type of data as a string and "readings" being an array of
 * <code>SensorReading</code>. Reading order is not guaranteed. Timestamps should be used to order
 * them at the client side.
 *
 * <code>
 *     {
 *         "desc": "PPG",
 *         "readings": [
 *             {
 *                 "time": 10000,
 *                 "value": 0.1234
 *             },
 *             ...
 *         ]
 *     }
 * </code>
 */
public class SensorReadingList {
    public List<JSONObject> readings;
    public Sensor desc;

    /**
     * Constructs a JSON object with the sensor type and readings array.
     *
     * @param type Type of sensor data.
     */
    public SensorReadingList(Sensor type) {
        this.readings = new ArrayList<>();
        this.desc = type;
    }

    public SensorReadingList(JSONObject obj) {
        this.readings = new ArrayList<>();
        JSONArray readings = obj.getJSONArray("readings");
        for(int i = 0; i < readings.length(); i++) {
            this.readings.add(readings.getJSONObject(i));
        }
        this.desc = Sensor.valueOf(obj.getString("desc"));
    }

    public void addReading(JSONObject reading) {
        this.readings.add(reading);
    }

    public JSONObject toJson() {
        JSONArray readings = new JSONArray();
        for(int i = 0; i < this.readings.size(); i++) {
            readings.put(this.readings.get(i));
        }

        JSONObject obj = new JSONObject();
        obj.put("readings", readings);
        obj.put("desc", this.desc);
        return obj;
    }
}