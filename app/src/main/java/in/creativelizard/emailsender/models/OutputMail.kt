package `in`.creativelizard.emailsender.models

/**
 * Created by siddhartha on 2/2/18.
 */
class OutputMail {
    /**
     * data : {"sucess":true,"msg":"Mail Send Sucessfully!"}
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
         * sucess : true
         * msg : Mail Send Sucessfully!
         */

        var isSucess: Boolean = false
        var msg: String? = null
    }
}