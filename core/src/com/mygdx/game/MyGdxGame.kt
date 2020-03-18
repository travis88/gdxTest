package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.entity.Ball
import com.mygdx.game.entity.Paddle

class MyGdxGame : ApplicationAdapter() {
    private lateinit var shape: ShapeRenderer
    lateinit var ball: Ball
    lateinit var paddle: Paddle

    override fun create() {
        shape = ShapeRenderer()
        ball = Ball(150f, 200f, 15f, 5, 5)
        paddle = Paddle((Gdx.graphics.width / 2).toFloat(), 10f, 70f, 10f)
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        ball.update()
        paddle.update()
        ball.checkCollision(paddle)
        shape.begin(ShapeRenderer.ShapeType.Filled)
        ball.draw(shape)
        paddle.draw(shape)
        shape.end()
    }
}