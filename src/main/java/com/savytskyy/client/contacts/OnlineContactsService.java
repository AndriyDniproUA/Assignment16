package com.savytskyy.client.contacts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savytskyy.client.dto.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


@AllArgsConstructor
public class OnlineContactsService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public void add(String name, String type, String value, String token) {

        AddContactRequest addContactRequest = new AddContactRequest(name, type, value);
        try {
            String req = objectMapper.writeValueAsString(addContactRequest);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/contacts/add"))
                    .POST(HttpRequest.BodyPublishers.ofString(req))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();


            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            AddContactResponse addContactResponse = objectMapper.readValue(
                    response.body(),
                    AddContactResponse.class);


            System.out.println("ADDING CONTACT");
            System.out.println(addContactResponse.getStatus());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> getAll(String token) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mag-contacts-api.herokuapp.com/contacts"))
                .GET()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ReadContactsResponse readContactsResponse = objectMapper.readValue(
                    response.body(),
                    ReadContactsResponse.class);

            System.out.println("READING CONTACTS");
            System.out.println(readContactsResponse.getStatus());
            return readContactsResponse.getContacts();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contact> findByName (String name, String token) {
        FindContactByNameRequest findContactByNameRequest = new FindContactByNameRequest(name);

        try {
            String req = objectMapper.writeValueAsString(findContactByNameRequest);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/contacts/find"))
                    .POST(HttpRequest.BodyPublishers.ofString(req))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ReadContactsResponse readContactsResponse = objectMapper.readValue(
                    response.body(),
                    ReadContactsResponse.class);

            System.out.println("Searching contacts ....");
            return readContactsResponse.getContacts();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contact> findByValue (String value, String token) {
        FindContactByValueRequest findContactByValueRequest = new FindContactByValueRequest(value);

        try {
            String req = objectMapper.writeValueAsString(findContactByValueRequest);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/contacts/find"))
                    .POST(HttpRequest.BodyPublishers.ofString(req))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ReadContactsResponse readContactsResponse = objectMapper.readValue(
                    response.body(),
                    ReadContactsResponse.class);

            System.out.println("Searching contacts ....");
            return readContactsResponse.getContacts();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
