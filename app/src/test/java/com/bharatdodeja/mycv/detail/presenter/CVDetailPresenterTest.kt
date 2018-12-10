package com.bharatdodeja.mycv.detail.presenter

import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.CVTestDoubles
import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import com.bharatdodeja.mycv.detail.model.repository.CVRepository
import com.bharatdodeja.mycv.framework.rx.BaseSchedulerProvider
import com.bharatdodeja.mycv.framework.rx.DisposableManager
import com.bharatdodeja.mycv.framework.rx.ImmediateSchedulerProvider
import com.bharatdodeja.mycv.framework.util.NetworkUtils
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CVDetailPresenterTest : CVTestDoubles() {

    private lateinit var presenter: CVDetailPresenter

    @Mock
    private lateinit var view: CVDetailContract.View

    @Mock
    private lateinit var repository: CVRepository

    @Mock
    private lateinit var disposableManager: DisposableManager

    @Mock
    private lateinit var networkUtils: NetworkUtils

    private lateinit var schedulerProvider: BaseSchedulerProvider

    @Before
    fun setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        // Make the sure that all schedulers are immediate.
        schedulerProvider = ImmediateSchedulerProvider()

        //Initialise class under test
        presenter = CVDetailPresenter(view, repository, disposableManager, networkUtils, schedulerProvider)
    }

    @Test
    fun ifNetworkNotConnected_showNetworkError() {
        //Given there is no network connection in device
        `when`(networkUtils.isConnected()).then { false }

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        //Then network error is shown
        verify(view).showNoNetworkError()
    }

    @Test
    fun ifNetworkConnected_doNotShowNetworkError() {
        //Given there is no network connection in device
        givenActiveNetworkAndRepositoryReturnsSuccess(userId, cvDataModel)

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        //Then network error is shown
        verify(view, never()).showNoNetworkError()
    }

    @Test
    fun getCVDetailCalled_repositoryCallbackIsCaptured() {

        //Given there is active network connection in device
        //and repository returns successful data
        givenActiveNetworkAndRepositoryReturnsSuccess(userId, cvDataModel)

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        //Then callback is captured
        verify(repository).getCV(userId)
    }

    @Test
    fun getCVDetailCalled_andSuccess_showCVDetailIntoView() {

        //Given there is active network connection in device
        //and repository returns successful data
        givenActiveNetworkAndRepositoryReturnsSuccess(userId, cvDataModel)

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        //Then callback is captured
        verify(repository).getCV(userId)

        // Then CV detail is shown
        verify(view).showCVDetail(cvDataModel)
    }

    @Test
    fun getCVDetailCalled_andError_showErrorIntoView() {
        //Given there is active network connection in device
        //and repository gives error
        givenActiveNetworkAndRepositoryReturnsError(userId, error)

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        // Then error is shown
        verify(view).showError(error)
    }

    @Test
    fun getCVDetail_progressIndicatorsShownAndHidden() {
        // Given there is active network connection in device
        // and repository returns successful data
        givenActiveNetworkAndRepositoryReturnsSuccess(userId, cvDataModel)

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        // Then progress indicator is shown
        verify(view).showLoading()

        // Then progress indicator is hidden
        verify(view).hideLoading()
    }

    @Test
    fun getCVDetail_AndThereIsError_progressIndicatorsShownAndHidden() {
        //Given there is active network connection in device
        //and repository gives error
        givenActiveNetworkAndRepositoryReturnsError(userId, error)

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        // Then progress indicator is shown
        verify(view).showLoading()

        // Then progress indicator is hidden
        verify(view).hideLoading()
    }

    private fun givenActiveNetworkAndRepositoryReturnsSuccess(userId: String, cvDataModel: CVDataModel) {
        `when`(networkUtils.isConnected()).then { true }
        `when`(repository.getCV(userId)).thenReturn(Flowable.just(cvDataModel))
    }

    private fun givenActiveNetworkAndRepositoryReturnsError(userId: String, error: Throwable) {
        `when`(networkUtils.isConnected()).then { true }
        `when`(repository.getCV(userId)).thenReturn(Flowable.error(error))
    }

}