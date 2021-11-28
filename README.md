# ZendeskChallenge
 
#Installation

In the folder "Jar File to be run", there is a jar file that can be run in the command line on windows by going into the folder where the jar lies
and writing the following command "java -jar TicketViewer.jar".

However, this jar will not run as it needs credentials put at the right place in order to work.
Since the tickets owned by the agent, i decided to implement Basic authentification

Since the requirements explicitly said not to post credentials and not to add an extra feature (like a login screen), the way to make it work is :
 - open the project (Android studio would be the best software)
 - go to "/Zksedne/src/main/java/com/example/zendesk/Objects/APIClient.kt" 
 - where it is written: "HERE GOES THE {email_address}:{password} OF THE AGENT IN BASEcode64", the "agent" has to put is email address and password in basecode64
 - go to "/Zksedne/src/main/java/com/example/zendesk/Views/TicketViewer.java"
 - in function/method "collectTickets", replace the baseUrl with the agent's url.
 - Recreate a jar file, then it will be runnable using CLI :
      * In Android studio, go to "Zksedne/build.gradle" and run the task 'customFatJar'. that will create the runnable jar file in "Zksedne/build/libs"

#Utilization

In the CLI, the menu is very clear and the user just has to follow the instructions on the screen.
the user can see all tickets by pressing 1, view a desired ticket by pressing 2 and exit by pressing 3.

  * when a user wants to see all the tickets, he presses 1 and the are displayed 25 at the time until the are all visible. only the Id and subject are visible
  * if the user wants more details, he can go to the option 2 
  * when the user wants to see a particular ticket, he must know the ticket id as it is required. the user can see the ticket id when displaying all the tickets
additionally, the menu will ask the user if he/she wants to see a ticket until he says no by entering "-1". he will then be taken to the main menu
  * if the request is not successfull, a message will be sent to the screen.
  * if the response is successfull but there are no tickets, a message will be sent to the screen


#Methodology
I developped the tickect viewer on Android Studio using Java and Kotlin. I used external libraries like Retrofit and okhttp3 to allow connection to the API.
