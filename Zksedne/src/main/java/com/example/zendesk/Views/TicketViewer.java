package com.example.zendesk.Views;

import com.example.zendesk.DataModels.Tickets;
import com.example.zendesk.DataModels.Zendesk;
import com.example.zendesk.Objects.Retro;
import com.example.zendesk.DataModels.TotalReqList;
import com.example.zendesk.Objects.ZendeskAPI;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Response;

public class TicketViewer
{
    private static TotalReqList totalReqList;
    private static int pageNumber;
    private static boolean callSuccessfull;
    private static Scanner scan;

    protected TicketViewer()
    {
        //we initialize all the global variables that will be used
        totalReqList = new TotalReqList();
        scan= new Scanner(System.in);
        callSuccessfull = false;
    }

    public static void main (String [] args)
    {
        new TicketViewer();
        String input = "";

        //Displays the menu as long as the user wants to interact....
        while (!input.equals("3"))
        {
            printMenu();
            input = scan.nextLine();
            input.trim();

        //we launch the menu
            System.out.println(Menu(input));
        }
        System.out.println("\nExiting the viewer.......");
    }

    //This function only prints the menu. nothing else
    public static void printMenu()
    {
        System.out.println("\tWelcome to the Tickect Viewer");
        System.out.println("\t\t-----menu----\n");
        System.out.println("\tEnter your choice:");
        System.out.println("\t1. View all tickets");
        System.out.println("\t2. View a ticket");
        System.out.println("\t3. Exit");
    }

    //this funtion displays the choice menu to the user
    public static String Menu(String input)
    {
        String message = "";//the message to be displayed to the user

            //if the user wants to see all the tickets
            if (input.equals("1"))
            {
                //if the call has not been succesfull, we try to collect the tickets
                if (!callSuccessfull)
                    collectTickets(1);
                else//call successfull -> we can print the tickets
                    DisplayTickets(totalReqList.getTotalReqList());

                message = "\nAll tickets have been successfully printed\n";
            }
            else if (input.equals("2"))//if the user wants to see one ticket
            {
                message = DisplayATicket();
            }
            else if (!input.equals("3"))
               message = "\nYou have entered an invalid choice. Try again";


        return message;
    }

    //this function is called when the user wishes to see a ticket only
    protected static String DisplayATicket()
    {
        int ticketID;
        String message = "";
        boolean playLoop = false;

        //while a ticket has not been displayed and the user wants to display one
        while (!playLoop)
        {
            //if the call to the server has not been made, try to collect tickets from the server
            if (!callSuccessfull)
            {
                System.out.println("\n\tConnecting to the server....");
                collectTickets(0);
            }

            System.out.println("\n\tEnter the ticket number or the value -1 to return to main menu: ");
            String answer = scan.nextLine().trim();

            try
            {
                ticketID = Integer.parseInt(answer);

                // if the ticket number is valid, print it
                if (totalReqList.getTotalReqList().containsKey(ticketID))
                {
                    PrintTicketDetails(ticketID);
                    message = "\n\tThe ticket "+ ticketID + " has been printed\n";
                }
                else if (ticketID != -1)
                {
                    //if the ticket number is not valid and the user does not want to exit
                    System.out.println("\n\tinvalid ticket ID number. Try again");
                }
                else if (ticketID == -1)// if the user wishes to exit
                {
                    playLoop = true;
                    message = "\nExiting...\n";
                }
            } catch (NumberFormatException e)
            {
                //if the input is not a digit
                System.out.println("\nYou have entered an invalid choice. Try again");
            }
        }
        return message;
    }

    //this function will intent to collect tickets from the zendesk API page by page
    // and print one page at the time if the agent desires the see all the tickets (code  = 1)
    protected static void collectTickets(int code)
    {
        String cursor = "";//the page cursor
        boolean hasMore = true;//true if there are more pages to display
        pageNumber = 1;

        //as long as there is a page to cover, connect to the server
        while (hasMore)
        {
            String baseUrl = "https://zzcjen.zendesk.com/";

            //collect the response
           Zendesk response = returnResponse(baseUrl , cursor);

           //if the response is successfull
           if (response != null)
           {
               ticketViewModel ticketViewModel = new ticketViewModel();

               //send the response to the function for processing to retrieve the list to print
               List<Tickets> requestList = ticketViewModel.processData(response, totalReqList.getTotalReqList());

               //if user wants to see all the tickets and they have  not been collected from the API,
               //we print them one page of 25 at the time
               if (code == 1)
               {
                   System.out.println("\n----Page " + (pageNumber - 1));
                   DisplayTickets(requestList);
               }

               cursor = response.getMeta().getAfter_cursor();//the cursor for the next page
               hasMore =  Boolean.parseBoolean(response.getMeta().getHas_more());//the hasMore value
               callSuccessfull = true;
           }
           else
           {
               //if the response is not successfull, we stop the loop. error message will be displayed
               hasMore = false;
               System.out.println("\nThe connection to the server was not successfull");
               System.out.println("\nThere may be a problem with the authorization details");
           }
        }
    }

    //This function returns perform the call to the Zendesk API and returns a reponse body
    //if the call is successfull or a null value if not successfull
    public static Zendesk returnResponse(String baseUrl, String cursor)
    {
        Zendesk body = null;

        //initialization of the retrofit instance that will help connect to the API
        ZendeskAPI zendesk = Retro.INSTANCE.provideRetrofit(baseUrl).create(ZendeskAPI.class);

        //the function that performs the call to the server
        Call<Zendesk> call =  zendesk.getZendeskData(cursor);

            try
            {
                Response<Zendesk> response = call.execute();//the execution of the call
                body = response.body();//store the body

                pageNumber++;
            } catch (IOException e)
            {
                System.out.println("\nUnable to Connect to the server");
            }
        return body;
    }

    //this function prints the mmost of the details concerning a unique ticket
    protected static void PrintTicketDetails(int id)
    {
        //retrieves the ticket to be displayed
        Tickets ticket = totalReqList.getTotalReqList().get(id);

        System.out.println("\tTicket " + ticket.getId() + " - Subject: \"" + ticket.getSubject() +  "\"" + "\t Status: " + ticket.getStatus()
                + "\tCreated on " + ticket.getCreated_at() + " by " + ticket.getRequester_id());
        System.out.println("\n\tDescription");
        System.out.println("\"" + ticket.getDescription()+ "\"");
 //       System.out.println("\tCreated on " + ticket.getCreated_at() + " by " + ticket.getRequester_id());
    }

    //This function prints a list of tickets. Only the ticket number and subject are displayed for each
    //ticket. the user can decide to view a ticket if there is a need to see more details
    protected static void DisplayTickets(List<Tickets> ticketList)
    {
        if (ticketList.size() > 0)
        {
            for (Tickets r : ticketList)
                System.out.println("\tTicket " + r.getId() + " - Subject: \"" + r.getSubject() + "\"");
        }
        else
            System.out.println("\tThere are no tickets to display");
    }

    //This function is a overloaded version of the previous one and does exactly the same thing
    protected static void DisplayTickets(Map<Integer, Tickets> ticketList)
    {
        if (ticketList.size() > 0)
        {
            for (Tickets r : ticketList.values())
                System.out.println("\tTicket " + r.getId() + " - Subject: \"" + r.getSubject() + "\"");
        }
        else
            System.out.println("\tThere are no tickets to display");
    }
}