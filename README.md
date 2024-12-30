# cs2030-discrete-event-simulator
(This is the bonus Part 4 of the project)

The discrete event simulator is a program designed to model a queue system in real life. Very much like the queuing system in a modern supermarket, this program models a Shop (the supermarket) and Operators (counters). There are two types of Operators, namely Servers (human counters) and SelfChecks (automated self-checkout counters). 

<img width="813" alt="image" src="https://github.com/user-attachments/assets/4c89d215-9002-4a6a-8c98-2385b2f3b6d4" />

Customer activity are modelled by events, more descriptively: 
- ArriveEvent: arises when a customer enters the system
- ServeEvent: arises when a customer starts getting served by an operator
- WaitAEvent: arises when a customer first has to wait in a queue
- WaitBEvent: arises when a customer has to continue waiting in a queue
- LeaveEvent: arises when a customer leaves the system due to no server or queue being available
- DoneEvent: arises when a customer is done being served by an operator
- RestEvent: arises when a human Server requires rest after serving a customer
