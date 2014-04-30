package com.ifountain.client.statussiren;

import com.ifountain.client.ClientException;
import com.ifountain.client.statussiren.model.incident.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface for accessing StatusSiren
 * @author Tuba Ozturk
 * @version 25.4.2014 08:45
 * @see StatusSirenClient
 */
public interface IStatusSirenClient {
    public CreateIncidentResponse createIncident(CreateIncidentRequest createIncidentRequest)throws IOException, ClientException, ParseException;
    public ResolveIncidentResponse resolveIncident(ResolveIncidentRequest resolveIncidentRequest);
    public DeleteIncidentResponse deleteIncident(DeleteIncidentRequest deleteIncidentRequest);
    public UpdateIncidentResponse updateIncident(UpdateIncidentRequest updateIncidentRequest);
    public GetIncidentResponse getIncident(GetIncidentRequest getIncidentRequest);
    public ListIncidentsResponse listIncidents(ListIncidentsRequest listIncidentsRequest);
}
