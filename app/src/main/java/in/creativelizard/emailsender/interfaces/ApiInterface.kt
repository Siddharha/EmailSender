package `in`.creativelizard.emailsender.interfaces

import `in`.creativelizard.emailsender.models.OutputMail
import `in`.creativelizard.emailsender.models.SendMail
import `in`.creativelizard.emailsender.utill.ConstantClass
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST



/**
 * Created by siddhartha on 2/2/18.
 */
interface ApiInterface {

    @POST("sendMailToOthers.php")
    fun getMailResponse(@Body versionCheckInput: SendMail): Observable<OutputMail>

}