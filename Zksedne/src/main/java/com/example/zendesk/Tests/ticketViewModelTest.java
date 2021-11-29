package com.example.zendesk.Tests;

import com.example.zendesk.DataModels.Meta;
import com.example.zendesk.DataModels.Tickets;
import com.example.zendesk.DataModels.Zendesk;
import com.example.zendesk.Views.ticketViewModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ticketViewModelTest {

    //Testing the process data function
    @Test
    public void TestProcessData()
    {
        ticketViewModel twm = new ticketViewModel();
        List<Tickets> tickets = new ArrayList<>();

        tickets.add(new Tickets("requester","assignee","described",
                "89","pending", "subject", "16-12-2018", "21-04-2019"));

        Zendesk zendesk = new Zendesk(tickets, new Meta("has", "after", "bef"));
        List<Tickets>requestList= twm.processData(zendesk);

        //if the function works well, the two lists should be different
        assertTrue(tickets.get(0).getId() == requestList.get(0).getId());
        assertTrue(tickets.size() == requestList.size());
    }

    //we put 1 ticket and test if the function performs as planned
    @Test
    public void TestifContainsTicket()
    {
        ticketViewModel twm = new ticketViewModel();

        twm.returnTicketList().put(89, new Tickets("requester","assignee","described",
                "89","pending", "subject", "16-12-2018", "21-04-2019"));

        assertTrue(twm.ifContainsTicket(89) == true);
        assertTrue(twm.ifContainsTicket(7) == false);
    }

    //testing for the function returnTicket.
    //if successfull, the tickets should be similar
    @Test
    public void TestreturnTicket()
    {
        ticketViewModel twm = new ticketViewModel();
        Tickets ticketA = new Tickets("requester","assignee","described",
                "89","pending", "subject", "16-12-2018", "21-04-2019");

        twm.returnTicketList().put(89, ticketA );

        Tickets ticketB = twm.returnTicket(89);

        assertTrue(ticketA.equals(ticketB));
    }
}