package `in`.creativelizard.emailsender.models

/**
 * Created by siddhartha on 2/2/18.
 */
class SendMail {
    /**
     * data : {"name":"Siddhartha","to":"krittika1dey@gmail.com","from":"siddhartha.ee.13@gmail.com","subject":"Test Mail","body":"Ha ha ha."}
     */

    private var data: DataBean? = null

    fun getData(): DataBean? {
        return data
    }

    fun setData(data: DataBean) {
        this.data = data
    }

    class DataBean {
        /**
         * name : Siddhartha
         * to : krittika1dey@gmail.com
         * from : siddhartha.ee.13@gmail.com
         * subject : Test Mail
         * body : Ha ha ha.
         */

        var name: String? = null
        var to: String? = null
        var from: String? = null
        var subject: String? = null
        var body: String? = null
    }
}