package com.ifountain.client.statussiren;

import com.ifountain.client.ClientException;
import com.ifountain.client.http.HttpClient;
import com.ifountain.client.statussiren.model.incident.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Tuba Ozturk
 * @version 25.4.2014 08:46
 */
public class StatusSirenClient implements IStatusSirenClient {
    HttpClient httpClient;

    public StatusSirenClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public CreateIncidentResponse createIncident(CreateIncidentRequest createIncidentRequest) throws IOException, ClientException, ParseException {
        return null;
    }

    @Override
    public ResolveIncidentResponse resolveIncident(ResolveIncidentRequest resolveIncidentRequest) {
        return null;
    }

    @Override
    public DeleteIncidentResponse deleteIncident(DeleteIncidentRequest deleteIncidentRequest) {
        return null;
    }

    @Override
    public UpdateIncidentResponse updateIncident(UpdateIncidentRequest updateIncidentRequest) {
        return null;
    }

    @Override
    public GetIncidentResponse getIncident(GetIncidentRequest getIncidentRequest) {
        return null;
    }

    @Override
    public ListIncidentsResponse listIncidents(ListIncidentsRequest listIncidentsRequest) {
        return null;
    }
}
