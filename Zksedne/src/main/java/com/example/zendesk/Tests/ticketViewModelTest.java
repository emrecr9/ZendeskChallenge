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

    @Test
    public void TestProcessData()
    {
        ticketViewModel twm = new ticketViewModel();
        List<Tickets> tickets = new ArrayList<>();
        Map<Integer, Tickets> totalReqList = new HashMap<>();
        Zendesk zendesk = new Zendesk(tickets, new Meta("has", "after", "bef"));
        List<Tickets>requestList= twm.processData(zendesk, totalReqList);

        assertTrue(totalReqList.size() == requestList.size() );
    }
}