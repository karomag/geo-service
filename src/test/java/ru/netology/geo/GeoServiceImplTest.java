package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    private static Stream<Arguments> byIpTest() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0))
        );
    }

    @ParameterizedTest
    @MethodSource //When we don't provide a name for the @MethodSource, JUnit will search for a source method with the same name as the test method.
    public void byIpTest(String ip, Location expected) {
        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);

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

        Assertions.assertNull(result);
    }
}
