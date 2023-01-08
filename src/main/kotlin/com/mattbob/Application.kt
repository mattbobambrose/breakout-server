package com.mattbob

import com.mattbob.ConfigPage.configPage
import com.mattbob.Context.configMap
import com.mattbob.plugins.configureHTTP
import com.mattbob.plugins.configureMonitoring
import com.mattbob.plugins.respondCss
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.link
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(CIO, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

object Context {
    val configMap = mutableMapOf<String, GameConfig>()
}

fun Application.module() {
    configureHTTP()
    configureMonitoring()
    install(CORS) {
        anyHost()
    }
    install(ContentNegotiation) {
        json(Json {
            encodeDefaults = true
            prettyPrint = true
            isLenient = true
        })
    }
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/config") {
            val sessionId = call.request.queryParameters["sessionId"] ?: -1
            val config =
                if (sessionId == "-1") {
                    GameConfig().assignBrickPattern()
                } else {
                    configMap[sessionId] ?: error("Missing sessionId: $sessionId")
                }
            call.respond(config)
        }
        get("/config-form") {
            call.respondText(configPage(), ContentType.Text.Html)
        }
        post("/submit-config") {
            val formParameters = call.receiveParameters()
            val gameConfig: GameConfig = GameConfig(
                ballRadius = formParameters["ballRadius"]?.toInt() ?: error("No ballRadius found"),
                ballSpeed = formParameters["ballSpeed"]?.toInt() ?: error("No ballSpeed found"),
                ballColor = formParameters["ballColor"] ?: error("No ballColor found"),
                paddleWidth = formParameters["paddleWidth"]?.toInt() ?: error("No paddleWidth found"),
                paddleHeight = formParameters["paddleHeight"]?.toInt() ?: error("No paddleHeight found"),
                paddleSpeed = formParameters["paddleSpeed"]?.toInt() ?: error("No paddleSpeed found"),
                paddleColor = formParameters["paddleColor"] ?: error("No paddleColor found"),
                brickSideBufferSpace = formParameters["brickSideBufferSize"]?.toDouble()
                    ?: error("No brickSideBufferSize found"),
                brickVerticalBufferSpace = formParameters["brickVerticalBufferSize"]?.toDouble()
                    ?: error("No brickVerticalBufferSize found"),
                pattern = formParameters["pattern"] ?: error("No pattern found"),
                brickColor = formParameters["brickColor"] ?: error("No brickColor found"),
                windowWidth = formParameters["windowWidth"]?.toInt() ?: error("No windowWidth found"),
                windowHeight = formParameters["windowHeight"]?.toInt() ?: error("No windowHeight found"),
                backgroundColor = formParameters["backgroundColor"] ?: error("No backgroundColor found"),
                bricksRandomColors = formParameters["bricksRandomColors"] != null
            )
            val sessionId = System.currentTimeMillis().toString()
            configMap[sessionId] = gameConfig.assignBrickPattern()
            call.respondRedirect("http://0.0.0.0:8081/static/www/index.html?sessionId=$sessionId")
        }
        get("/styles.css") {
            call.respondCss {
                body {
                    backgroundColor = Color.darkBlue
                    margin(0.px)
                }
                rule("h1.page-title") {
                    color = Color.white
                }
            }
        }

        get("/html-css-dsl") {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/styles.css", type = "text/css")
                }
                body {
                    h1(classes = "page-title") {
                        +"Hello from Ktor!"
                    }
                }
            }
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}