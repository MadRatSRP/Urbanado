package idd.urbanido.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuotesResponse(@field:SerializedName("id")
                          @field:Expose
                          val id: String,

                          @field:SerializedName("name")
                          @field:Expose
                          val name: String,

                          @field:SerializedName("curprice")
                          @field:Expose
                          val curprice: String?)