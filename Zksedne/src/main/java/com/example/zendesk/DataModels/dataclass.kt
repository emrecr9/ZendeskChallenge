package com.example.zendesk.DataModels


import com.google.gson.annotations.SerializedName



data class Zendesk (
        @SerializedName("tickets")
        val ticketsList: List<Tickets>,

        @SerializedName("meta")
        val meta: Meta
)

data class Tickets (
        @SerializedName("requester_id")
        val requester_id: String,
        @SerializedName("assignee_id")
        val assignee_id: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("subject")
        val subject: String,
        @SerializedName("created_at")
        val created_at: String,
        @SerializedName("updated_at")
        val updated_at: String

)

data class Meta(
        @SerializedName("has_more")
        val has_more: String,
        @SerializedName("after_cursor")
        val after_cursor: String,
        @SerializedName("before_cursor")
        val before_cursor: String
)