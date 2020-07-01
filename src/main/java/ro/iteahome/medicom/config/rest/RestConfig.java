package ro.iteahome.medicom.config.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class RestConfig {

// FIELDS: -------------------------------------------------------------------------------------------------------------

    @Value("${nhs.rest.server-url}")
    private String SERVER_URL;

    @Value("${nhs.rest.doctors-uri}")
    private String DOCTORS_URI;

    @Value("${nhs.rest.credentials}")
    private String CREDENTIALS;

// Methods: ------------------------------------------------------------------------------------------------------------

    public HttpHeaders buildAuthHeaders() {
        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.add(
                "Authorization",
                "Basic " + buildEncodedCredentials());
        return authHeaders;
    }

    public String buildEncodedCredentials() {
        return new String(Base64.getEncoder().encode(CREDENTIALS.getBytes()));
    }

    public String getSERVER_URL() {
        return SERVER_URL;
    }

    public void setSERVER_URL(String SERVER_URL) {
        this.SERVER_URL = SERVER_URL;
    }

    public String getDOCTORS_URI() {
        return DOCTORS_URI;
    }

    public void setDOCTORS_URI(String DOCTORS_URI) {
        this.DOCTORS_URI = DOCTORS_URI;
    }

    public String getCREDENTIALS() {
        return CREDENTIALS;
    }

    public void setCREDENTIALS(String CREDENTIALS) {
        this.CREDENTIALS = CREDENTIALS;
    }
}
