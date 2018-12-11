package com.bharatdodeja.mycv.detail.view

import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
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
    fun whenThereIsNoActiveNetwork_showNetworkError() {
        // This will work only when device is not connected to internet
        cvDetailScreen {

        } pullToRefresh {
            // uncomment below statement after disabling network in device to make test pass
            //isNetworkErrorShownWithRetry()
            screenshot("NetworkErrorMsgWithError")
        }
    }

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
}