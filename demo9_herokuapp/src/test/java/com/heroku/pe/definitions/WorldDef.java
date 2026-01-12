package com.heroku.pe.definitions;

import com.heroku.pe.models.BookingResponse;
import com.heroku.pe.models.Token;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.lessThan;

public class WorldDef {

    RequestSpecification request;
    Response response;
    static int id1;
    static String token1;

    public RequestSpecification construyeRequest()
    {
        return new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .setContentType(ContentType.JSON)
                .addHeader("Cookie", "token=" + token1)
                .build();

    }


    @Given("se prepara url de api")
    public void preparaUrl() {
        request = RestAssured.given().spec(construyeRequest());

    }

    @And("se adiciona ruta especifica {string}")
    public void adicionaRuta(String ruta) {
        request.basePath(ruta);
    }

    @And("se adiciona ruta con identificador {string}")
    public void adicionaRutaIdentificador(String ruta) {
        request.basePath(ruta+"/"+id1);
    }

    @And("se adiciona body")
    public void adicionaBody(String body) {
        request.body(body);
    }

    @When("se invoca metodo que retorna TOKEN {string}")
    public void invocaMetodoRetornaToken(String metodo) {
        request.log().all();
        response = request.request(metodo);
        //response = request.post();
        token1 = response.as( Token.class).getToken();
        response.then().log().all();
    }

    @When("se invoca metodo {string}")
    public void invocaMetodo(String metodo) {
        //System.out.println("request enviado");
        request.log().all();
        response = request.request(metodo);
        //System.out.println("response recibido");
        response.then().log().all();
    }

    @When("se invoca metodo que retorna body Booking {string}")
    public void invocaMetodoRetornaBody(String metodo) {
        request.log().all();
        response = request.request(metodo);
        id1 = response.as(BookingResponse.class).getBookingid();
        response.then().log().all();
    }

    @Then("se obtiene codigo de respuesta {int}")
    public void obtieneCodigoRespuesta(int codigo) {
        response.then().statusCode(codigo);
    }


    @And("verifica estructura correcta {string}")
    public void verificaEstructuraCorrecta(String estructuraEsperada) {
        response.then().body(JsonSchemaValidator.matchesJsonSchema(new File(estructuraEsperada)));
    }

    @And("verifica tiempo de respuesta menor a {long} ms")
    public void verificaTiempo(Long tiempoEsperado) {

        response.then().time(lessThan(tiempoEsperado));

    }
}
