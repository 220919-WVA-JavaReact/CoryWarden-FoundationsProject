#!/bin/bash

echo "Hello, $uname!";

echo "Would you like to submit a new ticket, check ticket status, or log out?";
read -p "(new/status/logout)" resp;

status="Pending"
if [[ $resp == "new" ]];
  then
  #implement form to complete and submit to database.
  echo "Status:  " $status;
  #somehow create new ticket id?
  read -p "Disbursement Amount:  " amount;
  read -p "Description for disbursement:  " description;
  echo "$status,$amount,$description,$uname" >> tickets.txt;

elif [[ $resp == "status" ]];
  then
  #implement code to pull existing tickets related to this account.
  echo "1"
else
  echo "Have a good day!";
fi