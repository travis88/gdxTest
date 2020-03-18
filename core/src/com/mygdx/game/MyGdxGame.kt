package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.entity.Ball
import java.util.*
import kotlin.collections.ArrayList

class MyGdxGame : ApplicationAdapter() {
    lateinit var shape: ShapeRenderer
    lateinit var balls: ArrayList<Ball>
    var rand = Random()

    override fun create() {
        shape = ShapeRenderer()
        balls = ArrayList()
        for (i in 0..10) {
            balls.add(Ball(rand.nextInt(Gdx.graphics.width).toFloat(),
                           rand.nextInt(Gdx.graphics.height).toFloat(),
                           rand.nextInt(100).toFloat(),
                           rand.nextInt(15),
                           rand.nextInt(15)))
        }
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        shape.begin(ShapeRenderer.ShapeType.Filled)
        for (ball in balls) {
            ball.update()
            ball.draw(shape)
        }
        shape.end()
    }
}