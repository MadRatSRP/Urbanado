package idd.urbanido.model.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileResponse(@field:SerializedName("name")
                          @field:Expose
                          val name: String,

                           @field:SerializedName("email")
                          @field:Expose
                          val email: String,

                           @field:SerializedName("password_digest")
                          @field:Expose
                          val password: String,

                           @field:SerializedName("phone")
                          @field:Expose
                          val phone: String)