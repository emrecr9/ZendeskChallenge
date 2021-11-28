package com.example.zendesk.DataModels;

import java.util.HashMap;
import java.util.Map;

public class TotalReqList
{
    private Map<Integer, Tickets> totalReqList = new HashMap<>();
    public TotalReqList() { }

    public Map<Integer, Tickets> getTotalReqList()
    { return totalReqList;}
}
