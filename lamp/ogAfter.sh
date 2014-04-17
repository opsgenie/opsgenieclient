#!/bin/bash

chmod -R 755 /var/log/opsgenie/lamp
chmod -R g+s /var/log/opsgenie/lamp
chmod -R 755 /var/lib/opsgenie/lamp
chmod -R 755 /etc/opsgenie/lamp
chmod -R 755 /var/opsgenie/lamp

chown -R opsgenie:opsgenie /etc/opsgenie/lamp
chown -R opsgenie:opsgenie /var/log/opsgenie/lamp
chown -R opsgenie:opsgenie /var/lib/opsgenie/lamp
chown -R opsgenie:opsgenie /var/opsgenie/lamp
