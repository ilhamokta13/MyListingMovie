package com.example.mylistingmovie.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@Suppress("DEPRECATION")
class ProfileFragmentTest{
    private lateinit var profileFragment: ProfileFragment

    @Mock
    private lateinit var mockedSharedPreferences: SharedPreferences

    @Mock
    private lateinit var mockedEditor: SharedPreferences.Editor

    @Mock
    private lateinit var mockedView: View

    @Mock
    private lateinit var mockedBundle: Bundle

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        profileFragment = ProfileFragment()
    }

    @After
    fun tearDown() {
    }


    @Test
    fun onViewCreated() {
        `when`(mockedView.context).thenReturn(mock(Context::class.java))
        `when`(mockedSharedPreferences.getString(eq("username"), anyString())).thenReturn("user123")
        `when`(mockedSharedPreferences.edit()).thenReturn(mockedEditor)

        val mockedContext = mock(Context::class.java)
        `when`(mockedContext.getSharedPreferences(eq("Register"), eq(Context.MODE_PRIVATE))).thenReturn(mockedSharedPreferences)

        val mockedBundle = mock(Bundle::class.java)
        `when`(mockedBundle.getString(eq("key"))).thenReturn("value") // Menambah pengaturan untuk mockedBundle

        profileFragment.onViewCreated(mockedView, mockedBundle)

        verify(mockedSharedPreferences).getString("username", "")
        verify(mockedEditor).putString("username", "user123")
        verify(mockedEditor).apply()
    }
}