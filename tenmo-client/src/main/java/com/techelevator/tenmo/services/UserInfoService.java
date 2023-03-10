package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.UserInfoDto;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/**
 * This service is used to request user info from the server.
 *
 * @author Dustin
 */
public class UserInfoService {
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();


    /**
     * Constructs the service, setting the baseUrl for requests.
     *
     * @param baseUrl Base url from the app.
     */
    public UserInfoService(String baseUrl) {
        this.baseUrl = baseUrl + "users";
    }


    /**
     * Request an array of users from the server. This array will contain all users except the user that is currently
     * logged in.
     *
     * @param currentUser Current authenticated user.
     * @return An array of users.
     */
    public UserInfoDto[] getUserInfos(AuthenticatedUser currentUser) {
        UserInfoDto[] history = null;

        try{
            long userId = currentUser.getUser().getId();

            ResponseEntity<UserInfoDto[]> response = restTemplate.exchange(baseUrl + "/listall?userId=" + userId,
                    HttpMethod.GET,
                    makeAuthEntity(currentUser), UserInfoDto[].class);
            history = response.getBody();
        } catch (RestClientResponseException e){
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return history;
    }

    /**
     * Makes an HttpEntity with a  header containing a bearer token.
     *
     * @param currentUser Current authenticated user.
     * @return A new HttpEntity
     */
    private HttpEntity<Void> makeAuthEntity(AuthenticatedUser currentUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}
