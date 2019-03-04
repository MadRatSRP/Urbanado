package idd.urbanido.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Users(@field:SerializedName("name")
                @field:Expose
                val name: String,
                 @field:SerializedName("email")
                @field:Expose
                val email: String,
                 @field:SerializedName("password")
                @field:Expose
                val password: String,
                 @field:SerializedName("password_confirm")
                @field:Expose
                val password_confirm: String,
                 @field:SerializedName("phone")
                @field:Expose
                val phone: String)