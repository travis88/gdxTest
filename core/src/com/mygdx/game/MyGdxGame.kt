package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.entity.Ball
import com.mygdx.game.entity.Brick
import com.mygdx.game.entity.Paddle

class MyGdxGame : ApplicationAdapter() {
    private lateinit var shape: ShapeRenderer
    lateinit var ball: Ball
    lateinit var paddle: Paddle
    lateinit var bricks: ArrayList<Brick>

    override fun create() {
        shape = ShapeRenderer()
        ball = Ball(150f, 200f, 15f, 5, 5)
        paddle = Paddle((Gdx.graphics.width / 2).toFloat(), 10f, 70f, 10f)
        bricks = ArrayList()
        val brickWidth = 63
        val brickHeight = 20
        for (y in Gdx.graphics.height / 2 until Gdx.graphics.height step brickHeight + 10) {
            for (x in 0..Gdx.graphics.width step brickWidth + 10) {
                bricks.add(Brick(x.toFloat(), y.toFloat(), brickWidth.toFloat(), brickHeight.toFloat()))
            }
        }
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        ball.update()
        paddle.update()
        ball.checkCollision(paddle)
        shape.begin(ShapeRenderer.ShapeType.Filled)
        for (brick in bricks) {
            brick.draw(shape)
            ball.checkCollision(brick)
        }
        for (i in 0 until bricks.size) {
            val b = bricks[i]
            if (b.destroyed) {
                bricks.remove(b)
                break
            }
        }
        ball.draw(shape)
        paddle.draw(shape)
        shape.end()
    }
}