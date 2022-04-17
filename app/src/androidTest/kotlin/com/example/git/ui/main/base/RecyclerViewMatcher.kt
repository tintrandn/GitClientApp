package com.example.git.ui.main.base

import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            var resources: Resources? = null

            override fun describeTo(description: Description) {
                var idDescription = recyclerViewId.toString()
                if (this.resources != null) {
                    idDescription = try {
                        this.resources!!.getResourceName(recyclerViewId)
                    } catch (var4: NotFoundException) {
                        String.format("%s (resource name not found)", recyclerViewId)
                    }

                }

                description.appendText("with id: $idDescription")
            }

            public override fun matchesSafely(view: View): Boolean {

                this.resources = view.resources

                val recyclerView: RecyclerView =
                    view.rootView.findViewById<View>(recyclerViewId) as RecyclerView
                if (recyclerView.id != recyclerViewId || null == recyclerView.findViewHolderForAdapterPosition(
                        position
                    )
                ) {
                    return false
                }

                val childView = recyclerView.findViewHolderForAdapterPosition(position)!!.itemView

                return if (targetViewId == -1) {
                    view === childView
                } else {
                    val targetView = childView.findViewById<View>(targetViewId)
                    view === targetView
                }
            }
        }
    }
}
