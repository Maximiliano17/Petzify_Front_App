package com.maxi.petzify.ui.core

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permissions {
    fun checkCameraPermiso(activity: Activity):Boolean{
        return if(ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)){
                false
            }
            else{
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), 0)
                return true
            }
        }
        else{
            true
        }
    }
}