# mail-test-networking

This is a Java project that tests the connectivity of an email server by sending and receiving messages through a client-server architecture. It has a user interface built with Java form and can be used to troubleshoot issues with email delivery or to verify that a server is properly configured.

## Prerequisites

In order to use this project, you will need the following:

- Java Development Kit (JDK) 8 or higher
- Any Java Integrated Development Environment (IDE) such as Eclipse or IntelliJ IDEA

## How to use

To use this project, follow these steps:

1. Clone the repository to your local machine.

2. Open the project in your chosen IDE.

3. Modify the following variables in the 

	```
	MailClient.java
	```

	 and 

	```
	MailServer.java
	```

	 files to match your email server and account information:

	- `SMTP_SERVER`: the address of your email server
	- `SMTP_PORT`: the port number for your email server
	- `USERNAME`: your email address
	- `PASSWORD`: your email password
	- `FROM_ADDRESS`: the email address you want to send from
	- `TO_ADDRESS`: the email address you want to send to

4. Run the `MailServer` class to start the server.

5. In a separate terminal, run the `MailClient` class to start the client.

6. A window with a form will appear in the client. Enter your email server and account information in the corresponding fields and click the "Send Email" button.

7. The server will receive the email and print a message in the terminal indicating that the email was received.

8. If the email is sent and received successfully, you should see a message indicating that the email was sent and received. If there is an error, it will be displayed in the client form and/or the server terminal.

## Additional notes

- This project is intended for testing purposes only and is not intended for use in a production environment.
- The `MailClient.java` and `MailServer.java` files are set up to use the SMTP login method for authenticating with the email server. If your server uses a different authentication method, you may need to modify the files accordingly.
