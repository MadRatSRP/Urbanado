package idd.urbanido.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EventResponse(@field:SerializedName("discription")
                         @field:Expose
                         val discription: String,

                         @field:SerializedName("title")
                         @field:Expose
                         val title: String,

                         @field:SerializedName("address")
                         @field:Expose
                         val address: String,

                         @field:SerializedName("koord")
                         @field:Expose
                         val koord: String,

                         @field:SerializedName("date")
                         @field:Expose
                         val date: String,

                         @field:SerializedName("type")
                         @field:Expose
                         val type: String,

                         @field:SerializedName("picture")
                         @field:Expose
                         val picture: String)
