#!/bin/bash

echo "Welcome to the ticket system!";
echo "Are you a new member or already registered?";
read -p "(new/existing): " status;

if [[ $status = "new" ]];
  then
  bash ./components/register.sh;
fi

# if member is existing, show current information for member.
if [[ $status = "existing" ]];
  then
  read -p "What is your username? " uname;
# parse information received and send back file report.
  sh parser.sh mockdata.csv | grep "^Username: $uname$";
  #implement catch to check if user is valid. If so, implement imbedded
  #if to check for employee/manager
  export uname;
  ./components/employee.sh;
  #./components/manager.sh;
  exit 0;
else
#if not member, return reason for no information. Potentially utilize wrap to beginning?
  read -p "You are not an existing member. Try again from the beginning? (y/n)" resp;
  if [[ $resp = "y" ]];
   then
   bash ./login.sh;
  else
   echo "Have a great day!";
  fi
fi