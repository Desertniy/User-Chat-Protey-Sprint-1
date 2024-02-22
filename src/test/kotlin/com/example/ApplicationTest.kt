package com.example

import com.example.models.User
import com.example.models.dto.user.UserCredential
import com.example.repository.UserRepo
import com.example.service.UserService
import com.example.utils.decodeFromBase64
import com.example.utils.encodeToBase64
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ApplicationTest {

    @MockK
    lateinit var userRepo: UserRepo

    @InjectMockKs
    lateinit var userService: UserService

    @BeforeEach
    fun info() = MockKAnnotations.init(this)

    @Test
    fun testEncodeBase64(){
        val data = "desertniy:1234"
        val result = encodeToBase64(data)
        assertEquals("ZGVzZXJ0bml5OjEyMzQ=", result)
    }

    @Test
    fun testDecodeBase64(){
        val data = "ZGVzZXJ0bml5OjEyMzQ="
        val result = decodeFromBase64(data)
        assertEquals("desertniy:1234", result)
    }

    @Test
    fun `test create user`() = runBlocking{
        val user = UserCredential(
            login = "desertniy123",
            password = "1234")
        val resultUser = User(
            idUser = 5,
            login = user.login,
            username = "Uknown",
            password = user.password
        )

        coEvery { userRepo.create(any()) } returns resultUser

        val result = userService.addUser(user)
        assertAll(
            {assertEquals(resultUser, result)},
            {assertNotNull(result)}
        )

        coVerify { userRepo.create(any()) }
    }
    /*@Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }*/
}
