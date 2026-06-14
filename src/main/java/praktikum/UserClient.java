package praktikum;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserClient {
    private String accessToken;
    private String email;
    private String password;
    private String name;

    public UserClient(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Конструктор без имени (для логина)
    public UserClient(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Step("Создание пользователя")
    public ValidatableResponse create() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);
        credentials.put("name", name);

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(Constants.BASE_URL)
                .body(credentials)
                .when()
                .post("/api/auth/register")
                .then().log().all();
    }

    @Step("Авторизация и получения токена пользователя")
    public String getAccessToken() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(Constants.BASE_URL)
                .body(credentials)
                .when()
                .post("/api/auth/login");

        if (response.statusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
            return accessToken;
        }
        return null;
    }

    @Step("Удаление пользователя")
    public boolean deleteUser() {
        if (accessToken == null) {
            getAccessToken(); // Получаем токен, если его нет
        }

        if (accessToken != null && !accessToken.isEmpty()) {
            Response response = given().log().all()
                    .header("Authorization", accessToken)
                    .baseUri(Constants.BASE_URL)
                    .when()
                    .delete("/api/auth/user");

            return response.statusCode() == 202;
        }
        return false;
    }
}