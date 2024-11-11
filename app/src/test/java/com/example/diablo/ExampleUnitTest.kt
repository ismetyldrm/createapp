package com.example.diablo


import com.example.diablo.CredentialsManager
import org.junit.Test


import org.junit.Assert.assertEquals

class CredentialsManagerTest1 {


    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        val isEmailValid = credentialsManager.isEmailValid("")

        assertEquals(false, isEmailValid)
    }


}
