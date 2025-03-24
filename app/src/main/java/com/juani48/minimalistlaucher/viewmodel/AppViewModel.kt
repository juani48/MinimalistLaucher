package com.juani48.minimalistlaucher.viewmodel

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo

class AppViewModel {

    private val installedApps = mutableListOf<AppObject>()

    fun loadInstalledApps(context: Context): MutableList<AppObject> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList: List<ResolveInfo> = context.packageManager.queryIntentActivities(intent, 0)
        for (elem in pkgAppsList){
            this.installedApps.add(
                AppObject(
                    name = elem.loadLabel(context.packageManager).toString(),
                    packageName = elem.activityInfo.packageName.toString(),
                )
            )
        }
        return this.installedApps
    }

    fun launchApp(context: Context, appObject: AppObject){
        val launchAppIntent: Intent? = context.packageManager.getLaunchIntentForPackage(appObject.getPackageName())
        if( launchAppIntent != null){
            context.startActivity(launchAppIntent)
        }
    }

    fun searchApp(text: String): MutableList<AppObject> {
        if(text == ""){
            return this.installedApps
        }
        val string = text.lowercase()
        val list = mutableListOf<AppObject>()
        for(app in this.installedApps){
            if(app.getName().lowercase().contains(string)){
                list.add(app)
            }
        }
        return list
    }

}