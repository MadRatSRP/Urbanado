package idd.urbanido.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuotesResponse(@field:SerializedName("name")
                          @field:Expose
                          val name: String,

                          @field:SerializedName("avprice")
                          @field:Expose
                          val price: String)