package com.ifountain.opsgenie.client;

/**
 * @author Sezgin Kucukkaraaslan
 * @version 5/28/12 3:01 PM
 */
public interface OpsGenieClientConstants {
    String OPSGENIE_API_URI = "https://api.opsgenie.com";


    interface Common {
        String API_DATE_FORMAT = "yyyy-MM-dd HH:mm";
        String API_DATE_FORMAT_WITH_TIMEZONE = "yyyy-MM-dd HH:mm z";
    }

    interface API {
        String ID = "id";
        String API_KEY = "apiKey";
        String CUSTOMER_KEY = "customerKey";
        String MESSAGE = "message";
        String DESCRIPTION = "description";
        String DIRECTION = "direction";
        String SOURCE = "source";
        String NAME = "name";
        String ENTITY = "entity";
        String ALIAS = "alias";
        String TAGS = "tags";
        String TAGS_OPERATOR = "tagsOperator";
        String QUERY = "query";
        String REQUEST_ID = "requestId";
        String TINY = "tiny";
        String DETAILS = "details";
        String ACTIONS = "actions";
        String DATA = "data";
        String SORT = "sort";
        String ACTION = "action";
        String RECIPIENTS = "recipients";
        String TEAM = "team";
        String TEAMS = "teams";
        String RECIPIENT = "recipient";
        String SEARCH_IDENTIFIER = "searchIdentifier";
        String SEARCH_IDENTIFIER_TYPE = "searchIdentifierType";
        String STATUS = "status";
        String CREATED_AT = "createdAt";
        String COUNT = "count";
        String OFFSET = "offset";
        String ALERT_ID = "alertId";
        String NOTE = "note";
        String NOTIFY = "notify";
        String ATTACHMENT = "attachment";
        String ALERT_IDENTIFIER_TYPE = "alertIdentifierType";
        String FILE = "file";
        String INDEX_FILE = "indexFile";
        String PAGING = "paging";
        String FIRST = "first";
        String NEXT = "next";
        String HELP = "help";
        String USER = "user";
        String OWNER = "owner";
        String IS_SEEN = "isSeen";
        String ACKNOWLEDGED = "acknowledged";
        String START_DATE = "startDate";
        String END_DATE = "endDate";
        String FROM_USER = "fromUser";
        String TO_USER = "toUser";
        String TIMEZONE = "timezone";
        String TINY_ID = "tinyId";
        String USERNAME = "username";
        String FULLNAME = "fullname";
        String ROLE = "role";
        String STATE = "state";
        String GROUPS = "groups";
        String GROUP = "group";
        String ESCALATION = "escalation";
        String ESCALATIONS = "escalations";
        String SCHEDULES = "schedules";
        String OVERRIDES = "overrides";
        String SCHEDULE = "schedule";
        String USERS = "users";
        String MEMBERS = "members";
        String RULES = "rules";
        String LAYERS = "layers";
        String ROTATIONS = "rotations";
        String ROTATION_IDS = "rotationIds";
        String TYPE = "type";
        String NOTIFY_TYPE = "notifyType";
        String DELAY = "delay";
        String START_DAY = "startDay";
        String END_DAY = "endDay";
        String START_TIME = "startTime";
        String END_TIME = "endTime";
        String UPDATED_AT = "updatedAt";
        String ENABLED = "enabled";
        String ENABLE = "enable";
        String ROTATION_TYPE = "rotationType";
        String ROTATION_LENGTH = "rotationLength";
        String PARTICIPANTS = "participants";
        String RESTRICTIONS = "restrictions";
        String TOOK = "took";
        String ALERTS = "alerts";
        String RESULT = "result";
        String FORWARDINGS = "forwardings";
        String ON_CALLS = "oncalls";
        String FORWARDED = "forwarded";
        String CREATED_AFTER = "createdAfter";
        String CREATED_BEFORE = "createdBefore";
        String UPDATED_AFTER = "updatedAfter";
        String UPDATED_BEFORE = "updatedBefore";
        String SORT_BY = "sortBy";
        String ORDER = "order";
        String LIMIT = "limit";
        String PARTICIPANT = "participant";
        String LAST_HEARTBEAT = "lastHeartbeat";
        String EXPIRED = "expired";
        String SOURCES = "sources";
        String LOCALE = "locale";
        String LOG = "log";
        String LOG_TYPE = "logType";
        String LOGS = "logs";
        String STATE_CHANGED_AT = "stateChangedAt";
        String METHOD = "method";
        String LAST_KEY = "lastKey";
        String TIME = "time";
        String SYSTEM_DATA = "systemData";
        String ESCALATION_TIME = "escalationTime";
        String FORWARDED_FROM = "forwardedFrom";
        String CONTACTS = "contacts";
        String INTEGRATION = "integration";
        String POLICY = "policy";
        String INTERVAL = "interval";
        String INTERVAL_UNIT = "intervalUnit";
        String HEARTBEATS = "heartbeats";
        String NOTES = "notes";
        String TO_USERS = "toUsers";
        String RULE_TYPES = "ruleTypes";
        String FLAT = "flat";
        String ESCALATION_ID = "escalationId";
        String ESCALATION_NAME = "escalationName";
        String KEYS = "keys";
        //contact constants
        String USER_ID = "userId";
        String USER_CONTACTS = "userContacts";
        String TO = "to";
        String DISABLED_REASON = "disabledReason";
        String USER_COUNT = "userCount";
        String PLAN = "plan";
        String IS_YEARLY = "isYearly";
        String MAX_USER_COUNT = "maxUserCount";
        //notification action constants
        String ACTION_TYPE = "actionType";
        String RENOTIFIED_ALERT = "Renotified Alert";
        String SCHEDULE_END = "Schedule End";
        String INCOMING_CALL_ROUTING = "Incoming Call Routing";
        String SCHEDULE_START = "Schedule Start";
        String CLOSED_ALERT = "Closed Alert";
        String NEW_ALERT = "New Alert";
        String ASSIGNED_ALERT = "Assigned Alert";
        String ACKNOWLEDGED_ALERT = "Acknowledged Alert";
        String ADD_NOTE = "Add Note";
        //notification condition constants
        String CONDITIONS = "conditions";
        String CONDITION_MATCH_TYPE = "conditionMatchType";
        String CONDITION_MATCH_ALL = "Match All";
        String CONDITION_MATCH_ALL_CONDITIONS = "Match All Conditions";
        String CONDITION_MATCH_ANY_CONDITIONS = "Match Any Condition";
        //notification restriction constants
        String RESTRICTION_END_HOUR = "endHour";
        String RESTRICTION_END_MINUTE = "endMinute";
        String RESTRICTION_END_DAY = "endDay";
        String RESTRICTION_START_HOUR = "startHour";
        String RESTRICTION_START_MINUTE = "startMinute";
        String RESTRICTION_START_DAY = "startDay";
        //notification operation constants
        String OPERATION = "operation";
        String OPERATION_CONTAINS = "Contains";
        String OPERATION_CONTAINS_KEY = "Contains Key";
        String OPERATION_CONTAINS_VALUE = "Contains Value";
        String OPERATION_EQUALS_IGNORE_WHITE_SPACE = "Equals Ignore Whitespace";
        String OPERATION_IS_EMPTY = "Is Empty";
        String OPERATION_EQUALS = "Equals";
        String OPERATION_MATCHES = "Matches";
        String OPERATION_STARTS_WITH = "Starts With";
        String OPERATION_ENDS_WITH = "Ends With";
        //notification variable constants
        String EXTRA_PROPERTIES = "extraProperties";
        String FIELD = "field";
        String NOT = "not";
        String EXPECTED_VALUE = "expectedValue";
        String APPLY_ORDER = "applyOrder";
        String LOOP_AFTER = "loopAfter";
        String STEPS = "steps";
        String SEND_AFTER = "sendAfter";
        //notification notifyBefore constants
        String NOTIFY_BEFORE = "notifyBefore";
        String NOTIFY_ONE_HOUR = "1 hour";
        String NOTIFY_ONE_DAY = "1 day";
        String NOTIFY_FIFTEEN_MINUTE = "15 mins";
        String NOTIFY_JUST_BEFORE = "Just Before";

        String RULE_ID = "ruleId";
        String EMAIL = "email";
        String VOICE = "voice";
        String SMS = "sms";
        String MOBILE_APP = "mobile";

        //Day of Week Enum
        String SUNDAY = "SUNDAY";
        String MONDAY = "MONDAY";
        String TUESDAY = "TUESDAY";
        String WEDNESDAY = "WEDNESDAY";
        String THURSDAY = "THURSDAY";
        String FRIDAY = "FRIDAY";
        String SATURDAY = "SATURDAY";
    }
}
