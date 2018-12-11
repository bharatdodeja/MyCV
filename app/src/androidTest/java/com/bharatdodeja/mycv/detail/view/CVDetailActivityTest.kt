package com.bharatdodeja.mycv.detail.view

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.bharatdodeja.mycv.detail.CVUITestDoubles
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CVDetailActivityTest : CVUITestDoubles() {

    /**
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(CVDetailActivity::class.java)

    @Test
    fun getCV_showsCVDetail() {
        cvDetailScreen {
            //no user input required
        } pullToRefresh {
            isCVDetailShown(cvDataModel)
            screenshot("CVDetailShown")
        }
    }

    @Test
    fun getCV_showsLoading_hidesLoading_afterSuccess() {
        cvDetailScreen {
            // no user input required
        } pullToRefresh {
            isProgressShown()
            screenshot("LoadingIndicator")
        }
    }

    @Test
    fun showNoNetworkError_showsSnackBarWithErrorMessageAndRetry() {
        cvDetailScreen (activityTestRule.activity) {
            // no user input required
        } showNoNetworkError {
            isNetworkErrorShownWithRetry()
            screenshot("NetworkErrorMsgWithError")
        }
    }

    @Test
    fun showNoNetworkError_retry_showsLoadingIndicator() {
        cvDetailScreen (activityTestRule.activity) {
            //no user input required
        } showNoNetworkErrorAndRetry {
            isProgressShown()
        }
    }
}