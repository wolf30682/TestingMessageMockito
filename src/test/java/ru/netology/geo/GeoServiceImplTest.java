package ru.netology.geo;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.netology.entity.Country;
import ru.netology.entity.Location;


public class GeoServiceImplTest {
    //     Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
    //     Проверить работу метода public Location byIp(String ip)

    @Test
    public void testLocationIsLocal (){
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp(GeoServiceImpl.LOCALHOST);
        Country actualCountry = actualLocation.getCountry();
        Assertions.assertSame(null, actualCountry);
    }

    @Test
    public void testLocationIsRus() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        Country actualCountry = actualLocation.getCountry();
        Country expectedCountry = Country.RUSSIA;
        Assertions.assertEquals(expectedCountry, actualCountry);
    }
    @Test
    public void testLocationIsNotBrazil() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        Country actualCountry = actualLocation.getCountry();
        Country expectedCountry = Country.BRAZIL;
        Assertions.assertNotEquals(expectedCountry, actualCountry);
    }


    @Test
    public void testIpLocationIsStartsWith172() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp("172.566.000.814");
        Country actualCountry = actualLocation.getCountry();
        Country expectedCountry = Country.RUSSIA;
        Assertions.assertEquals(expectedCountry, actualCountry);
    }
    @Test
    public void testIpLocationIsStartsWith96_1() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp("96.004.000.300");
        Country actualCountry = actualLocation.getCountry();
        Country expectedCountry = Country.RUSSIA;
        Assertions.assertNotEquals(expectedCountry, actualCountry);
    }
    @Test
    public void testLocationIsStartsWith96_2() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp("96.004.000.300");
        Country actualCountry = actualLocation.getCountry();
        Country expectedCountry = Country.USA;
        Assertions.assertEquals(expectedCountry, actualCountry);
    }

    @Test
    public void testLocationIsNY() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        Country actualCountry = actualLocation.getCountry();
        Country expectedCountry = Country.USA;
        Assertions.assertEquals(expectedCountry, actualCountry);
    }
    @Test
    public void testLocationIsMoscow() {
        GeoService geoService = new GeoServiceImpl();
        Location actualLocation = geoService.byIp("172.0.32.11");
        String actualCity = actualLocation.getCity();
        String expectedCity = "Moscow";
        Assertions.assertEquals(expectedCity, actualCity);
    }




}

