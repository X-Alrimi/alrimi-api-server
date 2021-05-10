package com.ssu.capstone.alrimi.core.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.InputStream
import javax.annotation.PostConstruct

@Configuration
class FcmConfig(
    @Value("\${fcm.admin.file}")
    var path: String
) {
    @PostConstruct
    fun init() {
        val serviceAccount: InputStream = ClassPathResource(path).inputStream

        val options: FirebaseOptions = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        FirebaseApp.initializeApp(options)
    }
}