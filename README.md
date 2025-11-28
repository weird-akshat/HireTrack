# HireTrack

**HireTrack** is an automated application designed to streamline the structuring and displaying of placement information received via a college WhatsApp group.

## Automation Workflow

1.  **Ingestion:** The system listens for and captures information from WhatsApp.
2.  **Extraction:** It reads various formats (PDF, DOC, PPT, Sheets, Text) and stores the raw source message in the database.
3.  **Transformation:** The raw message is converted into a structured text format.
4.  **Action:** The structured data is either:
    * Stored directly in the database.
    * Linked to an existing Job Listing (if it is a dependent object like a shortlist, notification, or update).

---

## Microservices Architecture

The system is composed of four primary microservices per job, plus management services.


### 1. Message Listener Service
**Functionality:** Actively listens to WhatsApp messages.

**Implementation Details:**
* **Accumulation Logic:** When a message arrives, a 2-minute timer starts.
    * If no new messages arrive, the message is sent.
    * If interrupted by a new message, the text is accumulated with the previous one, and the timer resets to 2 minutes.
    * *Constraint:* There is a **10-message limit** for accumulation to prevent infinite execution.
    * Once the timer expires, the payload is sent to the **Message Extractor**.
* **Edit Handling:** When a new message arrives, a job is scheduled to re-check the message after the editing time window closes. If changes are detected, the updated message is sent to the Message Extractor.
* **Deletion Handling:** If a message is deleted, the service checks the timestamp. It sends a request to the **Message Extractor** to delete objects matching that timestamp.

### 2. Message Extractor Service
**Functionality:** Processes raw input and manages source data.

* **Source Tracking:** Stores the raw message in the database and retrieves an ID to maintain a link to the source. This ID is passed to the **Message Analyzer**.
* **Media Support:** Capable of reading all types of media and text files.
* **Deletion Requests:** Filters the source message by timestamp and triggers an API call to delete all objects associated with that specific source message.
* **Edit Requests:** Currently uses the delete functionality to remove old objects and creates entirely new ones.
    * *ToDo:* Update the logic so that the `job_listing_id` of dependent objects acts as a foreign key to the newly created object.

### 3. Message Analyzer Service
**Functionality:** Intelligent classification and structuring.

* **LLM Integration:** Sends the extracted text to a Large Language Model (LLM).
* **Classification:** Classifies the intent of the message (e.g., New Job, Update, Shortlist).
* **Structuring:** Converts unstructured text input into a defined JSON object.
* **Handoff:** Calls the **Entity Manager** to store the result.

### 4. Entity Manager Service
**Functionality:** Database management and object linking.

* **Storage:** Persists the structured output into the database.
* **Dependency Linking:** Links dependent objects (Job Updates, Notifications, Shortlists) to the parent Job Listing by matching the **Company Name** and **Job Role**.
* **Update Handling:** In the case of a "Job Update," the full object is sent back to the **Message Analyzer** to modify the existing Job Listing.
* **Management:** Provides endpoints for the Listener Service to request message deletions.

---

## Infrastructure & Management

### Eureka Server
Acts as the **Service Registry** (Discovery Server) for all microservices, ensuring they can communicate with each other dynamically.

### Job Management Service
* **API:** Provides endpoints to display all job-related information.
* **Operations:** Handles basic CRUD (Create, Read, Update, Delete) operations.
* **Security:** Secured using **JWT (JSON Web Tokens)**.
