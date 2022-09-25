#!/bin/bash

echo "Hello, $uname!";

echo "Would you like to check pending reimbursements, review completed ones, or logout?";
read -p "(pending/complete/logout): " resp;

if [[ $resp == "pending" ]];
  then
  #implement code to pull pending reimbursement requests from database
  cat tickets.txt | grep  -A3 "Pending";
  echo "+--------------------------------------------+";
  echo "Type the ticket number you would like to review";
  read ticket;
  echo "Would you like this to be approved or denied? ";
  read -p "(A/D):  " newstatus;
  
elif [[ $resp == "complete" ]]
  then
  #implement code to pull processed reimbursements from seperate portion of data field
  cat tickets.txt | grep  -A3 "Complete";
else
  echo "Have a good day!"
  exit 0;
fi