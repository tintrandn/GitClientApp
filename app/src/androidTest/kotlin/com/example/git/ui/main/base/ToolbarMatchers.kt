package com.example.git.ui.main.base

import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object ToolbarMatchers {

    fun withToolbarTitle(@StringRes stringId: Int): Matcher<View> {
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
            internal var wrappedMatcher: Matcher<View>? = null

            override fun describeTo(description: Description) {
                description.appendText("title from id: $stringId")
                if (wrappedMatcher != null) {
                    description.appendText(" ")
                    wrappedMatcher!!.describeTo(description)
                }
            }

            override fun matchesSafely(toolbar: Toolbar): Boolean {
                val actualString = toolbar.resources.getString(stringId)
                wrappedMatcher = withToolbarTitle(actualString)
                return wrappedMatcher!!.matches(toolbar)
            }
        }
    }

    fun withToolbarTitle(title: String?): Matcher<View> {
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
            internal var actualTitle: String? = null

            override fun describeTo(description: Description) {
                description.appendText("title with text: " + title!!)
                if (actualTitle != null) {
                    description.appendText("\n ----> But got: " + actualTitle!!)
                }
            }

            override fun matchesSafely(toolbar: Toolbar): Boolean {
                val charTitle = toolbar.title

                return (charTitle == null && title == null || charTitle!!.toString() == title) && isDisplayed().matches(toolbar)
            }
        }
    }
}
