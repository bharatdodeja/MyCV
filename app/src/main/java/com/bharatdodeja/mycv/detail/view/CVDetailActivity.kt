package com.bharatdodeja.mycv.detail.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.text.scale
import com.bharatdodeja.mycv.R
import com.bharatdodeja.mycv.detail.CVDetailContract
import com.bharatdodeja.mycv.detail.di.Injection
import com.bharatdodeja.mycv.detail.model.data.CVDataModel
import com.bharatdodeja.mycv.framework.util.action
import com.bharatdodeja.mycv.framework.util.formatDate
import com.bharatdodeja.mycv.framework.util.snackBar
import kotlinx.android.synthetic.main.activity_cv_detail.*

class CVDetailActivity : AppCompatActivity(), CVDetailContract.View {

    //hardcoded user id, later it can be user input to make it dynamic
    private val userId: String = "thomasdavis"

    private lateinit var presenter: CVDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cv_detail)

        presenter = Injection.providePresenter(this)
        presenter.getCVDetail(userId)

        swipeRefreshLayout.setOnRefreshListener { presenter.getCVDetail(userId) }
    }

    override fun showCVDetail(cvDataModel: CVDataModel) {
        //Show CV detail in proper format on UI

        val user = cvDataModel.basics

        val name = user.name
        val nameParts = name.split(" ")
        val firstName = nameParts[0]
        val lastName = nameParts[1]

        txtCVDetail.text = buildSpannedString {

            color(getColor(R.color.black)) {
                scale(1.75f) {
                    bold {
                        append(firstName)
                    }
                    append(" $lastName")
                }
            }

            scale(1.25f) {
                append("\n")
                append(user.label)
            }
            append("\n\n\n")
            bold {
                append("email")
            }
            append("\n")
            append(user.email)
            append("\n\n")
            bold {
                append("website")
            }
            append("\n")
            append(user.website)

            user.profiles.forEach {
                append("\n\n")
                bold {
                    append(it.network)
                }
                append("\n")
                append(it.url)
            }

            append("\n\n\n")
            bold {
                color(getColor(R.color.black)) {
                    append("Summary".toUpperCase())
                }
            }
            append("\n\n")
            append(user.summary)


            //Skill
            append("\n\n\n")
            scale(1.25f) {
                bold {
                    color(getColor(R.color.blue)) {
                        append("Skills".toUpperCase())
                    }
                }
            }
            cvDataModel.skills.forEach { skill ->
                append("\n\n")

                color(getColor(R.color.black)) {
                    bold { append(skill.name) }
                }
                skill.keywords.forEach { name ->
                    append("\n")
                    append("- $name")
                }
            }

            append("\n\n\n")
            scale(1.25f) {
                bold {
                    color(getColor(R.color.blue)) {
                        append("Work".toUpperCase())
                    }
                }
            }
            cvDataModel.work.forEach { work ->
                append("\n\n")
                color(getColor(R.color.black)) {
                    bold { append(work.position) }
                }
                append("\n")
                bold { append(work.company) }
                append("\n")
                append(work.website)
                if (work.startDate.isNullOrEmpty().not() && work.endDate.isNullOrEmpty().not()) {
                    append("\n")
                    append("${work.startDate.formatDate()} - ${work.endDate.formatDate()}")
                }
                append("\n\n")
                append(work.summary)

                if (work.highlights.isNullOrEmpty().not()) {
                    append("\n\n")
                    bold {
                        append("Highlights")
                    }

                    work.highlights.forEach {
                        append("\n")
                        append("- $it")
                    }
                }
            }

            append("\n\n\n")
            scale(1.25f) {
                bold {
                    color(getColor(R.color.blue)) {
                        append("Education".toUpperCase())
                    }
                }
            }
            cvDataModel.education.forEach { education ->
                append("\n\n")
                color(getColor(R.color.black)) {
                    bold { append(education.institution) }
                }
                append("\n")
                scale(.75f) {
                    append("${education.startDate.formatDate()} - ${education.endDate.formatDate()}")
                }
                append("\n")
                color(getColor(R.color.black)) {
                    append(education.area)
                }
                append("\n")
                append(education.studyType)
            }
        }
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
        txtCVDetail.text = ""
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showError(error: Throwable) {
        hideLoading()
        txtCVDetail.snackBar(error.toString()) {
            action(R.string.retry) { presenter.getCVDetail(userId) }
        }
    }

    override fun showNoNetworkError() {
        txtCVDetail.snackBar(R.string.network_error) {
            action(R.string.retry) { presenter.getCVDetail(userId) }
        }
    }
}