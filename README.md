# sprintbootwebapp

This is a sample SpringBoot web app which uses following technologies -
- Thymeleaf which is a template engine (like Freemarker or Velocity)
- h2 in-memory database
- bootstrap for adding styling to the web pages
- jdom for processing XML

Instructions to setup in Eclipse
* Download the project 
* Import the project as a Maven project in Eclipse

For Testing
* Download and setup ngrok 
* Start the ngrok executable from command prompt
* Notice the domain name
* Use it to configure URLs in the AppDirect's 'Run Ping Tests' page
e.g. http://68a3ca36.ngrok.io/api/v1/subscription/create?url={eventUrl}
* Run the SpringbootwebappApplication class
* Click 'Run All Tests' on the AppDirect page


