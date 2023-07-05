package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @Test
    public void sendRuTest() {
        String expected = "Добро пожаловать";
        Location Russia = new Location(null, Country.RUSSIA, null, 0);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(Russia);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Mockito.any(Country.class)))
                .thenReturn(expected);

        MessageSender sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", "172.0.0.0");
        String result = sender.send(headers);

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void sendEngTest() {
        String expected = "Welcome";
        Location USA = new Location(null, Country.USA, null, 0);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(USA);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Mockito.any(Country.class)))
                .thenReturn(expected);

        MessageSender sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", "96.0.0.0");
        String result = sender.send(headers);

        Assertions.assertEquals(expected, result);
    }
}
