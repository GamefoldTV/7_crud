package ru.netology.nmedia.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import ru.netology.nmedia.exception.InternalServerError

@Configuration
class AppWebMvcConfigurer : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(object : HandlerInterceptor {
            override fun preHandle(
                request: HttpServletRequest,
                response: HttpServletResponse,
                handler: Any
            ): Boolean {
                if (
                    request.requestURI.startsWith("/api/slow") ||
                    request.requestURI.startsWith("/avatars") ||
                    request.requestURI.startsWith("/images")
                ) {
                    Thread.sleep(5_000)
                }
                return true
            }
        })
        registry.addInterceptor(object : HandlerInterceptor {
            override fun preHandle(
                request: HttpServletRequest,
                response: HttpServletResponse,
                handler: Any
            ): Boolean {
                if (Math.random() > 0.5) {
                    throw InternalServerError()
                }
                return true
            }
        })
    }
}