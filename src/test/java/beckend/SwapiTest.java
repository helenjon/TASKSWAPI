package beckend;

import backend.businesobjects.models.StarshipEntity;
import backend.services.controllers.wsapi.WsapiFilmSteps;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SwapiTest {

    private static final String FILM_NAME = "A New Hope";
    private static final String PERSON_NAME = "Biggs Darklighter";
    private WsapiFilmSteps wsapiFilmSteps = new WsapiFilmSteps();

    @Test
    void testTask2() {
        var listPrsonStarships = wsapiFilmSteps.getListStarshipOfPerson(FILM_NAME, PERSON_NAME);
        String expectedPilotName = "Luke Skywalker";
        String expectedStarshipName = "Starfighter";
        assertAll(
                () -> listPrsonStarships.forEach(starShip -> assertEquals(expectedStarshipName, starShip.starship_class, starShip.name + "is not starship class Star fighter")),
                () -> listPrsonStarships.forEach(starship -> checkPilotInTheList(starship, expectedPilotName)));
    }

    private void checkPilotInTheList(StarshipEntity starship, String pilotName) {
        assertTrue(starship.pilots.stream().anyMatch(e -> wsapiFilmSteps.getPersonEntity(e).name.equals(pilotName)),
                "No " + pilotName + " in the starshiplist");
    }
}

