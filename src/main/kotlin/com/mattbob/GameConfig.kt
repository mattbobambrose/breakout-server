package com.mattbob

import com.github.lalyos.jfiglet.FigletFont
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class GameConfig(
    val ballRadius: Int = 10,
    val ballSpeed: Int = 5,
    val ballColor: String = "#FF0000",
    val paddleWidth: Int = 200,
    val paddleHeight: Int = 10,
    val paddleSpeed: Int = 10,
    val paddleColor: String = "#0000FF",
    val brickSideBufferSpace: Double = 0.1,
    val brickVerticalBufferSpace: Double = 0.1,
    val pattern: String = """
        HELLO
        THERE
    """.trimIndent(),
    var brickPattern: String = "",
    val brickColor: String = "#00FF00",
    val windowWidth: Int = 800,
    val windowHeight: Int = 600,
    val backgroundColor: String = "#000000",
    val bricksRandomColors: Boolean = false,
) {
    //    http: //www.figlet.org/fonts/banner3.flf
    fun assignBrickPattern() =
        apply {
            brickPattern = pattern.lines().map {
                FigletFont.convertOneLine(File("src/main/resources/fonts/banner3.flf"), it).lines() + listOf("")
            }.flatten().joinToString("\n")
        }
}