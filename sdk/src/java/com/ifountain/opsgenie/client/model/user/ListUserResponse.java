package com.ifountain.opsgenie.client.model.user;

import com.ifountain.opsgenie.client.OpsGenieClientConstants;
import com.ifountain.opsgenie.client.model.BaseResponse;
import com.ifountain.opsgenie.client.model.beans.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list user request.
 *
 * @see com.ifountain.opsgenie.client.IUserOpsGenieClient#listUsers(ListUserRequest)
 */
public class ListUserResponse extends BaseResponse {
    private List<User> users;

    /**
     * List of users
     * @see User
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets list of users
     * @see User
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public void deserialize(Map data) throws ParseException {
        super.deserialize(data);
        List<Map> usersData = (List<Map>) data.get(OpsGenieClientConstants.API.USERS);
        users = new ArrayList<User>();
        if(usersData != null){
            for(Map userData:usersData){
                User user = new User();
                user.fromMap(userData);
                users.add(user);
            }
        }
    }
}
