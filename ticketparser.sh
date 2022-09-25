#!/bin/bash

exec < $1
read header #read/skip first line of csv file.

while IFS="," read -r status ticket uname amount description
do
    echo "Ticket Status: $status";
    echo "Ticket ID: $ticket";
    echo "Submitted by: $uname";
	echo "Amount: $ $amount";
	echo "Description: $description";
    echo "+---------------------------------------+";
done
