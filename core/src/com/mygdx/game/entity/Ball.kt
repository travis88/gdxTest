package com.mygdx.game.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer


class Ball(private var x: Float, private var y: Float,
           private var size: Float,
           private var xSpeed: Int,
           private var ySpeed: Int, private var color: Color = Color.WHITE) {

    fun draw(shape: ShapeRenderer) {
        shape.color = this.color
        shape.circle(this.x, this.y, this.size)
    }

    fun update() {
        x += xSpeed
        y += ySpeed
        if (x - size / 2 < 0 || x + size / 2 > Gdx.graphics.width) {
            xSpeed = -xSpeed
        }
        if (y - size / 2 < 0 || y + size / 2 > Gdx.graphics.height) {
            ySpeed = -ySpeed
        }
    }

    fun checkCollision(collObj: CollisionObject) {
        if (collidesWith(collObj)) {
            xSpeed = -xSpeed
            ySpeed = -ySpeed
            color = Color.RED
            if (collObj is Brick) {
                collObj.destroyed = true
            }
        } else {
            color = Color.WHITE
        }
    }

    fun collidesWith(collObj: CollisionObject): Boolean {
        val xCollision = collObj.x <= this.x + this.size && collObj.x + collObj.width >= this.x - this.size
        val yCollision = collObj.y <= this.y + this.size && collObj.y + collObj.height >= this.y - this.size
        return xCollision && yCollision
    }
}