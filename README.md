# RestService

[![Build Status](https://travis-ci.org/hiJackinGg/RestService.svg?branch=master)](https://travis-ci.org/hiJackinGg/RestService)

Run Postgresql server (if not runed yet). Access as <b>postgres</b> user and create <b>contactsdb</b> database (create database contactsdb;). Then you can run project via maven command: <b>mvn jetty:run</b>. 
Then access URI. Example: localhost:8080/hello/contacts?nameFilter=[your regular expression].

<b>Note:</b> Application will connect to the database on 5432 port under <b>postgres</b> user with password <b>"1"</b>. You can change these settengs in database.property file (in /resources directory) in case you have another Postgresql settings. 



(To compile and run project you need Maven, Java8, Postgresql.)
