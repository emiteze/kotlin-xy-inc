package backend.rest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class XyIncApplication{
}

fun main(args : Array<String>){
    SpringApplication.run(XyIncApplication::class.java, *args)
}