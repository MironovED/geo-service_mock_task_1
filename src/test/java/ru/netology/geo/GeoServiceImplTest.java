package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void searchRussianLocationByIpTest() {
        String ip = "172.0.32.11";

        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(ip);

        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    void searchUSALocationByIpTest() {
        String ip = "96.44.183.149";

        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp(ip);

        assertEquals(Country.USA, location.getCountry());
    }
}