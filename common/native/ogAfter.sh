#!/bin/bash

if [ ! -d "/var/log/opsgenie" ]; then
    mkdir /var/log/opsgenie
fi

if [ ! -d "/var/log/opsgenie/lamp" ]; then
    mkdir /var/log/opsgenie/lamp
fi

if [ ! -d "/var/log/opsgenie/marid" ]; then
    mkdir /var/log/opsgenie/marid
fi

mv /etc/opsgenie/lamp/conf/* /etc/opsgenie/lamp/
rm -r /etc/opsgenie/lamp/conf
mv /var/lib/opsgenie/lamp/lib/* /var/lib/opsgenie/lamp/
rm -r /var/lib/opsgenie/lamp/lib

mv /etc/opsgenie/marid/conf/* /etc/opsgenie/marid/
rm -r /etc/opsgenie/marid/conf
mv /var/lib/opsgenie/marid/lib/* /var/lib/opsgenie/marid/
rm -r /var/lib/opsgenie/marid/lib

chmod -R 775 /var/log/opsgenie
chmod -R g+s /var/log/opsgenie
chmod -R 775 /var/lib/opsgenie
chmod -R 775 /etc/opsgenie
chmod -R 775 /var/opsgenie
chmod -R +x /usr/local/bin/lamp
chmod -R +x /etc/init.d/marid

chown -R opsgenie:opsgenie /etc/opsgenie
chown -R opsgenie:opsgenie /var/log/opsgenie
chown -R opsgenie:opsgenie /var/lib/opsgenie
chown -R opsgenie:opsgenie /var/opsgenie

JAVA_CMD=java
if [ ! -z $JAVA_HOME ]; then
        JAVA_CMD=$JAVA_HOME/bin/java
fi

type -P $JAVA_CMD &>/dev/null && echo "" || echo "-   No java executable found. Please set your JAVA_HOME variable in /etc/opsgenie/profile file properly!"
