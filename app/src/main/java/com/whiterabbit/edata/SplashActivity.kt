package com.whiterabbit.edata

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val backgroundExecutor: ScheduledExecutorService =
            Executors.newSingleThreadScheduledExecutor()

        backgroundExecutor.schedule({


            val intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
            finish()


        }, 3, java.util.concurrent.TimeUnit.SECONDS)

    }

}