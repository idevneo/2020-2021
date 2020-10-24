package org.firstinspires.ftc.teamcode.Modules

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.openftc.revextensions2.ExpansionHubServo

class WobbleClamp constructor(Op: OpMode) {
    val grabber : ExpansionHubServo = Op.hardwareMap.get(ExpansionHubServo::class.java, "grabber")
    val rotate : ExpansionHubServo = Op.hardwareMap.get(ExpansionHubServo::class.java, "rotate")


    init {
        store()
    }

    fun store() {
        grabber.position = 0.0
        rotate.position = 0.0
    }

    fun clamp() {
        grabber.position = .1
    }

    fun rotatorDown() {
        rotate.position = .75
        grabber.position = .69
    }

    fun dropBlock() {
        grabber.position = 0.7
    }

    fun hold() {
        rotate.position = .45
    }

    fun tilt() {
        rotate.position = .6
    }

}