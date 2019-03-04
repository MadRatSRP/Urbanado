package idd.urbanido.model.users

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import idd.urbanido.model.Users

data class UsersResponse(@field:SerializedName("users")
                         @field:Expose
                         val users: List<Users>)