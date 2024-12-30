# cs2030-discrete-event-simulator
(This is the bonus Part 4 of the project)

The discrete event simulator is a program designed to model a queue system in real life. Very much like the queuing system in a modern supermarket, this program models a Shop (the supermarket) and Operators (counters). There are two types of Operators, namely Servers (human counters) and SelfChecks (automated self-checkout counters). Servers, being human, will occassionally take breaks and rest after serving while SelfChecks can operate continually without resting in between. 

<img width="813" alt="image" src="https://github.com/user-attachments/assets/4c89d215-9002-4a6a-8c98-2385b2f3b6d4" />

Customer activity are modelled by events, more descriptively: 
- ArriveEvent: arises when a customer enters the system
- ServeEvent: arises when a customer starts getting served by an operator
- WaitAEvent: arises when a customer first has to wait in a queue
- WaitBEvent: arises when a customer has to continue waiting in a queue
- LeaveEvent: arises when a customer leaves the system due to no server or queue being available
- DoneEvent: arises when a customer is done being served by an operator
- RestEvent: arises when a human Server requires rest after serving a customer

The rough flow of events are as such: 
- (Scenario 1) Customer is served by Operator immediately: Customer enters and the system checks for an available Operator. An available Operator is found and the Customer is served immediately.
- (Scenario 2) Customer enters queue first: Customer enters and the system checks for an available Operator. No available Operators are found, thus the system checks for an available queue. An available queue is found and the Customer queues up. After some time, Customer is served.
- (Scenario 3) Customer does not get served: Customer enters and the system checks for an available Operator. None is found, thus the system checks for an available queue. All queues are at full capacity. Customer leaves.

The program is designed as closely to the modern supermarket queue system, as explained above. 
