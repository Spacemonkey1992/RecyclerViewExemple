package ca.qc.cstj.recyclerviewexemple.helpers

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity

object PermissionHelper {

    fun AppCompatActivity.checkPermissions(permissions: Array<String>) : Boolean {

        permissions.forEach {
            if (this.checkCallingOrSelfPermission(it) == PackageManager.PERMISSION_DENIED){
                return false
            }
        }

        return true
    }
}