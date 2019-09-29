package com.example.circlecitest.data.source.os

import android.content.Intent
import android.content.pm.PackageManager
import com.example.circlecitest.MyApplication

class OsDataSourceImpl private constructor(
        private val app: MyApplication
) : OsDataSource {

    override fun isInstalled(applicationId: String): Boolean {
        try {
            app.packageManager.getApplicationInfo(applicationId, 0)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }

    override fun getInstalledApplications(): List<OsDataSource.AppInfo> {
        val intent = Intent(Intent.ACTION_MAIN).also {
            it.addCategory(Intent.CATEGORY_LAUNCHER)
        }
        return app.packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL)
                .mapNotNull { it.activityInfo }
                .filter { it.packageName != app.packageName }
                .map { OsDataSource.AppInfo(it.packageName, it.name) }
                .toList()
    }

    companion object {

        @Volatile
        private var INSTANCE: OsDataSourceImpl? = null

        fun getInstance(app: MyApplication) =
                INSTANCE ?: synchronized(OsDataSourceImpl::class.java) {
                    INSTANCE ?: OsDataSourceImpl(app).also {
                        INSTANCE = it
                    }
                }
    }
}
