package com.example.zendesk.Tests;

import com.example.zendesk.Views.TicketViewer;
import com.example.zendesk.DataModels.Zendesk;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TicketViewerTest {


    @Test
    public void TestReturnResponse()
    {
        //bad cursor test. the response body should be null
        Zendesk response = TicketViewer.returnResponse("https://zzcjen.zendesk.com/", "hello");
        assertTrue(response == null);

        //bad url test. the response body should be null
        response = TicketViewer.returnResponse("https://zzcjen.zendesk.co/", "");
        assertTrue(response == null);

        //testing for a good response with valid url and cursor. the response is good, hence there is a ticket list
        response = TicketViewer.returnResponse("https://zzcjen.zendesk.com/", "");
        assertTrue(response.getTicketsList().size() > 0);
    }

    @Test
    public void testMenu()
    {
       //testing for when an unavailable choice has been entered
       assertTrue(TicketViewer.Menu("bad input") == "\nYou have entered an invalid choice. Try again");
    }

}