**CQRS Project: 

https://www.youtube.com/watch?v=XolV-pKjVyA

***Relationship:

Commands initiate actions: They are sent to the system to request changes.

Aggregates handle commands: Commands are routed to the appropriate aggregate, which executes the business logic and generates events as a result.

Events capture state changes: After a successful command execution, events are emitted to notify other parts of the system about the changes.

Event handlers react to events: Components listen for relevant events and update their own state or perform additional actions based on the event information.


***Example Workflow:

Command Execution:
- A command is received by the system (e.g., CreateOrderCommand).
- The appropriate aggregate (OrderAggregate) is identified to handle the command.
- The command is validated and executed within the aggregate, potentially resulting in state changes.
- Events (OrderCreatedEvent) are emitted as a result of the command execution.

Event Handling:
- Event handlers (e.g., projections, denormalizers) listen for relevant events (e.g., OrderCreatedEvent).
- They update their own state or perform additional actions based on the event information.
- This can include updating read models, sending notifications, or triggering further commands.
