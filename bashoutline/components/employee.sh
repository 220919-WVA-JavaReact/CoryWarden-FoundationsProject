#!/bin/bash

echo "Hello, $uname!";

echo "Would you like to submit a new ticket, check ticket status, or log out?";
read -p "(new/status/logout):  " resp;

status="Pending" #auto defaults status to pending when ticket generated
ticket=$RANDOM #generates random number between 1 - 32k
if [[ $resp == "new" ]];
  then
  #implement form to complete and submit to database.
  echo "Status:  " $status;
  echo "Ticket:  " $ticket;
  read -p "Disbursement Amount:  " amount;
  read -p "Description for disbursement:  " description;
  echo "$status,$ticket,$uname,$amount,$description" >> tickets.csv;
  #ticket+=1
elif [[ $resp == "status" ]];
  then
  #implement code to pull existing tickets related to this account.
  sh ticketparser.sh tickets.csv | grep  -A3 -B2 "$uname";
else
  echo "Have a good day!";
fi