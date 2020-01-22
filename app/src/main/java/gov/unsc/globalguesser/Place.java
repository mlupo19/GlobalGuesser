package gov.unsc.globalguesser;

import com.google.android.gms.maps.model.LatLng;

public class Place {
    private double lat, lon;
    private int id;

    Place (LatLng latLng) {
        this(latLng.latitude, latLng.longitude);
    }

    Place(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    Place(double l, double m, double s, boolean ws, double ll, double mm, double ss, boolean wsws, int id) {
        this(toCoordDouble(l, m, s, ws), toCoordDouble(ll, mm, ss, wsws));
        this.id = id;
    }

    private static double toCoordDouble(double l, double m, double s, boolean ws) {
        return (l + (m / 60.0) + (s / 3600.0)) * (ws ? -1 : 1);
}

    static double dist(Place place1, Place place2) {
        double p1 = Math.toRadians(place1.lat);
        double p2 = Math.toRadians(place2.lat);
        double dp = p2 - p1;
        double dl = Math.toRadians(place2.lon - place1.lon);
        double a = Math.sin(dp/2) * Math.sin(dp/2) + Math.cos(p1) * Math.cos(p2) * Math.sin(dl/2) * Math.sin(dl/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double R = 6371e3;
        return R * c;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getId() {
        return id;
    }
}
