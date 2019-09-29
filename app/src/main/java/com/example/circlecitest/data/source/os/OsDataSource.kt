package com.example.circlecitest.data.source.os

interface OsDataSource {

    data class AppInfo(val applicationId: String, val className: String)

    fun isAvailable(applicationId: String, className: String): Boolean
    fun getInstalledApplications(): List<AppInfo>
}
