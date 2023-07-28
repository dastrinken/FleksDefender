package ui

import korlibs.image.color.*
import korlibs.korge.input.*

import korlibs.korge.ui.*
import korlibs.korge.view.*
import korlibs.math.geom.*

// inline function for adding the topBar to the container it is called in.
inline fun Container.topBar(
    myParent: View
) = TopBar(myParent).addTo(this)
class TopBar(myParent: View) : Container() {
    init {
        // sections for the left, middle, and right sides
        // width will be overridden
        val leftSection = solidRect(100, 100, Colors.RED)
        val middleSection= solidRect(100, 100, Colors.GREEN)
        val rightSection= solidRect(100, 100, Colors.BLUE)

        leftSection.onClick{
            println("left clicked")
        }

        middleSection.onClick{
            println("middle clicked")
        }

        rightSection.onClick{
            println("right clicked")
        }

        // Position the bitmaps horizontally
        uiHorizontalFill{
            // set fill width or it won't actually fill horizontally
            this.width = myParent.width

            // Place the sections in order
            this += leftSection
            this += middleSection
            this += rightSection
        }
    }
}
