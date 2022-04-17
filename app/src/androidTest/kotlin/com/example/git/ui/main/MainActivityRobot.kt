package com.example.git.ui.main

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.git.ui.main.base.RecyclerViewTestUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import com.example.git.R

import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf


class MainActivityRobot {

    fun seesUser(position: Int, nameText: String, score: String): MainActivityRobot {
        scrollToListPosition(position)
        onView(atPositionOnView(position, R.id.user_name))
            .check(matches(CoreMatchers.allOf(isDisplayed(), withText(nameText))))
        onView(atPositionOnView(position, R.id.user_score))
            .check(matches(CoreMatchers.allOf(isDisplayed(), withText(score))))

        return this
    }

    fun inputUserName(userName: String): MainActivityRobot {
        onView(withId(R.id.search_view))
            .perform(typeSearchViewText(userName))
        return this
    }

    fun onSearchButtonClick(): MainActivityRobot {
        onView(withId(R.id.btn_search)).perform(click())
        return this
    }

    private fun scrollToListPosition(position: Int) {
        onView(withId(R.id.user_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
    }

    private fun atPositionOnView(
        position: Int,
        viewId: Int
    ): Matcher<View> {
        return RecyclerViewTestUtils.withRecyclerView(R.id.user_list)
            .atPositionOnView(position, viewId)
    }

    private fun typeSearchViewText(text: String?): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change view text"
            }

            override fun perform(uiController: UiController?, view: View) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }
}
