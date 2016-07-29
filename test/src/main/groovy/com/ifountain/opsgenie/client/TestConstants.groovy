package com.ifountain.opsgenie.client;

public interface TestConstants {
    public static final String OPSGENIE_API_URI = "https://api.opsgenie.com";

    public interface Common {
        String API_DATE_FORMAT = "yyyy-MM-dd HH:mm";
        String API_DATE_FORMAT_WITH_TIMEZONE = "yyyy-MM-dd HH:mm z";
    }
    public interface API {
        public static final String ID = "id";
        public static final String API_KEY = "apiKey";
        public static final String CUSTOMER_KEY = "customerKey";
        public static final String MESSAGE = "message";
        public static final String DESCRIPTION = "description";
        public static final String SOURCE = "source";
        public static final String NAME = "name";
        public static final String ENTITY = "entity";
        public static final String ALIAS = "alias";
        public static final String TAGS = "tags";
        public static final String TAGS_OPERATOR = "tagsOperator";
        public static final String DETAILS = "details";
        public static final String ACTIONS = "actions";
        public static final String ACTION = "action";
        public static final String RECIPIENTS = "recipients";
        public static final String TEAM = "team";
        public static final String TEAMS = "teams";
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
        public static final String OVERRIDES = "overrides";
        public static final String SCHEDULE = "schedule";
        public static final String USERS = "users";
        public static final String MEMBERS = "members";
        public static final String RULES = "rules";
        public static final String LAYERS = "layers";
        public static final String ROTATIONS = "rotations";
        public static final String ROTATION_ID = "rotationId";
        public static final String ROTATION_IDS = "rotationIds";
        public static final String TYPE = "type";
        public static final String NOTIFY_TYPE = "notifyType";
        public static final String DELAY = "delay";
        public static final String START_DAY = "startDay";
        public static final String END_DAY = "endDay";
        public static final String START_TIME = "startTime";
        public static final String END_TIME = "endTime";
        public static final String UPDATED_AT = "updatedAt";
        public static final String ENABLED = "enabled";
        public static final String ENABLE = "enable";
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
        public static final String LOCALE = "locale";
        public static final String LOG = "log";
        public static final String LOG_TYPE = "logType";
        public static final String LOGS = "logs";
        public static final String STATE_CHANGED_AT = "stateChangedAt";
        public static final String METHOD = "method";
        public static final String LAST_KEY = "lastKey";
        public static final String TIME = "time";
        public static final String SYSTEM_DATA = "systemData";
        public static final String ESCALATION_TIME = "escalationTime";
        public static final String FORWARDED_FROM = "forwardedFrom";
        public static final String CONTACTS = "contacts";
        public static final String INTEGRATION = "integration";
        public static final String POLICY = "policy";
        public static final String INTERVAL = "interval";
        public static final String INTERVAL_UNIT = "intervalUnit";
        public static final String HEARTBEATS = "heartbeats";
        public static final String NOTES = "notes";
        public static final String FLAT = "flat";
        //contact constants
        public static final String USER_ID = "userId";
        public static final String USER_CONTACTS = "userContacts";
        public static final String TO = "to";
        public static final String DISABLED_REASON = "disabledReason";
        public static final String USER_COUNT = "userCount";
        public static final String PLAN = "plan";
        public static final String IS_YEARLY = "isYearly";
        public static final String MAX_USER_COUNT = "maxUserCount";
        //notification action constants
        public static final String ACTION_TYPE = "actionType";
        public static final String RENOTIFIED_ALERT = "Renotified Alert";
        public static final String SCHEDULE_END = "Schedule End";
        public static final String INCOMING_CALL_ROUTING = "Incoming Call Routing";
        public static final String SCHEDULE_START = "Schedule Start";
        public static final String CLOSED_ALERT = "Closed Alert";
        public static final String NEW_ALERT = "New Alert";
        public static final String ASSIGNED_ALERT = "Assigned Alert";
        public static final String ACKNOWLEDGED_ALERT = "Acknowledged Alert";
        public static final String ADD_NOTE = "Add Note";
        //notification condition constants
        public static final String CONDITIONS = "conditions";
        public static final String CONDITION_MATCH_TYPE = "conditionMatchType";
        public static final String CONDITION_MATCH_ALL = "Match All";
        public static final String CONDITION_MATCH_ALL_CONDITIONS = "Match All Conditions";
        public static final String CONDITION_MATCH_ANY_CONDITIONS = "Match Any Condition";
        //notification restriction constants
        public static final String RESTRICTION_END_HOUR = "endHour";
        public static final String RESTRICTION_END_MINUTE = "endMinute";
        public static final String RESTRICTION_END_DAY = "endDay";
        public static final String RESTRICTION_START_HOUR = "startHour";
        public static final String RESTRICTION_START_MINUTE = "startMinute";
        public static final String RESTRICTION_START_DAY = "startDay";
        //notification operation constants
        public static final String OPERATION = "operation";
        public static final String OPERATION_CONTAINS = "Contains";
        public static final String OPERATION_CONTAINS_KEY = "Contains Key";
        public static final String OPERATION_CONTAINS_VALUE = "Contains Value";
        public static final String OPERATION_EQUALS_IGNORE_WHITE_SPACE = "Equals Ignore Whitespace";
        public static final String OPERATION_IS_EMPTY = "Is Empty";
        public static final String OPERATION_EQUALS = "Equals";
        public static final String OPERATION_MATCHES = "Matches";
        public static final String OPERATION_STARTS_WITH = "Starts With";
        public static final String OPERATION_ENDS_WITH = "Ends With";
        //notification variable constants
        public static final String EXTRA_PROPERTIES = "extraProperties";
        public static final String FIELD = "field";
        public static final String NOT = "not";
        public static final String EXPECTED_VALUE = "expectedValue";
        public static final String APPLY_ORDER = "applyOrder";
        public static final String LOOP_AFTER = "loopAfter";
        public static final String STEPS = "steps";
        public static final String SEND_AFTER = "sendAfter";
        //notification notifyBefore constants
        public static final String NOTIFY_BEFORE = "notifyBefore";
        public static final String NOTIFY_ONE_HOUR = "1 hour";
        public static final String NOTIFY_ONE_DAY = "1 day";
        public static final String NOTIFY_FIFTEEN_MINUTE = "15 mins";
        public static final String NOTIFY_JUST_BEFORE = "Just Before";

