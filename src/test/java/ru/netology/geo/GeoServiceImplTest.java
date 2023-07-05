package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    @ParameterizedTest
    @ValueSource(strings = {GeoServiceImpl.LOCALHOST, GeoServiceImpl.MOSCOW_IP, GeoServiceImpl.NEW_YORK_IP, "172.", "96."})
    public void byIpTest(String ip) {
        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);
        Location expected = null;
        if (GeoServiceImpl.LOCALHOST.equals(ip)) {
            expected = new Location(null, null, null, 0);
        } else if (GeoServiceImpl.MOSCOW_IP.equals(ip)) {
            expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        } else if (GeoServiceImpl.NEW_YORK_IP.equals(ip)) {
            expected = new Location("New York", Country.USA, " 10th Avenue", 32);
        } else if (ip.startsWith("172.")) {
            expected = new Location("Moscow", Country.RUSSIA, null, 0);
        } else if (ip.startsWith("96.")) {
            expected = new Location("New York", Country.USA, null, 0);
        }
        Assertions.assertEquals(expected.getCountry(), result.getCountry());
        Assertions.assertEquals(expected.getCity(), result.getCity());
        Assertions.assertEquals(expected.getStreet(), result.getStreet());
        Assertions.assertEquals(expected.getBuiling(), result.getBuiling());
    }

    @ParameterizedTest
    @EmptySource
    public void byIpIsEmptyTest(String ip) {
        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);

        Assertions.assertEquals(null, result);
    }
}
