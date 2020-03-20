package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.entity.Ball
import com.mygdx.game.entity.Brick
import com.mygdx.game.entity.Paddle

class MyGdxGame : ApplicationAdapter() {
    private lateinit var shape: ShapeRenderer
    private lateinit var ball: Ball
    private lateinit var paddle: Paddle
    private lateinit var bricks: ArrayList<Brick>

    private lateinit var spriteBatch: SpriteBatch
    private lateinit var font: BitmapFont

    private var score = 0
    private var lives = 10

    override fun create() {
        shape = ShapeRenderer()
        spriteBatch = SpriteBatch()
        font = BitmapFont()

        ball = Ball(150f, 200f, 10f, 5f, 5f)
        paddle = Paddle((Gdx.graphics.width / 2).toFloat(), 10f, 100f, 10f)
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
        if (ball.checkOver())
            --lives
        shape.begin(ShapeRenderer.ShapeType.Filled)
        for (brick in bricks) {
            brick.draw(shape)
            ball.checkCollision(brick)
        }
        for (i in 0 until bricks.size) {
            val b = bricks[i]
            if (b.destroyed) {
                bricks.remove(b)
                ++score
                break
            }
        }
        ball.draw(shape)
        paddle.draw(shape)
        shape.end()

        spriteBatch.begin()
        font.color = Color.RED
        font.draw(spriteBatch, "score: $score", 10f, 20f)
        font.draw(spriteBatch, "lives: $lives", 10f, 50f)
        spriteBatch.end()
    }
}