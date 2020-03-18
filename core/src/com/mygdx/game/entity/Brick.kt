package com.mygdx.game.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Brick(override var x: Float, override var y: Float,
            override var width: Float, override var height: Float,
            var destroyed: Boolean = false) : CollisionObject(x, y, width, height) {

    fun draw(shape: ShapeRenderer) {
        shape.rect(this.x, this.y, this.width, this.height)
    }
}