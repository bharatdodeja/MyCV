package com.bharatdodeja.mycv.detail.model.repository.datasource

import com.bharatdodeja.mycv.detail.CVTestDoubles
import com.bharatdodeja.mycv.detail.model.api.CVApiService
import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CVRemoteDataSourceTest : CVTestDoubles() {

    @Mock
    private lateinit var cvApiService: CVApiService

    private lateinit var mTestSubscriber: TestSubscriber<CVDataModel>

    private lateinit var remoteDataSource: CVRemoteDataSource

    @Before
    fun setUp() {

        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        mTestSubscriber = TestSubscriber()

        //Initialise class under test
        remoteDataSource = CVRemoteDataSource(cvApiService)
    }

    @After
    fun destroy() {
        mTestSubscriber.dispose()
    }

    @Test
    fun getCV_apiServiceCallIsCaptured() {
        // Given
        givenApiServiceReturnsSuccess(userId, cvDataModel)

        // When
        remoteDataSource.getCV(userId)

        // Then
        verify(cvApiService).getCV(userId)
    }

    @Test
    fun getCV_apiReturnsSuccess_resultIsSuccess() {
        // Given
        givenApiServiceReturnsSuccess(userId, cvDataModel)

        // When
        remoteDataSource.getCV(userId).subscribe(mTestSubscriber)

        // Then
        mTestSubscriber.assertValue(cvDataModel)
    }

    @Test
    fun getCV_apiReturnsError_resultIsError() {
        // Given
        givenApiServiceReturnsError(userId, error)

        // When
        remoteDataSource.getCV(userId).subscribe(mTestSubscriber)

        // Then
        mTestSubscriber.assertError(error)
    }

    @Test
    fun getCV_apiReturnsError_noValuesReturned() {
        // Given
        givenApiServiceReturnsError(userId, error)

        // When
        remoteDataSource.getCV(userId).subscribe(mTestSubscriber)

        // Then
        mTestSubscriber.assertNoValues()
    }

    private fun givenApiServiceReturnsSuccess(userId: String, cvDataModel: CVDataModel) {
        `when`(cvApiService.getCV(userId)).thenReturn(Flowable.just(cvDataModel))
    }

    private fun givenApiServiceReturnsError(userId: String, error: Throwable) {
        `when`(cvApiService.getCV(userId)).thenReturn(Flowable.error(error))
    }
}