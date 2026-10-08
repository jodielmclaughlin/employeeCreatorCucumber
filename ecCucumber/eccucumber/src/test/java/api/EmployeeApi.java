package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import io.cucumber.messages.ndjson.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.messages.ndjson.internal.com.fasterxml.jackson.databind.ObjectMapper;



public class EmployeeApi {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token;

    public void login() throws Exception {

        String loginJson = """
        {
            "email": "admin@test.com",
            "password": "password123"
        }
        """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(loginJson))
                .build();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Failed to login. Status: "
                            + response.statusCode()
                            + " Response: "
                            + response.body()
            );
        }

        token = response.body();
    }

    public Long createTestEmployee() throws Exception {
        if (token == null) {
            login();
        }
        String employeeJson = """
            {
                "firstName": "Delete",
                "lastName": "Test",
                "email": "delete.test@example.com",
                "phoneNumber": "07287598827",
                "address": "1 Test Street",
                "contractType": "FULL_TIME",
                "jobTitle": "Test Employee",
                "startDate": "2025-01-01"
            }
            """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/employees"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(employeeJson))
                .build();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() != 201) {
            throw new RuntimeException(
                    "Failed to create test employee. Status: "
                    + response.statusCode()
                    + " Response: "
                    + response.body()
            );
        }

        JsonNode employee = objectMapper.readTree(response.body());

        return employee.get("id").asLong();
    }

    public Long createTestEditEmployee() throws Exception {
        if (token == null) {
            login();
        }
        String employeeJson = """
            {
                "firstName": "Edit",
                "lastName": "Test",
                "email": "Edit.test@example.com",
                "phoneNumber": "07287598822",
                "address": "1 Test Street",
                "contractType": "FULL_TIME",
                "jobTitle": "Test Edit Employee",
                "startDate": "2025-01-01"
            }
            """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/employees"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(employeeJson))
                .build();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() != 201) {
            throw new RuntimeException(
                    "Failed to create test edit employee. Status: "
                            + response.statusCode()
                            + " Response: "
                            + response.body()
            );
        }

        JsonNode employee = objectMapper.readTree(response.body());

        return employee.get("id").asLong();
    }

    public void deleteEmployee(Long employeeId) throws Exception {
        if (employeeId != null) {


            if (token == null) {
                login();
            }
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/employees/" + employeeId))
                    .header("Authorization", "Bearer " + token)
                    .DELETE()
                    .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() != 204) {
                throw new RuntimeException(
                        "Failed to delete test employee. Status: "
                                + response.statusCode()
                                + " Response: "
                                + response.body()
                );
            }
        }
    }


}