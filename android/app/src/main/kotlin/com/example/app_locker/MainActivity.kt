package com.example.app_locker

import io.flutter.embedding.android.FlutterActivity

class MainActivity: FlutterActivity(){
    private val CHANNEL = "app.locker/validate"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "validate") {
                // TODO: Replace with actual validation logic
                val isValid = performValidation()
                result.success(isValid)
            } else {
                result.notImplemented()
            }
        }
    }

    private fun performValidation(): Boolean {
        // TODO: Implement Google Drive + Excel parsing here
        // For now, stub returns true
        return true
    }
}
