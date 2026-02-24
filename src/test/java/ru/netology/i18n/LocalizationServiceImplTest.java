package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

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