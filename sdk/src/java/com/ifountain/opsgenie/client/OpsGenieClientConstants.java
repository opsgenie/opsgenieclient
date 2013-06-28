package com.ifountain.opsgenie.client;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 5/28/12 3:01 PM
 */
public interface OpsGenieClientConstants {
    public static final String OPSGENIE_API_URI = "https://api.opsgenie.com";

    public interface Common {
        String API_DATE_FORMAT = "yyyy-MM-dd HH:mm";
        String API_DATE_FORMAT_WITH_TIMEZONE = "yyyy-MM-dd HH:mm z";
    }
    public interface API {
        public static final String ID = "id";
        public static final String CUSTOMER_KEY = "customerKey";
        public static final String MESSAGE = "message";
        public static final String DESCRIPTION = "description";
        public static final String SOURCE = "source";
        public static final String NAME = "name";
        public static final String ENTITY = "entity";
        public static final String ALIAS = "alias";
        public static final String TAGS = "tags";
        public static final String DETAILS = "details";
        public static final String ACTIONS = "actions";
        public static final String ACTION = "action";
        public static final String RECIPIENTS = "recipients";
        public static final String RECIPIENT = "recipient";
        public static final String STATUS = "status";
        public static final String CREATED_AT = "createdAt";
        public static final String COUNT = "count";
        public static final String ALERT_ID = "alertId";
        public static final String NOTE = "note";
        public static final String NOTIFY = "notify";
        public static final String ATTACHMENT = "attachment";
        public static final String INDEX_FILE = "indexFile";
        public static final String HELP = "help";
        public static final String USER = "user";
        public static final String OWNER = "owner";
        public static final String IS_SEEN = "isSeen";
        public static final String ACKNOWLEDGED = "acknowledged";
        public static final String START_DATE = "startDate";
        public static final String END_DATE = "endDate";
        public static final String FROM_USER = "fromUser";
        public static final String TO_USER = "toUser";
        public static final String TIMEZONE = "timezone";
        public static final String TINY_ID = "tinyId";
        public static final String USERNAME = "username";
        public static final String FULLNAME = "fullname";
        public static final String ROLE = "role";
        public static final String STATE = "state";
        public static final String GROUPS = "groups";
        public static final String ESCALATIONS = "escalations";
        public static final String SCHEDULES = "schedules";
        public static final String USERS = "users";
        public static final String RULES = "rules";
        public static final String TYPE = "type";
        public static final String DELAY = "delay";
        public static final String START_DAY = "startDay";
        public static final String END_DAY = "endDay";
        public static final String START_TIME = "startTime";
        public static final String END_TIME = "endTime";
        public static final String UPDATED_AT = "updatedAt";
        public static final String ENABLED = "enabled";
        public static final String ROTATION_TYPE = "rotationType";
        public static final String ROTATION_LENGTH = "rotationLength";
        public static final String PARTICIPANTS = "participants";
        public static final String RESTRICTIONS = "restrictions";
        public static final String TOOK = "took";
        public static final String ALERTS = "alerts";
        public static final String RESULT = "result";
        public static final String FORWARDINGS = "forwardings";
        public static final String ON_CALLS = "oncalls";
        public static final String FORWARDED = "forwarded";
        public static final String CREATED_AFTER = "createdAfter";
        public static final String CREATED_BEFORE = "createdBefore";
        public static final String UPDATED_AFTER = "updatedAfter";
        public static final String UPDATED_BEFORE = "updatedBefore";
        public static final String SORT_BY = "sortBy";
        public static final String ORDER = "order";
        public static final String LIMIT = "limit";
        public static final String PARTICIPANT = "participant";
        public static final String LAST_HEARTBEAT = "lastHeartbeat";
        public static final String EXPIRED = "expired";
        public static final String SOURCES = "sources";
        public static final String LOG = "log";
        public static final String LOG_TYPE = "logType";
        public static final String LOGS = "logs";
        public static final String STATE_CHANGED_AT = "stateChangedAt";
        public static final String METHOD = "method";
        public static final String LAST_KEY = "lastKey";
    }
}
