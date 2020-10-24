package org.firstinspires.ftc.teamcode.Modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.openftc.revextensions2.ExpansionHubServo

class Gate constructor(Op : OpMode) {

    val Gate : ExpansionHubServo = Op.hardwareMap.get(ExpansionHubServo::class.java, "Gate")

    var down : Boolean = false

    init {
        origin()
    }

    fun origin() {
        Gate.position = 0.0

    }

    fun down() {
        down = true
        Gate.position = 0.7

    }

    fun up() {
        down = false
        origin()
    }

    fun toggle() {
        down = if (down) {
            up()
            false
        } else {
            down()
            true
        }
    }
}