package com.ssu.capstone.alrimi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableCaching
@SpringBootApplication
@EnableAspectJAutoProxy
class AlrimiApplication

fun main(args: Array<String>) {
    runApplication<AlrimiApplication>(*args)
}
