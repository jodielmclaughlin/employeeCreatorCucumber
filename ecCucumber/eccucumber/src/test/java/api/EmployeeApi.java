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

    public Long createTestEmployee() throws Exception {

        String employeeJson = """
            {
                "firstName": "Delete",
                "lastName": "Test",
                "email": "delete.test@example.com",
                "phoneNumber": "07123456789",
                "address": "1 Test Street",
                "contractType": "FULL_TIME",
                "jobTitle": "Test Employee",
                "startDate": "2025-01-01"
            }
            """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/employees"))
                .header("Content-Type", "application/json")
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
}