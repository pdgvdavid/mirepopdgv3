@Suite
Feature: historia request a heroku

  @Caso1
  Scenario: 1-Genera token de conexion (POST)
    Given se prepara url de api
    And se adiciona ruta especifica "AUTH"
    And se adiciona body
    """
    {
    "username" : "admin",
    "password" : "password123"
}
    """
    When se invoca metodo que retorna TOKEN "POST"
    Then se obtiene codigo de respuesta 200

    Scenario: 2-Crea Registro (POST)
      Given se prepara url de api
      And se adiciona ruta especifica "booking"
      And se adiciona body
      """
      {
    "firstname" : "Jamessss",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
      """
      When se invoca metodo que retorna body Booking "POST"
      Then se obtiene codigo de respuesta 200
      And verifica tiempo de respuesta menor a 10000 ms
      
      Scenario: 3-Consulta registro creado (GET)
        Given se prepara url de api
        And se adiciona ruta con identificador "booking"
        When se invoca metodo "GET"
        Then se obtiene codigo de respuesta 200
        And verifica estructura correcta "src/test/resources/esquemas/booking.json"
        And verifica tiempo de respuesta menor a 5000 ms

        Scenario: 4-Modifica el valor creado (PUT)
          Given se prepara url de api
          And se adiciona ruta con identificador "booking"
          And se adiciona body
          """
          {
    "firstname": "xyzwwww",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
          """
          When se invoca metodo "PUT"
          And se obtiene codigo de respuesta 200


          Scenario: 5- Elimina el registro creado (DELETE)
            Given se prepara url de api
            And se adiciona ruta con identificador "booking"
            When se invoca metodo "DELETE"
            And se obtiene codigo de respuesta 201
