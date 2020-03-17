package com.mygdx.game.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer


class Ball(private var x: Float, private var y: Float,
           private var size: Float, private var xSpeed: Int,
           private var ySpeed: Int) {
    fun update() {
        x += xSpeed
        y += ySpeed
        if (x < 0 || x > Gdx.graphics.width) {
            xSpeed = -xSpeed
        }
        if (y < 0 || y > Gdx.graphics.height) {
            ySpeed = -ySpeed
        }
    }

    fun draw(shape: ShapeRenderer) {
        shape.circle(this.x, this.y, this.size)
    }
}