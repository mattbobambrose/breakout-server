package com.mattbob

import kotlinx.html.*
import kotlinx.html.stream.createHTML

object ConfigPage {
    internal fun configPage() =
        createHTML()
            .html {
                head {
                    title { +"CanvasCacheServer Version" }
                }
                body {
                    // dsl for simple form
                    val gameConfig = GameConfig()
                    form {
                        method = FormMethod.post
                        action = "/submit-config"
                        table {
                            tr {
                                td {
                                    +"Ball Radius"
                                }
                                td {
                                    input {
                                        type = InputType.number
                                        name = "ballRadius"
                                        value = gameConfig.ballRadius.toString()
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Ball Speed"
                                }
                                td {
                                    input {
                                        type = InputType.number
                                        name = "ballSpeed"
                                        value = gameConfig.ballSpeed.toString()
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Ball Color"
                                }
                                td {
                                    input {
                                        type = InputType.color
                                        name = "ballColor"
                                        value = gameConfig.ballColor
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Paddle Width"
                                }
                                td {
                                    input {
                                        type = InputType.number
                                        name = "paddleWidth"
                                        value = gameConfig.paddleWidth.toString()
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Paddle Height"
                                }
                                td {
                                    input {
                                        type = InputType.number
                                        name = "paddleHeight"
                                        value = gameConfig.paddleHeight.toString()
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Paddle Speed"
                                }
                                td {
                                    input {
                                        type = InputType.number
                                        name = "paddleSpeed"
                                        value = gameConfig.paddleSpeed.toString()
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Paddle Color"
                                }
                                td {
                                    input {
                                        type = InputType.color
                                        name = "paddleColor"
                                        value = gameConfig.paddleColor
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Brick Side Buffer Size"
                                }
                                td {
                                    input {
                                        type = InputType.number
                                        name = "brickSideBufferSize"
                                        value = gameConfig.brickSideBufferSpace.toString()
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Brick Vertical Buffer Size"
                                }
                                td {
                                    input {
                                        type = InputType.number
                                        name = "brickVerticalBufferSize"
                                        value = gameConfig.brickVerticalBufferSpace.toString()
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Pattern"
                                }
                                td {
                                    textArea {
                                        name = "pattern"
                                        +gameConfig.pattern
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Brick Color"
                                }
                                td {
                                    input {
                                        type = InputType.color
                                        name = "brickColor"
                                        value = gameConfig.brickColor
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Window Width"
                                }
                                td {
                                    input {
                                        type = InputType.number
                                        name = "windowWidth"
                                        value = gameConfig.windowWidth.toString()
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Window Height"
                                }
                                td {
                                    input {
                                        type = InputType.number
                                        name = "windowHeight"
                                        value = gameConfig.windowHeight.toString()
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Bricks Random Colors"
                                }
                                td {
                                    input {
                                        type = InputType.checkBox
                                        name = "bricksRandomColors"
                                        checked = gameConfig.bricksRandomColors
                                    }
                                }
                            }
                            tr {
                                td {
                                    +"Background Color"
                                }
                                td {
                                    input {
                                        type = InputType.color
                                        name = "backgroundColor"
                                        value = gameConfig.backgroundColor
                                    }
                                }
                            }

                            tr {
                                td { }
                                td {
                                    submitInput { value = "Submit" }
                                }
                            }
                        }
                    }
                }
            }
}