        public static final String RULE_ID = "ruleId";
        public static final String EMAIL = "email";
        public static final String VOICE = "voice";
        public static final String SMS = "sms";

    }

    public interface AWS {
        public static final String ACCESS_KEY = "aws.accesskey";
        public static final String SECRET_KEY = "aws.secretkey";
        public static final String DYNAMO_DB_CLIENT_PREFIX = "aws.dynamodb.client";
        public static final String SQS_CLIENT_PREFIX = "aws.sqs.client";
        public static final String SIMPLEDB_CLIENT_PREFIX = "aws.sdb.client";
        public static final String KINESIS_CLIENT_PREFIX = "aws.kinesis.client";
        public static final String S3_CLIENT_PREFIX = "aws.s3.client";
        public static final String EC2_CLIENT_PREFIX = "aws.ec2.client";
        public static final String CLOUDWATCH_CLIENT_PREFIX = "aws.cw.client";
        public static final String MAX_ERROR_RETRY_COUNT = "maxErrorRetry";
        public static final String SIMPLEDB_QUERY_TIMEOUT_RETRY_COUNT = "queryTimeoutRetry";
        public static final String DYNAMODB_PROVISION_EXCEEDED_RETRY_COUNT = "provisionExceededRetry";
        public static final String MAX_NUMBER_OF_CONNECTIONS = "maxNumberOfConnections";
        public static final String CONNECTION_TIMEOUT = "connectionTimeout";
        public static final String RESTORE_ACCESS_KEY = "restore.aws.accesskey";
        public static final String RESTORE_SECRET_KEY = "restore.aws.secretkey";
        String X_FORWARDED_FOR = "X-Forwarded-For";
        String X_FORWARDED_FOR_PROTO = "X-Forwarded-Proto";
        String ENDPOINT = "endpoint";
        String LOCAL_FILE_SYSTEM_CONFIG = "aws.file.local.root";
        String AWS_FILE_SYSTEM_BUCKET_NAME_CONFIG = "aws.file.s3.bucketName";
        public static final String ACTIVE_STATUS = "ACTIVE";
    }
}