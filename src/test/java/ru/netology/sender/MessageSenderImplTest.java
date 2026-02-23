package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {


    @Test
    void sendMessageTestWhenIp172_Ru() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.345.0.1"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.345.0.1");
        String message = messageSender.send(headers);

        assertEquals("Добро пожаловать", message);
    }


    @Test
    void sendMessageTestWhenIp96_USA() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("96.38.5.1"))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.38.5.1");
        String message = messageSender.send(headers);

        assertEquals("Welcome", message);
    }


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


    @Test
    void shouldReturnRussianTextTest() {
        Country country = Country.RUSSIA;

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String text = localizationService.locale(country);

        assertEquals("Добро пожаловать", text);
    }

    @Test
    void shouldReturnAmericanTextTest() {
        Country country = Country.USA;

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String text = localizationService.locale(country);

        assertEquals("Welcome", text);
    }
}