package com.external.gmail

class GmailClient(
    private val user: String,
    private val password: String
) {

    fun send(email: Email) {
        println(
            """
            Subject: ${email.subject}
            Body: ${email.body}  
            """
        )
    }
}