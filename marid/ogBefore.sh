#!/bin/bash
if [  -z $(getent passwd opsgenie) ]
then
  useradd opsgenie
fi
