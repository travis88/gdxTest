package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.game.entity.Ball

class MyGdxGame : ApplicationAdapter() {
    lateinit var shape: ShapeRenderer
    lateinit var ball: Ball
    
    override fun create() {
        shape = ShapeRenderer()
        ball = Ball(150f, 200f, 70f, 12, 5)
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        ball.update()
        shape.begin(ShapeRenderer.ShapeType.Filled)
        ball.draw(shape)
        shape.end()
    }
}