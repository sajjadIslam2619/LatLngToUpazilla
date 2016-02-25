/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latlngtoupazilaname;

/**
 *
 * @author sajjad
 */
public class LISdataProperty {
    
    private DateClass date;
    private LatLng latlng;
    private String radiance;
    private String milliseconds;
    private String Events;

    public LISdataProperty() {
    }

    public LISdataProperty(DateClass date, LatLng latlng, String radiance, String milliseconds, String Events) {
        this.date = date;
        this.latlng = latlng;
        this.radiance = radiance;
        this.milliseconds = milliseconds;
        this.Events = Events;
    }

    public DateClass getDate() {
        return date;
    }

    public void setDate(DateClass date) {
        this.date = date;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public String getRadiance() {
        return radiance;
    }

    public void setRadiance(String radiance) {
        this.radiance = radiance;
    }

    public String getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(String milliseconds) {
        this.milliseconds = milliseconds;
    }

    public String getEvents() {
        return Events;
    }
    public void setEvents(String Events) {
        this.Events = Events;
    }
}
