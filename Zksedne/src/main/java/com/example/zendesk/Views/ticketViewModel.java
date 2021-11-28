package com.example.zendesk.Views;

import com.example.zendesk.DataModels.Tickets;
import com.example.zendesk.DataModels.Zendesk;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class ticketViewModel
{
    public ticketViewModel() { }

    //put the tickets if any in a separated list/map to be used later to diminsh network calls
    //and return the list of tickets present on the current page
    public List<Tickets>  processData(@NotNull Zendesk zdkData, Map<Integer, Tickets> totalReqList)
    {
        for (Tickets r : zdkData.getTicketsList())
            totalReqList.put(Integer.parseInt(r.getId()),r);
        return zdkData.getTicketsList();
    }
}
