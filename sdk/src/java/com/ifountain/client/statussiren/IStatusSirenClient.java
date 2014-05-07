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
    /**
     * Creates incident at StatusSiren
     *
     * @param createIncidentRequest Object to construct request parameters.
     * @return <code>CreateIncidentResponse</code> object containing StatusSiren response information.
     * @see com.ifountain.client.statussiren.model.incident.CreateIncidentRequest
     * @see com.ifountain.client.statussiren.model.incident.CreateIncidentResponse
     * @throws java.io.IOException,com.ifountain.client.ClientException,java.text.ParseException
     */
    public CreateIncidentResponse createIncident(CreateIncidentRequest createIncidentRequest)throws IOException, ClientException, ParseException;
    /**
     * Resolves incident at StatusSiren
     *
     * @param resolveIncidentRequest Object to construct request parameters.
     * @return <code>ResolveIncidentResponse</code> object containing StatusSiren response information.
     * @see com.ifountain.client.statussiren.model.incident.ResolveIncidentRequest
     * @see com.ifountain.client.statussiren.model.incident.ResolveIncidentResponse
     * @throws java.io.IOException,com.ifountain.client.ClientException,java.text.ParseException
     */
    public ResolveIncidentResponse resolveIncident(ResolveIncidentRequest resolveIncidentRequest)throws IOException, ClientException, ParseException;

    /**
     * Deletes incident at StatusSiren
     *
     * @param deleteIncidentRequest Object to construct request parameters
     * @return <code>ResolveIncidentResponse</code> object containing StatusSiren response information.
     * @see com.ifountain.client.statussiren.model.incident.DeleteIncidentRequest
     * @see com.ifountain.client.statussiren.model.incident.DeleteIncidentResponse
     * @throws java.io.IOException,com.ifountain.client.ClientException,java.text.ParseException
     */
    public DeleteIncidentResponse deleteIncident(DeleteIncidentRequest deleteIncidentRequest)throws IOException, ClientException, ParseException;

    /**
     * Updates incident at StatusSiren
     *
     * @param updateIncidentRequest Object to construct request parameters.
     * @return <code>UpdateIncidentResponse</code> object containing StatusSiren response information.
     * @see com.ifountain.client.statussiren.model.incident.UpdateIncidentRequest
     * @see com.ifountain.client.statussiren.model.incident.UpdateIncidentResponse
     * @throws java.io.IOException,com.ifountain.client.ClientException,java.text.ParseException
     */
    public com.ifountain.client.model.BaseResponse updateIncident(UpdateIncidentRequest updateIncidentRequest)throws IOException, ClientException, ParseException;

    /**
     * Retrieves specified incident with details from StatusSiren
     *
     * @param getIncidentRequest Object to construct request parameters
     * @return <code>GetIncidentResponse</code> object containing StatusSiren response information.
     * @see com.ifountain.client.statussiren.model.incident.GetIncidentRequest
     * @see com.ifountain.client.statussiren.model.incident.GetIncidentResponse
     * @throws java.io.IOException,com.ifountain.client.ClientException,java.text.ParseException
     */
    public com.ifountain.client.model.BaseResponse getIncident(GetIncidentRequest getIncidentRequest)throws IOException, ClientException, ParseException;

    /**
     *  Lists alerts from OpsGenie.
     *
     * @param listIncidentsRequest Object to construct request parameters
     * @return <code>ListIncidentResponse</code> object containing StatusSiren response information.
     * @see com.ifountain.client.statussiren.model.incident.ListIncidentsRequest
     * @see com.ifountain.client.statussiren.model.incident.ListIncidentsResponse
     * @throws java.io.IOException,com.ifountain.client.ClientException,java.text.ParseException
     */
    public ListIncidentsResponse listIncidents(ListIncidentsRequest listIncidentsRequest)throws IOException, ClientException, ParseException;

    /**
     * Set root endpoint uri that the client uses to send http requests.
     *
     * @param rootUri Uri to set.
     */
    public void setRootUri(String rootUri);

    /**
     * Closes client
     */
    public void close();
}
