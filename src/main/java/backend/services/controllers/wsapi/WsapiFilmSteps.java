package backend.services.controllers.wsapi;

import backend.businesobjects.models.FilmEntity;
import backend.businesobjects.models.FilmsListEntity;
import backend.businesobjects.models.PersonEntity;
import backend.businesobjects.models.StarshipEntity;
import backend.services.exceptions.ExceptionNotUniqueElement;
import backend.utils.ReadPropertyFile;
import backend.utils.helpers.RestParametersHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static backend.services.controllers.wsapi.RestAssuredClient.convertResponse;
import static org.apache.http.HttpStatus.SC_OK;

public class WsapiFilmSteps extends RestAssured {

    private static String propertiesFilePath = "src/test/resources/swapi.properties";
    private static final String SWAPI_API_BASE_URL = ReadPropertyFile.getProperties(propertiesFilePath).getProperty("swapi.api.base.url");
    private static final String SWAPI_API_FILMS_URL = ReadPropertyFile.getProperties(propertiesFilePath).getProperty("swapi.api.films.url");
    private static final String SWAPI_API_PEOPLE_URL = ReadPropertyFile.getProperties(propertiesFilePath).getProperty("swapi.api.people.url");



    private FilmsListEntity getFilmsListEntity() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(SWAPI_API_BASE_URL + SWAPI_API_FILMS_URL)
                .then()
                .statusCode(SC_OK)
                .extract().response();
        return convertResponse(response, FilmsListEntity.class);
    }

    public PersonEntity getPersonEntity(String personUrl) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(personUrl)
                .then()
                .statusCode(SC_OK)
                .extract().response();
        return convertResponse(response, PersonEntity.class);
    }

    public StarshipEntity getStarshipEntity(String starshipApiUrl) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(starshipApiUrl)
                .then()
                .statusCode(SC_OK)
                .extract().response();
        return convertResponse(response, StarshipEntity.class);
    }


    public List<String> getCharactersUrlListForFilm(String filmName) {
        return new ArrayList<>(getFilmEntityByFilmName(filmName).getCharacters());
    }

    public List<PersonEntity> getlistOfCharacters(String filmname) {
        return getCharactersUrlListForFilm(filmname)
                .stream().map(this::getPersonEntity)
                .collect(Collectors.toList());
    }

    public PersonEntity getPersonWithName(String filmName, String personName) {

        return getlistOfCharacters(filmName).stream().filter(e -> e.name.equals(personName)).collect(Collectors.toList()).get(0);
    }

    public List<StarshipEntity> getListStarshipOfPerson(String filmName, String personName) {
        return getPersonWithName(filmName, personName).starships.stream().map(this::getStarshipEntity).collect(Collectors.toList());
    }

    public List<FilmEntity> getFilmsList(){
        return new ArrayList<>(getFilmsListEntity().getResults());
    }

    @SneakyThrows
    private FilmEntity getFilmEntityByFilmName(String filmName){
        List<FilmEntity> filmsList  = getFilmsList();
        if (filmsList.stream().filter(e -> e.title.equals(filmName)).count()>1)
             throw new ExceptionNotUniqueElement(String.format("More then 1 film with name %s was found", filmName ));
        else  return filmsList.stream().filter(e -> e.title.equals(filmName)).collect(Collectors.toList()).get(0);
    }
}
