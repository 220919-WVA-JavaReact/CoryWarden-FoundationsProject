#!/bin/bash

echo "Hello, $uname!";

echo "Would you like to check pending reimbursements, review completed ones, or logout?";
read -p "(pending/complete/logout):  " resp;

if [[ $resp == "pending" ]];
  then
  #implement code to pull pending reimbursement requests from database
  sh ticketparser.sh tickets.csv | grep  -A5 "Ticket Status: Pending";
  echo "Type the ticket number you would like to review";
  read ticket;
  sh ticketparser.sh tickets.csv | grep -A4 -B1 "Ticket ID: $ticket";
  echo "Would you like this to be approved or denied? ";
  read -p "(A/D):  " newstatus; 
  #update status within csv--still working on code.
  if [[ $newstatus == "A" ]];
    then
    #implement status chng, force comp approved w other vars and mv to new file
    echo "1"
  else
    echo "Your response was invalid.";
    bash ./manager.sh;
  fi

elif [[ $resp == "complete" ]]
  then
  #implement code to pull processed reimbursements from seperate portion of data field
  sh ticketparser.sh ticketscomp.csv
else
  echo "Have a good day!"
  exit 0;
fi