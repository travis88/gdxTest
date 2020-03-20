package com.mygdx.game.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import kotlin.math.abs


class Ball(private var x: Float, private var y: Float,
           private var size: Float,
           private var xSpeed: Float,
           private var ySpeed: Float, private var color: Color = Color.WHITE) {

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
            color = Color.RED
            ySpeed = -ySpeed

            if (collObj is Paddle){
                // разница между центром и точкой попадания
                val diff = (collObj.x + collObj.width / 2) - this.x
                val percent = (diff / (collObj.width / 2))

                Gdx.app.log(TAG, "current speed = $xSpeed change speed = ${SPEED * percent}")
                xSpeed = -SPEED * percent
            }

            if (collObj is Brick){
                collObj.destroyed = true
            }
        } else {
            color = Color.WHITE
        }
    }

    private fun collidesWith(collObj: CollisionObject): Boolean {
        val xCollision = collObj.x <= this.x + this.size && collObj.x + collObj.width >= this.x - this.size
        val yCollision = collObj.y <= this.y + this.size && collObj.y + collObj.height >= this.y - this.size
        return xCollision && yCollision
    }

    companion object{
        const val TAG = "TAG"
        const val SPEED = 5f
    }
}