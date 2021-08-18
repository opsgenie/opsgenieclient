package com.ifountain.opsgenie.client.script.util.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.util.JsonUtils;
import com.opsgenie.oas.sdk.model.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by bcelenk on 10/27/17.
 */
public class RecipientDeserializer extends StdDeserializer<Recipient> {

    public RecipientDeserializer() {
        this(null);
    }

    public RecipientDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Recipient deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Recipient recipient = null;
        JsonNode node = jp.getCodec().readTree(jp);
        Recipient.TypeEnum type = Recipient.TypeEnum.fromValue(node.get(OpsGenieClientConstants.API.TYPE).asText());
        if (type != null) {
            Map recipientInfo = mapper.convertValue(node, Map.class);
            recipientInfo.remove(OpsGenieClientConstants.API.TYPE);
            if (type.getValue().equals(Recipient.TypeEnum.USER.getValue())) {
                recipient = new UserRecipient();
            } else if (type.getValue().equals(Recipient.TypeEnum.TEAM.getValue())) {
                recipient = new TeamRecipient();
            } else if (type.getValue().equals(Recipient.TypeEnum.SCHEDULE.getValue())) {
                recipient = new ScheduleRecipient();
            } else if (type.getValue().equals(Recipient.TypeEnum.ESCALATION.getValue())) {
                recipient = new EscalationRecipient();
            } else if (type.getValue().equals(Recipient.TypeEnum.NONE.getValue())) {
                recipient = new NoRecipient();
            } else if (type.getValue().equals(Recipient.TypeEnum.GROUP.getValue())) {
                recipient = new GroupRecipient();
            } else if (type.getValue().equals(Recipient.TypeEnum.ALL.getValue())) {
                recipient = new AllRecipient();
            }

            try {
                JsonUtils.fromMap(recipient, recipientInfo);
                return recipient;
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return new Recipient();
    }
}
