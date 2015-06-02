#!/bin/bash
if [  -z $(getent passwd opsgenie) ]
then
    groupadd opsgenie
    useradd -g opsgenie opsgenie -r -d /var/opsgenie/
fi
