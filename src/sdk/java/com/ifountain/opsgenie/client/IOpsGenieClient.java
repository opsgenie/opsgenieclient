package com.ifountain.opsgenie.client;

import com.ifountain.opsgenie.client.model.*;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;

/**
 * Created by Sezgin Kucukkaraaslan
 * Date: 6/1/12
 * Time: 10:09 AM
 */
public interface IOpsGenieClient {
    public CreateAlertResponse createAlert(CreateAlertRequest request) throws IOException, OpsGenieClientException;

    public CloseAlertResponse closeAlert(CloseAlertRequest request) throws OpsGenieClientException, IOException;

    public AddNoteResponse addNote(AddNoteRequest request) throws OpsGenieClientException, IOException;

    public HeartbeatResponse heartbeat(HeartbeatRequest request) throws OpsGenieClientException, IOException;

    public AttachResponse attach(AttachRequest request) throws OpsGenieClientException, IOException;

    public GetAlertResponse getAlert(GetAlertRequest request) throws OpsGenieClientException, IOException;

    public void setRootUri(String rootUri);
}
