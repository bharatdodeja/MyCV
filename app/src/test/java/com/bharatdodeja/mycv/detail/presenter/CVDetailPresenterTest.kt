package com.bharatdodeja.mycv.detail.presenter

import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import com.bharatdodeja.mycv.detail.model.repository.CVRepository
import com.bharatdodeja.mycv.framework.rx.BaseSchedulerProvider
import com.bharatdodeja.mycv.framework.rx.DisposableManager
import com.bharatdodeja.mycv.framework.rx.ImmediateSchedulerProvider
import com.bharatdodeja.mycv.framework.util.GenericModel
import com.bharatdodeja.mycv.framework.util.NetworkUtils
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CVDetailPresenterTest {

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

    private val userId: String = "thomasdavis"

    private val cvDataModelString: String =
        "{\"basics\":{\"name\":\"Thomas Davis\",\"label\":\"Web Developer\",\"summary\":\"I’m a full stack web developer who loves working with open source technology. I work best at planning the architecture of web  applications and their development life cycles. I also love to get the community involved and have had much experience with building and organizing large open source groups. Specialties: React, Redux, Javascript - Full stack developer with lots of experience in lots of stuff\",\"email\":\"thomasalwyndavis@gmail.com\",\"website\":\"https://ajaxdavis.com\",\"location\":{\"city\":\"Melbourne\",\"countryCode\":\"AU\"},\"profiles\":[{\"username\":\"ajaxdavis\",\"url\":\"https://twitter.com/ajaxdavis\",\"network\":\"twitter\"},{\"url\":\"https://github.com/thomasdavis\",\"username\":\"thomasdavis\",\"network\":\"github\"}]},\"education\":[{\"endDate\":\"2009-12-12\",\"startDate\":\"2008-02-12\",\"area\":\"Software Engineering (incomplete)\",\"studyType\":\"Bachelors\",\"institution\":\"The University of Queensland\"}],\"references\":[{\"reference\":\"Thomas was hired as a lead developer and, upon the leaving of our co-founder took over as CTO of Earbits. Thomas is, hands down, one of those A Players you hear of companies dying to hire. He is incredibly smart, not just at code but about everything from classical music to Chinese language and culture. Thomas is great to work with and, as a well established contributor to open source projects and several successful ventures, commands the respect of engineers at all levels. I would suggest doing anything you can to have him on your team.\",\"name\":\"Joey Flores, Co-founder and CEO of Earbits, Inc.\"},{\"reference\":\"I've had the great pleasure of working with Thomas for the past three years at Earbits, and on a few side projects. Two years ago our CTO left on a moment's notice, Thomas saved our company by quickly stepping up to fill this role. He has been with our company through thick and thin and made serious personal sacrifices in order to help the company during tough times. He is a phenomenal hacker and a true team player. Highly recommended!\",\"name\":\"Yotam Rosenbaum, SVP of Operations, Earbits, Inc.\"},{\"reference\":\"Thomas is an extremely talented engineer with a very broad range of skills and experience. From being a thought leader in the front-end community via backbonetutorials.com and cdnjs.com, to designing and implementing the API for cdnjs.com, working with Thomas has been fantastic learning experience. Thomas is truly a full stack develop, and his work output is incredible. If there is any opportunity to work with Thomas, I take it. He is the definition of an A player.\",\"name\":\"Ryan Kirkman, Senior Software Engineer at Nerdwallet\"},{\"reference\":\"On Thomas Davis ... Hire this guy. Do not be fooled. Incredibly capable and fast. Plays well with others. Unbelievable at front-end work end programming but that is just the start. Visionary. Hire him before I do.\",\"name\":\"Greg Davis\"}],\"skills\":[{\"keywords\":[\"HTML\",\"CSS\",\"Javascript\",\"React\",\"Redux\"],\"name\":\"Frontend\"},{\"keywords\":[\"Node\",\"Ruby\",\"Postgres\",\"DynamoDb\"],\"name\":\"Backend\"}],\"work\":[{\"summary\":\"An Australian crypto currency exchange\",\"website\":\"https://blockbid.io\",\"company\":\"Blockbid\",\"position\":\"Developer\",\"startDate\":\"2018-03-01\"},{\"highlights\":[\"Worked with Postgres, Redis and Dynamodb for storage.\",\"Hosted on a mixture of Heroku Apps and EC2 servers.\",\"Caching by Fastly and Cloudflare\",\"Hybrid app supported on all platforms\"],\"summary\":\"Built a very large and complex React / Redux application. It works on all platforms and has IOS/Android builds due to it being a PWA. (wrapped it in React Native though only implementing a WebView)\",\"website\":\"https://listium.com\",\"company\":\"Listium\",\"position\":\"Developer\",\"startDate\":\"2016-01-01\",\"endDate\":\"2018-01-01\"},{\"highlights\":[\"Millions of sites use the CDN in production\",\"Larger market share than Yahoo's and Microsoft's content distribution network\",\"We serve hundreds of billions request a month\",\"Contains over 2000 popular Javascript libraries\",\"Millions of developers visit the site per year\"],\"summary\":\"Following Google’s CDN for jQuery, we decided to start a CDN for the less popular Javascript frameworks. The CDN is community moderated and open source on GitHub. We secured a partnership with Cloudflare who now supports the infrastructure.\",\"website\":\"http://www.cdnjs.com\",\"company\":\"Cdnjs\",\"position\":\"Co-Founder\",\"startDate\":\"2011-01-08\"},{\"highlights\":[\"Developed new tools for contacting congress\",\"Brainstormed campaign ideas to raise maximum awareness about issues\",\"Lots of social networking integration\"],\"summary\":\"Hired to take EFF's campaigning to the next level by building an action centre in Ruby on Rails. The action centre is built around some large open source tools that lower the barrier to entry when contacting congress.\",\"website\":\"http://www.eff.org\",\"company\":\"Electronic Frontier Foundation\",\"position\":\"Developer\",\"startDate\":\"2014-04-01\",\"endDate\":\"2016-01-01\"},{\"highlights\":[\"Managed a small team of developers and designers\",\"Built the entire frontend application with Backbone.js\",\"Transferred all of the infrastructure from Heroku to AWS\"],\"summary\":\"Started off as a front end developer but took on the role of CTO in early 2013. The application frontend is built with Javascript and organized as a single page application that talks to a collection of Rails web servers which are connected to MongoDB.\",\"website\":\"http://www.earbits.com\",\"company\":\"Earbits\",\"position\":\"CTO\",\"endDate\":\"2015-01-09\",\"startDate\":\"2013-03-08\"},{\"highlights\":[\"Over 1500 stars on Github\",\"Community developed themes\",\"50,000 views on the hosted service\"],\"summary\":\"JSON Resume is a community driven open source initiative to create a JSON based standard for resumes. There is no reason why there can't be a common standard for writing a resume that can be extended with an ecosystem of open source tools.\",\"website\":\"http://jsonresume.org\",\"company\":\"JSON Resume\",\"position\":\"Founder\",\"startDate\":\"2014-04-01\"},{\"highlights\":[\"Generated  37,000,000 banner views\",\"100, 000 phone calls to congress\",\"500, 000 emails\",\"250, 000 signatures\"],\"summary\":\"Worked on many politically charged campaigns against mass surveillance. Not only technically challenging work but also a lot of networking and getting my hands dirty with politics.    Our biggest project was \\\"TheDayWeFightBack\\\"..\",\"website\":\"http://www.taskforce.is\",\"company\":\"Taskforce.is\",\"position\":\"Developer\",\"startDate\":\"2013-06-01\",\"endDate\":\"2016-01-01\"},{\"highlights\":[\"The site and blog combined have managed to receive over 200,000 visitors in 2014.\"],\"summary\":\"An international directory of civilian drone / UAV operators for hire. Services include aerial photography, aerial video, mapping, surveying, precision agriculture, real estate photography, remote inspections and infrared imaging. Our plan is to be the place to go when looking for UAV/Drone services. The website is built in Backbone.js and API is built with Node.js and Postgres. \",\"website\":\"http://www.dronehire.org\",\"company\":\"Drone Hire\",\"position\":\"Co-Founder\",\"startDate\":\"2013-01-01\"},{\"highlights\":[\"Over two million unique visitors a year\",\"25,000+ ebook downloads\",\"280,000 Youtube views\"],\"summary\":\"I write tutorials and blog post regarding the popular Javascript framework Backbone.js. The tutorials cover a range of topics regarding front end development aimed at beginners, experts and anyone in between.\",\"website\":\"http://backbonetutorials.com\",\"company\":\"BackboneTutorials.com\",\"position\":\"Founder\",\"startDate\":\"2011-01-01\",\"endDate\":\"2014-01-01\"},{\"summary\":\"Ephox is a worldwide company who is heavily involved with the development of TinyMce and enterprise editors. My primary role included building front-end widgets and applications. Worked on a major product using Backbone.js as a base. Heavily involved in UI/UX design and wire-framing. Also spend a lot of time designing API specifications and documentation.\",\"website\":\"http://ephox.com\",\"company\":\"Ephox\",\"position\":\"Front-end Developer\",\"endDate\":\"2012-06-01\",\"startDate\":\"2011-01-01\"}],\"__v\":0}"

    private val cvDataModel = GenericModel.newInstance(cvDataModelString, CVDataModel::class.java)!!

    private val error = Throwable("SomeUnexpectedError")

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
        whenActiveNetworkAndRepositoryReturnsSuccess(userId, cvDataModel)

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        //Then network error is shown
        verify(view, never()).showNoNetworkError()
    }

    @Test
    fun getCVDetailCalled_repositoryCallbackIsCaptured() {

        //Given there is active network connection in device
        //and repository returns successful data
        whenActiveNetworkAndRepositoryReturnsSuccess(userId, cvDataModel)

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        //Then callback is captured
        verify(repository).getCV(userId)
    }

    @Test
    fun getCVDetailCalled_andSuccess_showCVDetailIntoView() {

        //Given there is active network connection in device
        //and repository returns successful data
        whenActiveNetworkAndRepositoryReturnsSuccess(userId, cvDataModel)

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
        whenActiveNetworkAndRepositoryReturnsError(userId, error)

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        // Then error is shown
        verify(view).showError(error)
    }

    @Test
    fun getCVDetail_progressIndicatorsShownAndHidden() {
        // Given there is active network connection in device
        // and repository returns successful data
        whenActiveNetworkAndRepositoryReturnsSuccess(userId, cvDataModel)

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
        whenActiveNetworkAndRepositoryReturnsError(userId, error)

        //When presenter is asked to get CV detail
        presenter.getCVDetail(userId)

        // Then progress indicator is shown
        verify(view).showLoading()

        // Then progress indicator is hidden
        verify(view).hideLoading()
    }

    private fun whenActiveNetworkAndRepositoryReturnsSuccess(userId: String, cvDataModel: CVDataModel) {
        `when`(networkUtils.isConnected()).then { true }
        `when`(repository.getCV(userId)).thenReturn(Flowable.just(cvDataModel))
    }

    private fun whenActiveNetworkAndRepositoryReturnsError(userId: String, error: Throwable) {
        `when`(networkUtils.isConnected()).then { true }
        `when`(repository.getCV(userId)).thenReturn(Flowable.error(error))
    }

}