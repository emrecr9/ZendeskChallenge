# ZendeskChallenge
 
#Installation

In the folder "Jar File to be run", there is a jar file that can be run in the command line on windows by going into the folder where the jar lies
and writing the following command "java -jar TicketViewer.jar".

However, this jar will not run as it needs credentials put at the right place in order to work.

Since the requirements explicitly said not to post credentials and not to add an extra feature (like a login screen), the way to make it work is :
 - open the project (Android studio would be the best software)
 - go to "/Zksedne/src/main/java/com/example/zendesk/Objects/APIClient.kt" 
 - where it is written: "HERE GOES THE {email_address}:{password} OF THE AGENT IN BASEcode64", the "agent" has to put is email address and password in basecode64
 - Recreate a jar file, then it will be runnable using CLI :
      * In Android studio, go to "Zksedne/build.gradle" and run the task 'customFatJar'. that will create the runnable jar file in "Zksedne/build/libs"

in the CLI, the menu is very clear and the user just has to follow the instructions on the screen.

#Methodology
I developped the tickect viewer on Android Studio using Java and Kotlin. I used external libraries like Retrofit and okhttp3 to allow connection to the API.
