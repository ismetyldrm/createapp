package com.example.diablo

import org.junit.Assert.assertEquals
import org.junit.Test



class CredentialsManagerTest {

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        val isEmailValid = credentialsManager.isEmailValid("")

        assertEquals(false, isEmailValid)
    }

}

