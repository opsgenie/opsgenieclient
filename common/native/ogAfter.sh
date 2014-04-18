#!/bin/bash

mkdir /var/log/opsgenie
mkdir /var/log/opsgenie/lamp
mkdir /var/log/opsgenie/opsgenie
chmod -R 755 /var/log/opsgenie
chmod -R g+s /var/log/opsgenie
chmod -R 755 /var/lib/opsgenie
chmod -R 755 /etc/opsgenie
chmod -R 755 /var/opsgenie

chown -R opsgenie:opsgenie /etc/opsgenie
chown -R opsgenie:opsgenie /var/log/opsgenie
chown -R opsgenie:opsgenie /var/lib/opsgenie
chown -R opsgenie:opsgenie /var/opsgenie
