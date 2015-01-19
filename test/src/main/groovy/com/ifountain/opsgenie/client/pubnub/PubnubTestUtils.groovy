package com.ifountain.opsgenie.client.pubnub

import com.ifountain.opsgenie.client.test.util.CommonTestUtils

/**
 * @author Sezgin Kucukkaraaslan
 * @version 8/7/12 11:41 AM
 */
class PubnubTestUtils {

    public static PubnubCredentials getCredentials() {
        PubnubCredentials credentials = new PubnubCredentials();
        credentials.setPublishKey(CommonTestUtils.getTestProperty("Pubnub.publishKey", ""))
        credentials.setSecretKey(CommonTestUtils.getTestProperty("Pubnub.secretKey", ""))
        credentials.setSubscribeKey(CommonTestUtils.getTestProperty("Pubnub.subscribeKey", ""))
        credentials.setSslOn(Boolean.parseBoolean(CommonTestUtils.getTestProperty("Pubnub.sslOn", "true")))
        return credentials;
    }
}
