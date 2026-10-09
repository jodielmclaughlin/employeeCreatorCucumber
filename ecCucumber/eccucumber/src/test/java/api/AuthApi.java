package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthApi {

    private final HttpClient client = HttpClient.newHttpClient();
    private final String baseApiUrl = "http://localhost:8080";

    public String login(String email, String password) throws Exception {
        String body = """
            {
                "email": "%s",
                "password": "%s"
            }
            """.formatted(email, password);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseApiUrl + "/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Test login failed with status: " + response.statusCode()
            );
        }

        return response.body();
    }
}