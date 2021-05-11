package com.ssu.capstone.alrimi.core.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.IOException
import java.io.InputStream
import javax.annotation.PostConstruct

@Configuration
class FcmConfig {

    @PostConstruct
    fun init() {
        try {
            val serviceAccount: InputStream =
                ClassPathResource("fcm.json").inputStream

            val options: FirebaseOptions = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
            FirebaseApp.initializeApp(options,"X-Alrimi")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}