package com.mattbob

import kotlinx.serialization.Serializable

@Serializable
data class GameConfig(
    val ballRadius: Int = 10,
    val ballSpeed: Int = 5,
    val ballColor: String = "#FF0000",
    val paddleWidth: Int = 200,
    val paddleHeight: Int = 10,
    val paddleSpeed: Int = 10,
    val paddleColor: String = "#0000FF",
    val brickColor: String = "#00FF00",
    val brickSideBufferSize: Double = 0.1,
    val brickVerticalBufferSize: Double = 0.1,
    val pattern: String = """
            # # ### #    #    ###
            # # #   #    #    # #
            ### ### #    #    # #
            # # #   #    #    # #
            # # ### #### #### ###
        """,
    val windowWidth: Int = 800,
    val windowHeight: Int = 600,
    val backgroundColor: String = "#FF0000",
    val bricksRandomColors: Boolean = true,
)