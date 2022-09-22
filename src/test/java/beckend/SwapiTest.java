package beckend;

import backend.businesobjects.models.StarshipEntity;
import backend.services.controllers.wsapi.WsapiFilmSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SwapiTest {

    private static final String FILM_NAME = "A New Hope";
    private static final String PERSON_NAME = "Biggs Darklighter";
    private WsapiFilmSteps wsapiFilmSteps = new WsapiFilmSteps();

    @Test
    @DisplayName("Test Task 2 BE")
    void testTask2() {
        var listPrsonStarships = wsapiFilmSteps.getListStarshipOfPerson(FILM_NAME, PERSON_NAME);
        String expectedPilotName = "Luke Skywalker";
        String expectedStarshipName = "Starfighter";
        assertAll(
                () -> listPrsonStarships.forEach(starShip -> assertEquals(expectedStarshipName, starShip.starship_class, starShip.name + "is not starship class Star fighter")),
                () -> listPrsonStarships .forEach(starship -> checkPilotInTheList(starship, expectedPilotName)));

    }

    @Test
    @DisplayName("Test Task 3 BE - Check contract")
    void testTask3() {
     //   wsapiFilmSteps.testVideoGameSchemaJson("people/1/");

        LocalDate currentdate = LocalDate.now();
        ArrayList<String> test = new ArrayList<>();
        test.add("2018-01-30");
        test.add("2018-09-30");
        test.add("2018-12-30");
        test.add("2018-12-30");
        test.stream().filter(e -> LocalDate.parse(e).getMonth().equals(currentdate.getMonth())).collect(Collectors.toList()).size();


    }


    private void checkPilotInTheList(StarshipEntity starship, String pilotName) {
        assertTrue(starship.pilots.stream().anyMatch(e -> wsapiFilmSteps.getPersonEntity(e).name.equals(pilotName)),
                "No " + pilotName + " in the starshiplist");
    }
}

