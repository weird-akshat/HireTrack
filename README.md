# HireTrack
This is an application built to automate strucutring and displaying placement information received from a college's whatsapp group.

Automation workflow
The workflow is, getting the information from whatsapp.
Reading(pdf,doc,ppt,sheeet,text) and storing it in database (source message).
The read message is then transformed to a structured format.
Then it is either stored in the database, or if it's a dependent object(like shortlists/notifcations/updates being linked to a job listing), then the job listing is found, and further actions is taken on it. 

There are 4 microservices made for each job. 
MessageListener- Sends whatever information is recieved to messageExtractor.
messageExtractor- Extractor takes out the message and converts it to a text format.
messageAnalyzer- Sends the text to an LLM to classify it and then structure it according to the object.
EntityManager- Stores the object in the database.

Message Listner Service:
Functionality :- Listens to whatsapp messages.
Implementation Details:
- Whenever a message comes, the service starts the time counter for 2 minutes, after which it sends the message if no new message interrupts during that time. If interrupted with another message, it accumulates and stores the message with the last one and resets the timer to 2 minutes again. (Possible infinite execution, have a 10 message limit here for accumulation). Once the timer is up, the message is sent to the [[Message Extractor]].
- Also whenever a new message arrives, a job is scheduled to check the message again after the editing time window is over. If the message is found to be changed, then it is sent to the message extractor to make the required changes.
- When a message gets deleted, check the timestamp of the deleted message and send it to the [[Message Extractor]] to delete. If multiple messages have the same timestamp, send those too with the request to filter in message extractor.

Message Extractor Service:
- Stores the message to the database and gets back the id (which will be later sent to [[Message Analyzer]] ). This is done to keep track of the source message.
- Reads all types of media and texts.
- Deletion request filters the source message to delete and makes an api call to delete all the objects created with that source message.
- Edit request uses the delete functionality to delete the objects, and then creates a new one entirely. (ToDo: Change the job listing id of all the other objects to this new object created.)

Message Anaylzer Service:
  - Classifies the received text with an LLM and creates the required object. (Essentially used for classification and converstion to structured output for an unstructured text input)
  - Calls on the entity manager to store the output.

Entity Manager:
  - Manages the database and provides the endpoints to delete messages to the listener service.
  - Stores the structured output in the database.
  - Links dependent objects (like job updates, job notifications, shortlists). Finds the job listing with the help of company name and the job role.
  - In case of job updates, the whole object is sent back to the message analyzer to update the joblisting.

Eureka Server:
- Service registry for all the services.

Job Management:
- Provides api to display all the information regarding the jobs. Basic CRUD.
- JWT Secured.
