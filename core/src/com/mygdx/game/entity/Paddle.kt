package com.mygdx.game.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Paddle(override var x: Float, override var y: Float,
             override var width: Float, override var height: Float,
             private var color: Color = Color.WHITE) : CollisionObject(x, y, width, height) {

    fun draw(shape: ShapeRenderer) {
        shape.color = this.color
        shape.rect(this.x, this.y, this.width, this.height)
    }

    fun update() {
        when {
            x <= 0 -> this.color = Color.BLUE
            x + width >= Gdx.graphics.width -> this.color = Color.GREEN
            else -> this.color = Color.WHITE
        }
        x = Gdx.input.x.toFloat() - width / 2
    }
}