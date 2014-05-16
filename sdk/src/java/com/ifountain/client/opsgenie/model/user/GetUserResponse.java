package com.ifountain.client.opsgenie.model.user;

import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.User;

import java.text.ParseException;
import java.util.Map;

/**
 * Represents OpsGenie service response for get user request.
 *
 * @author Mustafa Sener
 * @version 09.04.2013 17:03
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#getUser(GetUserRequest)
 */
public class GetUserResponse extends BaseResponse{
    private User user;

    /**
     * Details of user
     * @see User
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets details of user
     * @see User
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        user = new User();
        user.fromMap(data);
    }
}
