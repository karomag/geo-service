package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

public class LocalizationServiceImplTest {

    @Test
    public void localeRuTest() {
        String expectedRu = "Добро пожаловать";
        LocalizationService service = new LocalizationServiceImpl();
        String result = service.locale(Country.RUSSIA);
        Assertions.assertEquals(expectedRu, result);
    }
    @ParameterizedTest
    @MethodSource("getEngCountries")
    public void localeRuTest(Country country) {
        String expectedEng = "Welcome";
        LocalizationService service = new LocalizationServiceImpl();
        String result = service.locale(country);
        Assertions.assertEquals(expectedEng, result);
    }

    public static Stream<Arguments> getEngCountries() {
        return Stream.of(
                Arguments.of(Country.BRAZIL),
                Arguments.of(Country.USA),
                Arguments.of(Country.GERMANY)
        );
    }
}
