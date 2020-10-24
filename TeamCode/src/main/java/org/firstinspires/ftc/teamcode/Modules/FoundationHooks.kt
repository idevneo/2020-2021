package org.firstinspires.ftc.teamcode.Modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.openftc.revextensions2.ExpansionHubServo

class FoundationHooks constructor(Op : OpMode) {
    val Gate : ExpansionHubServo = Op.hardwareMap.get(ExpansionHubServo::class.java, "gate")

    var down : Boolean = false

    init {
        origin()
    }

    fun origin() {
        Gate.position = 0.33
    }


    fun down() {
        down = true
        origin()
    }

    fun up() {
        down = false
        Gate.position = 0.7
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