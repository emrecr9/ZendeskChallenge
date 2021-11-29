package com.example.zendesk.Views;

import com.example.zendesk.DataModels.Tickets;
import com.example.zendesk.DataModels.TotalReqList;
import com.example.zendesk.DataModels.Zendesk;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class ticketViewModel
{
    private static TotalReqList totalReqList;
    public ticketViewModel() { totalReqList = new TotalReqList(); }

    //put the tickets if any in a separated list/map to be used later to diminsh network calls
    //and return the list of tickets present on the current page
 //   public List<Tickets>  processData(@NotNull Zendesk zdkData, Map<Integer, Tickets> totalReqList)
    public List<Tickets>  processData(@NotNull Zendesk zdkData)
    {
        for (Tickets r : zdkData.getTicketsList())
            totalReqList.getTotalReqList().put(Integer.parseInt(r.getId()),r);
        return zdkData.getTicketsList();
    }

    public Map<Integer, Tickets> returnTicketList()
    {
        return totalReqList.getTotalReqList();
    }
    public Tickets returnTicket (int id)
    {
        return totalReqList.getTotalReqList().get(id);
    }

    public boolean ifContainsTicket(int id)
    {
        if (totalReqList.getTotalReqList().containsKey(id))
            return true;
        else
            return false;
    }
}
