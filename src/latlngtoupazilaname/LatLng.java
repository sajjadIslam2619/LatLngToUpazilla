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
public class LatLng {
    private Float Lat;
    private Float Lng;

    public LatLng() {
    }

    public LatLng(Float Lat, Float Lng) {
        this.Lat = Lat;
        this.Lng = Lng;
    }

    public Float getLat() {
        return Lat;
    }

    public void setLat(Float Lat) {
        this.Lat = Lat;
    }

    public Float getLng() {
        return Lng;
    }

    public void setLng(Float Lng) {
        this.Lng = Lng;
    }
}
