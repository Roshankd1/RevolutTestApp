package com.application.revoluttestapp.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.application.revoluttestapp.R
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.Matchers.greaterThan
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.

        Thread.sleep(2000)
        onView(withId(R.id.recyclerViewContainer))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MainAdapter.RateViewHolder>(
                    ITEM_BELOW_THE_FOLD,
                    click()
                )
            )

        // Match the text in an item below the fold and check that it's displayed.
        val itemElementText = getApplicationContext<Context>().resources.getString(
            R.string.currency_inr
        )
        onView(withText(itemElementText)).check(matches(isDisplayed()))
    }

    companion object {
        private const val ITEM_BELOW_THE_FOLD = 15
    }

    @Rule
    @JvmField
    var rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun ensureRecyclerViewIsPresent() {
        val activity = rule.activity
        val viewById = activity.findViewById<RecyclerView>(R.id.recyclerViewContainer)
        assertThat(viewById, notNullValue())
        assertThat(viewById, instanceOf(RecyclerView::class.java))
        val recyclerView: RecyclerView = viewById
        val adapter = recyclerView.adapter
        assertThat(adapter, instanceOf(MainAdapter::class.java))
        if (adapter?.itemCount != 0) {
            assertThat(adapter!!.itemCount, greaterThan(10))
        }


    }
}


