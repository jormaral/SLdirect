==========================================================================
=== Process Mapping Web Application
==========================================================================
@author Alejandro Sánchez Acosta <asanchez@neurowork.net>



==========================================================================
=== Build and Deployment
==========================================================================

The Process Mapping Web Application is built using Maven.
When the project is first built, Maven will automatically download all 
required dependencies (if these haven't been downloaded before). Thus the 
initial build may take a few minutes depending on the speed of your 
Internet connection, but subsequent builds will be much faster.

Available build commands:

- mvn clean         --> cleans the project
- mvn clean test    --> cleans the project and runs all tests
- mvn clean package --> cleans the project and builds the WAR

After building the project with "mvn clean package", you will find the
resulting WAR file in the "target/" directory. No other steps are 
necessary to get the data source up and running: you can simply deploy 
the built WAR file directly to your Servlet container.
