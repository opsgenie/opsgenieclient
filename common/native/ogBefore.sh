#!/bin/bash
if [  -z $(getent passwd opsgenie) ]
then
    groupadd opsgenie
    useradd -g opsgenie opsgenie -d /var/opsgenie/ --no-create-home
fi
