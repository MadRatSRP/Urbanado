package idd.urbanido.model.registration

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import idd.urbanido.model.Users

data class RegistrationResponse(@field:SerializedName("users")
                                @field:Expose
                                val users: Users)