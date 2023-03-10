package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.TransferStatus;
import com.techelevator.util.BasicLogger;
import org.apiguardian.api.API;
import org.openqa.selenium.remote.Response;
import org.springframework.http.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/**
 * This class represents a service that is used to request information about transfer statuses.
 * It contains the following objects:
 *      - A baseUrl to send requests to.
 *      - A restTemplate to make the requests.
 *      - A currentUser that is used to authenticate the requests.
 *
 * @author Dustin
 */
public class TransferStatusService {
    public final String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;


    /**
     * Constructs a new transfer status service with a baseUrl specific to this service.
     *
     * @param baseUrl The app's base url.
     */
    public TransferStatusService(String baseUrl) {
        this.baseUrl = baseUrl + "transfers/status";
    }

    /**
     * Setter used to set the current user of the app. Allows us to make an authentication header.
     *
     * @param currentUser The current authenticated user.
     */
    public void setCurrentUser(AuthenticatedUser currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * This method was used for testing purposes only. It is intended to return an array of all statuses in the
     * database. It is not implemented in the app.
     *
     * @return An array of all possible statuses.
     */
    public TransferStatus[] getAllStatuses() {
        TransferStatus[] statuses = null;
        try {
            ResponseEntity<TransferStatus[]> response =
                    restTemplate.exchange(baseUrl, HttpMethod.GET, makeAuthEntity(currentUser), TransferStatus[].class);
            statuses = response.getBody();
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getMessage());
        }
        return statuses;
    }

    /**
     * Finds a transfer status by its id. Used in getting a status description when printing a single transfer's details.
     *
     * @param id The status id.
     * @return The status, if found.
     */
    public TransferStatus getStatusById(long id) {
        TransferStatus status = null;
        try {
            ResponseEntity<TransferStatus> response =
                    restTemplate.exchange((baseUrl + "?id=" + id), HttpMethod.GET, makeAuthEntity(currentUser),
                            TransferStatus.class);
            status = response.getBody();
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getMessage());
        }
        return status;
    }

    /**
     * Finds a transfer's status by the status' name. Used in creating a transfer in order to get the status id for a
     * status of "Approved".
     *
     * @param name Name of the status.
     * @return The status of the transfer.
     */
    public TransferStatus getStatusByName(String name) {
        TransferStatus status = null;
        try {
            ResponseEntity<TransferStatus> response =
                    restTemplate.exchange((baseUrl + "?name=" + name), HttpMethod.GET, makeAuthEntity(currentUser),
                            TransferStatus.class);
            status = response.getBody();
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getMessage());
        }
        return status;
    }

    /**
     * Creates an HttpEntity with a bearer token belonging to the current user.
     *
     * @param currentUser An AuthenticatedUser that is currently logged into the app.
     * @return A new HttpEntity.
     */
    private HttpEntity<Void> makeAuthEntity(AuthenticatedUser currentUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}
