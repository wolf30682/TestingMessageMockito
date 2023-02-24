package ru.netology.sender;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Matchers.any;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

import java.util.HashMap;
import java.util.Map;

//    Написать тесты для проверки языка отправляемого сообщения (класс MessageSender) с использованием Mockito
//    1. Проверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому
//    сегменту адресов.
//    2. Проверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к
//            американскому сегменту адресов.


public class MessageSenderImpTest {
    @Test
    public void testNotSendEnglishTextWhenIpRussian() {

        // сделаем mock на ГеоСервис, он как будто внешний
        GeoService geoService = Mockito.mock(GeoService.class);
        // Определим локацию России
        Location russianLocation = new Location("Moscow", Country.RUSSIA, null, 0);
        // Далее всегда возвращаем Россию
        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(russianLocation);

        //объект, который принимает страну и отправляет в ответ только сообщение для росийского сегмента адресов
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        // Теперь наш отправщик сообщений
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        // Создаём мапу, в которую закинем Ip
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, null);

        String actual = messageSender.send(headers);

        String expected = "Welcome";
        // Проверка что это не так
        Assertions.assertNotEquals(actual, expected);
    }

    @Test
    public void testSendRussianTextWhenIpRussian() {

        // сделаем mock на ГеоСервис, он как будто внешний
        GeoService geoService = Mockito.mock(GeoService.class);
        // Определим локацию России
        Location russianLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        // Далее всегда возвращаем Россию
        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(russianLocation);

        //объект, который принимает страну и отправляет в ответ только сообщение для росийского сегмента адресов
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        // Теперь наш отправщик сообщений
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        // Создаём мапу, в которую закинем Ip
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, null);

        String actual = messageSender.send(headers);
        String expected = "Добро пожаловать";
        // String expected = "Welcome";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testSendEnglishTextWhenIpEnglish() {

        // сделаем mock на ГеоСервис, он как будто внешний
        GeoService geoService = Mockito.mock(GeoService.class);
        // Определим локацию Англоязычного сегмента
        Location englishLocation = new Location("New York", Country.USA, null, 0);
        // Далее всегда возвращаем Англоязычную локацию
        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(englishLocation);

        //объект, который принимает страну и отправляет в ответ только сообщение для англоязычного сегмиента адресов
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        // Теперь наш отправщик сообщений
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        // Создаём мапу, в которую закинем Ip
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, null);

        String actual = messageSender.send(headers);
        String expected = "Welcome";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testSendNotRussianTextWhenIpEnglish() {

        // сделаем mock на ГеоСервис, он как будто внешний
        GeoService geoService = Mockito.mock(GeoService.class);
        // Определим локацию Англоязычного сегмента
        Location englishLocation = new Location("New York", Country.USA, " 10th Avenue", 32);
        // Далее всегда возвращаем Англоязычную локацию
        Mockito.when(geoService.byIp(Mockito.anyString()))
                .thenReturn(englishLocation);

        //объект, который принимает страну и отправляет в ответ только сообщение для англоязычного сегмиента адресов
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        // Теперь наш отправщик сообщений
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        // Создаём мапу, в которую закинем Ip
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, null);

        String actual = messageSender.send(headers);
        String expected = "Добро пожаловать";
        Assertions.assertNotEquals(actual, expected);
    }


}

