package `in`.creativelizard.emailsender.db

/**
 * Created by siddhartha on 11/1/18.
 */
 class DatabaseConstant {
    companion object {
        val DATABASE_NAME: String = "EmailSender"
        val DATABASE_VERSION: Int = 1
        val TABLE_EMAIL_SENDER = "emailTemplate"
        val COLUMN_ID = "id"
        val COLUMN_SENDER_TITLE = "title"
        val COLUMN_SENDER_DESCRIPTION = "descriotion"
    }
}