package com.bharatdodeja.mycv.detail.model.repository

import com.bharatdodeja.mycv.detail.CVTestDoubles
import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import com.bharatdodeja.mycv.detail.model.repository.datasource.CVDataSource
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CVRepositoryTest : CVTestDoubles() {

    @Mock
    private lateinit var remoteDataSource: CVDataSource

    private lateinit var mTestSubscriber: TestSubscriber<CVDataModel>

    private lateinit var repository: CVRepository

    @Before
    fun setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        mTestSubscriber = TestSubscriber()

        //Initialise class under test
        repository = CVRepository(remoteDataSource)
    }

    @After
    fun destroy() {
        mTestSubscriber.dispose()
    }

    @Test
    fun `getCV dataSourceCallIsCaptured`() {
        // Given remote data source returns success
        givenDataSourceReturnsSuccess(userId, cvDataModel)

        // When getCV call is subscribed to data source
        repository.getCV(userId).subscribe(mTestSubscriber)

        // Then remote data source call is captured
        verify(remoteDataSource).getCV(userId)
    }

    @Test
    fun `getCV andThereIsError repositoryReturnsError`() {
        // Given remote data source returns success
        givenDataSourceReturnsError(userId, error)

        // When getCV call is subscribed to data source
        repository.getCV(userId).subscribe(mTestSubscriber)

        // Then observable emits error
        mTestSubscriber.assertError(error)
    }

    @Test
    fun `getCV andThereIsSuccess returnsExpectedResult`() {
        // Given remote data source returns success
        givenDataSourceReturnsSuccess(userId, cvDataModel)

        // When getCV call is subscribed to data source
        repository.getCV(userId).subscribe(mTestSubscriber)

        // Then observable emits success value
        mTestSubscriber.assertValue(cvDataModel)
    }

    private fun givenDataSourceReturnsSuccess(userId: String, cvDataModel: CVDataModel) {
        `when`(remoteDataSource.getCV(userId)).thenReturn(Flowable.just(cvDataModel))
    }

    private fun givenDataSourceReturnsError(userId: String, error: Throwable) {
        `when`(remoteDataSource.getCV(userId)).thenReturn(Flowable.error(error))
    }
}