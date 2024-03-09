# Online Chat

## Stage 1/6: Simulating a simple server
### Description
Online chat is an application that lets you send and receive messages through the Internet. This project will show and explain the magic behind casually chatting with your online pals: you will work that magic yourself!

The first stage of the project is pretty simple. Imagine that some people are messaging each other in a group chat. Your program should receive raw messages from the standard input(i.e. System.in), process them, and output only the text type messages ignoring the technical messages like when someone joined/left the chat.

Each line of the input contains the person's name and the action this person performed. If the person performed the sent action, then the rest of the line after the keyword sent is the text of their message. Check out the output example for a better understanding!

### Objectives
In this stage, your program should satisfy the following conditions:

Every line of the input contains the person's name and the action this person performed (sent, joined, left, connected, and disconnected).
The person's name is a single word; there are no character restrictions for the person’s name.
The sent messages match the pattern "Name sent Message".
The messages in the output match the pattern "Name: Message".
All other types of messages are not shown in the output.
Your program will receive only correct raw messages.
Example
The greater-than symbol followed by a space > represents the user input. Note that it's not part of the input.

### Example

```
> Nick joined
> Nick sent Hello everyone!
Nick: Hello everyone!
> Nick sent Someone here?
Nick: Someone here?
> Josh joined
> Josh sent Hey Nick!
Josh: Hey Nick!
> Nick sent Hey Josh! I thought no one here...
Nick: Hey Josh! I thought no one was here...
> Josh sent Oh, I just joined, so..
Josh: Oh, I just joined, so..
> Nick sent OK
Nick: OK
> Nick disconnected
> Josh sent Are you here?
Josh: Are you here?
> Nick connected
> Nick sent Yes, just bad connection
Nick: Yes, just bad connection
> Josh sent Alright
Josh: Alright
> Nick left
> Josh left
```
## Stage 2/6: First contact
### Description
Chat programs usually have a client/server architecture. This means that there are a lot of clients and a single server, although, for chats with a really big audience, there can be more servers. All clients connect to this server and the server handles the clients' messages.

An important term to know in this stage is a socket. A socket is an interface to exchange data between different processes. These processes can be on the same computer or on different machines connected through the Internet. The clients in the client/server architecture have only one open socket; they are only connected to the server. However, the server has a lot of open sockets — one for each connected client.

In this stage, you will implement the simplest connection between one server and one client. The client and the server get a text from the standard input. Both the client and the server should send the received messages to each other through a socket and print them. When the client disconnects, the server should terminate.

To connect to the server, the client must know the address of the server. The address consists of two parts: IP address and port. The address of your computer is always "127.0.0.1", so you can use this address in your program. The port can be any number from 0 to 65535, but preferably greater than 1024. The IP address and port of the client should be the same as those of the server; otherwise, the client won't find the server, and the server won't know about the client.

To start, you need to import java.io.* and java.net.* to avoid importing problems.

Let's look at the client-side code:
```
String address = "127.0.0.1";
int port = 23456;
Socket socket = new Socket(InetAddress.getByName(address), port);
DataInputStream input = new DataInputStream(socket.getInputStream());
DataOutputStream output = new DataOutputStream(socket.getOutputStream());
```

The client creates a new socket. This means that they're trying to connect to the server. The successful creation of a socket means that the client found the server and connected to it.

After that, you can see the creation of the DataInputStream and DataOutputStream objects. These are the input and output connections to this server. If you expect to receive data from the server, you need to write input.readUTF(). This returns the String object that the server sent to the client. If you want to send data to the server, you need to write output.writeUTF(stringText) and this message will be sent to the server.

Now let's look at this server-side code:
```
String address = "127.0.0.1";
int port = 23456;
ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
Socket socket = server.accept();
DataInputStream input = new DataInputStream(socket.getInputStream());
DataOutputStream output  = new DataOutputStream(socket.getOutputStream());
```
The server creates a ServerSocket object that waits for client connections. When a client connects, the server.accept() method returns the Socket connection to this client. After that, you can see the creation of the DataInputStream and DataOutputStream objects, which are the input and output connections to this client, now from the server side. To receive data from the client, you need to write input.readUTF(). To send data to the client, write output.writeUTF(stringText).

Both the server and client should print messages through the socket; the client sends a message through the socket, the server reads it and prints through System.out, the server sends a message through the socket, the client reads it, and prints through System.out.

Finally, the programs can’t start simultaneously: they need to be connected correctly after they are prepared to work. The main method of the client should be located in the class chat.client.Client, and the server's main method should be located in the class chat.server.Server. Write your client code in the package chat.client and your server code in the package chat.server.

`input.readUTF()` and `socket.accept()` can hold a thread, be careful!

Be careful with closing resources. This may cause the unexpected behavior of your programs.

Also, the client should print Client started! and the server should print Server started! at the start of the program.

Before starting the tests make sure that the address is not taken by any other instance of program.
### Objectives
In this stage, your program should satisfy the following conditions:

* Implement the client and server architecture in different classes, namely; chat.client.Client and chat.server.Server. You need to create two different programs that work in coordination with each other.
* Implement sockets to connect the server and the client. The sockets for both of them should be created with the same IP address and port number.
* The Client program should print Client started! and the Server program should print Server started! when they start.
* Each program takes message input from System.in and sends them via the socket to the other program. The receiving program reads this message and prints it through System.out.
### Example
java client server socket communication

![2023-01-22 18-09-17.gif](readme-media%2F2023-01-22%2018-09-17.gif)