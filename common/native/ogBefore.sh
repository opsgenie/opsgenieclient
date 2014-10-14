#!/bin/bash
if [  -z $(getent passwd opsgenie) ]
then
  useradd -U opsgenie
fi
