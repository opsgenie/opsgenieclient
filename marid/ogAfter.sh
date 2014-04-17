#!/bin/bash

chmod -R 755 /var/log/opsgenie/marid
chmod -R g+s /var/log/opsgenie/marid
chmod -R 755 /var/lib/opsgenie/marid
chmod -R 755 /etc/opsgenie/marid
chmod -R 755 /var/opsgenie/marid

chown -R opsgenie:opsgenie /etc/opsgenie/marid
chown -R opsgenie:opsgenie /var/log/opsgenie/marid
chown -R opsgenie:opsgenie /var/lib/opsgenie/marid
chown -R opsgenie:opsgenie /var/opsgenie/marid
