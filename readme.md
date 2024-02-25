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

```> Nick joined
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