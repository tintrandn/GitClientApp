package com.example.git.ui.main

import android.content.Context
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.git.ui.main.mockconfig.MockResponseDispatcher
import com.example.git.ui.main.mockconfig.MockWebServerRobot
import com.example.git.ui.main.mockconfig.MockWebServerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val mockWebServerRule = MockWebServerRule(MockResponseDispatcher())

    private val mainActivityRobot = MainActivityRobot()

    private val mockWebServerRobot = MockWebServerRobot(mockWebServerRule)

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().context
        mockWebServerRobot.useDefaultDispatcher()
    }

    @Test
    fun searchUserStart_seesUserList() {
        mainActivityRobot
            .inputUserName("madaza13")
            .onSearchButtonClick()
            .seesUser(0, "madaza13", "1")
    }

}
