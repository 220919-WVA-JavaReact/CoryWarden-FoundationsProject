#!/bin/bash

echo "Welcome! Please answer a few questions below.";

read -p "What is your first name? " fname;
read -p "What is your last name? " lname;
read -p "What is your email? " email;
read -p "What is your username? " user;
read -p "What is your password? " pw;
echo "You have now signed up!";

echo "$id,$fname,$lname,$email,$user,$pw,employee" >> mockdata.csv; #push saved variables into mockdata in csv format
echo "Returning you to login area.";
bash ./login.sh;