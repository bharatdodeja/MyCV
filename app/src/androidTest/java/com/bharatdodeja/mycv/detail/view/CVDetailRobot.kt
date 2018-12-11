package com.bharatdodeja.mycv.detail.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.bharatdodeja.mycv.R
import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import com.bharatdodeja.mycv.util.BaseRobot

/**
 * Robot class for [CVDetailActivity], separating HOW from WHAT of testing
 * by following robot pattern as test architecture.
 */
class CVDetailRobot(private val activity: CVDetailActivity?) : BaseRobot() {

    infix fun pullToRefresh(func: Result.() -> Unit): Result {
        onView(withId(R.id.swipeRefreshLayout))
            .perform(ViewActions.swipeDown())
        return Result().apply { func() }
    }

    infix fun showNoNetworkError(func: Result.() -> Unit): Result {
        activity?.showNoNetworkError()
        return Result().apply { func() }
    }

    infix fun showNoNetworkErrorAndRetry(func: Result.() -> Unit): Result {
        activity?.showNoNetworkError()
        onView(withText(R.string.retry))
            .perform(ViewActions.click())
        return Result().apply { func() }
    }

    class Result : BaseRobot() {
        fun isNetworkErrorShownWithRetry() {
            onView(withText(R.string.network_error))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            onView(withText(R.string.retry))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
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

    return CVDetailRobot(null).apply { func() }
}

fun cvDetailScreen(activity: CVDetailActivity?, func: CVDetailRobot.() -> Unit): CVDetailRobot {

    //assert expected views are displayed
    onView(withId(R.id.txtCVDetail))
        .check(matches(isDisplayed()))

    return CVDetailRobot(activity).apply { func() }
}
