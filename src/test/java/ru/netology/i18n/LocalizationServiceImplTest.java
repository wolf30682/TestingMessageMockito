package ru.netology.i18n;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.netology.entity.Country;

import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTest {

    //     Написать тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)
    //     Проверить работу метода public String locale(Country country)

    @Test
    public void testReturnTextRussian() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Country country = RUSSIA;
        String actual = localizationService.locale(country);
        String exspected = "Добро пожаловать";
        Assertions.assertEquals(exspected, actual);

    }

    @Test
    public void testReturnTextEnglish() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Country[] countries = {USA, GERMANY, BRAZIL};
        for (Country country: countries) {
            String actual = localizationService.locale(country);
            String exspected = "Welcome";
            Assertions.assertEquals(exspected, actual);

        }

    }


}
