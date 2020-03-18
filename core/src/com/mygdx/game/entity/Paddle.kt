package com.mygdx.game.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Paddle(var x: Float, var y: Float,
             var width: Float, var height: Float, private var color: Color = Color.WHITE) {

    fun draw(shape: ShapeRenderer) {
        shape.setColor(this.color)
        shape.rect(this.x, this.y, this.width, this.height)
    }

    fun update() {
        if (x <= 0) {
            color = Color.BLUE
        } else if (x + width >= Gdx.graphics.width) {
            color = Color.GREEN
        } else {
            color = Color.WHITE
        }
        x = Gdx.input.x.toFloat() - width / 2
//        this.y = Gdx.graphics.height - Gdx.input.getY().toFloat()
    }
}