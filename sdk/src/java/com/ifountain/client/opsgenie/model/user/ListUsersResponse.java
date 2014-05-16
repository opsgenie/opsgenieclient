package com.ifountain.client.opsgenie.model.user;

import com.ifountain.client.ClientConstants;
import com.ifountain.client.model.BaseResponse;
import com.ifountain.client.opsgenie.model.beans.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents OpsGenie service response for list user request.
 *
 * @author Mustafa Sener
 * @version 09.04.2013 17:03
 * @see com.ifountain.client.opsgenie.IUserOpsGenieClient#listUsers(ListUsersRequest)
 */
public class ListUsersResponse extends BaseResponse {
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
    public void deserialize(Map<String, Object> data) throws ParseException {
        super.deserialize(data);
        List<Map<String, Object>> usersData = (List<Map<String, Object>>) data.get(ClientConstants.API.USERS);
        users = new ArrayList<User>();
        if(usersData != null){
            for(Map<String, Object> userData:usersData){
                User user = new User();
                user.fromMap(userData);
                users.add(user);
            }
        }
    }
}
