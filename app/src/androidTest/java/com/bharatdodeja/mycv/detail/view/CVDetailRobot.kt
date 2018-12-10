package com.bharatdodeja.mycv.detail.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.bharatdodeja.mycv.R
import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import com.bharatdodeja.mycv.util.BaseRobot

/**
 * Robot class for [CVDetailActivity], separating HOW from WHAT of testing
 * by following robot pattern as test architecture.
 */
class CVDetailRobot : BaseRobot() {

    infix fun pullToRefresh(func: Result.() -> Unit): Result {
        onView(withId(R.id.swipeRefreshLayout))
            .perform(ViewActions.swipeDown())
        return Result().apply { func() }
    }

    class Result : BaseRobot() {
        fun isNetworkErrorShownWithRetry() {
            onView(withText(R.string.network_error))
                .check(matches(isDisplayed()))
            onView(withText(R.string.retry))
                .check(matches(isDisplayed()))
        }

        fun isCVDetailShown(cvDataModel: CVDataModel) {
            onView(withId(R.id.txtCVDetail))
                .check(matches(isDisplayed()))
        }

        fun isProgressShown() {
            onView(withId(R.id.swipeRefreshLayout))
                .check(ViewAssertions.matches(isDisplayed()))
        }
    }
}

/**
 * Extension function for building CVDetailRobot and asserting expected views are available
 */
fun cvDetailScreen(func: CVDetailRobot.() -> Unit): CVDetailRobot {

    //assert expected views are displayed
    onView(withId(R.id.txtCVDetail))
        .check(matches(isDisplayed()))

    return CVDetailRobot().apply { func() }
}
