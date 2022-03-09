package com.example.module.notification.application

import com.example.module.notification.domain.Notifier
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class NotificationSenderTest {

    private val notifier: Notifier = mock()
    private val notificationSender = NotificationSender(notifier)

    @Test
    fun `verify invoke notifier when notify`() {
        runBlocking {
            //Given
            val text = stubNotificationText()
            val type = stubNotificationType()

            `when`(notificationSender.invoke(text, type))
                .thenReturn(Result.success("Mock sender"))

            //When
            val result = notificationSender(text, type)

            //Then
            assert(result.isSuccess)
            verify(notifier, times(1)).notify(text, type)
        }
    }
}