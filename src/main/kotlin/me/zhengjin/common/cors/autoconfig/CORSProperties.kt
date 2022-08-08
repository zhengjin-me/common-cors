/*
 * MIT License
 *
 * Copyright (c) 2022 ZhengJin Fang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.zhengjin.common.cors.autoconfig

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.web.cors.CorsConfiguration

/**
 * @version V1.0
 * @Title: CORSProperties
 * @Package me.zhengjin.common.cors.autoconfig.properties
 * @Description: cors 跨域配置
 * @Author fangzhengjin
 * @Date 2017-10-17 10:34
 */
@ConfigurationProperties(prefix = "customize.common.cors")
class CORSProperties {
    var configs: List<CORSConfig> = mutableListOf()

    data class CORSConfig(
        var mapping: String = "",
        var allowCredentials: Boolean = false,
        var maxAge: Long = 300,
        var origins: ArrayList<String> = ArrayList(),
        var allowMethods: ArrayList<String> = ArrayList(),
        var allowHeaders: ArrayList<String> = ArrayList(),
        var exposedHeaders: ArrayList<String> = ArrayList()
    )

    fun toCorsConfiguration(): CorsConfiguration {
        val corsConfiguration = CorsConfiguration()
        if (configs.isNotEmpty()) {
            for (corsConfig in configs) {
                var origins = corsConfig.origins
                if (origins.isEmpty()) {
                    origins = arrayListOf()
                }
                var allowedMethods = corsConfig.allowMethods
                if (allowedMethods.isEmpty()) {
                    allowedMethods = arrayListOf("GET", "POST", "OPTIONS")
                }
                var allowedHeaders = corsConfig.allowHeaders
                if (allowedHeaders.isEmpty()) {
                    allowedHeaders = arrayListOf("*")
                }
                var exposedHeaders = corsConfig.exposedHeaders
                if (exposedHeaders.isEmpty()) {
                    exposedHeaders = arrayListOf()
                }
                corsConfiguration.allowedMethods = allowedMethods
                corsConfiguration.allowedHeaders = allowedHeaders
                corsConfiguration.exposedHeaders = exposedHeaders
                corsConfiguration.allowCredentials = corsConfig.allowCredentials
                corsConfiguration.maxAge = corsConfig.maxAge
                // 为true时,allowedOrigins不允许为*
                if (corsConfig.allowCredentials) {
                    corsConfiguration.allowedOriginPatterns = origins
                } else {
                    corsConfiguration.allowedOrigins = origins
                }
            }
        }
        return corsConfiguration
    }
}
