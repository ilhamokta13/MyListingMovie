@file:Suppress("DEPRECATION")

package com.example.mylistingmovie.viewmodel

import com.example.mylistingmovie.model.ListMovie
import com.example.mylistingmovie.network.RestfulApi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class HomeViewModelTest{
    lateinit var service : RestfulApi
    @Before
    fun setup(){
        //fungsi mock() untuk membuat objek palsu (mock) dari kelas yang ingin kita uji - RestfulApi
        service = mockk()
    }

    @Test
    fun testRetriveData():Unit = runBlocking {
        val responseRetrive = mockk<Call<ListMovie>>()

        every {
            runBlocking {
                service.allMoviesRecom()

            }
        } returns responseRetrive
        val result = service.allMoviesRecom()

        verify {
            runBlocking {
                service.allMoviesRecom()
            }
        }

        assertEquals(result,responseRetrive)

    }


}