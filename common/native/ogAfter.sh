#!/bin/bash

if [ ! -d "/var/log/opsgenie" ]; then
    mkdir /var/log/opsgenie
fi

if [ ! -d "/var/log/opsgenie/marid" ]; then
    mkdir /var/log/opsgenie/marid
fi

chmod -R 775 /var/log/opsgenie
chmod -R g+s /var/log/opsgenie
chmod -R 775 /var/lib/opsgenie
chmod -R 775 /etc/opsgenie
chmod -R 775 /var/opsgenie
chmod -R +x /etc/init.d/marid

chown -R opsgenie:opsgenie /etc/opsgenie
chown -R opsgenie:opsgenie /var/log/opsgenie
chown -R opsgenie:opsgenie /var/lib/opsgenie
chown -R opsgenie:opsgenie /var/opsgenie

JAVA_CMD=java
if [ ! -z $JAVA_HOME ]; then
        JAVA_CMD=$JAVA_HOME/bin/java
fi

type -P $JAVA_CMD &>/dev/null && echo "" || echo "WARNING : No java executable found. If you're going to use Marid on this machine, you should set your JAVA_HOME variable in /etc/opsgenie/profile"
