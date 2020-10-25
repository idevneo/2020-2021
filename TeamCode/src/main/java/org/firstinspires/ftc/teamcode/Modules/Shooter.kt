package org.firstinspires.ftc.teamcode.Modules

import android.os.Handler
import android.os.Looper
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.Lib.Structs.Pose2D
import org.openftc.revextensions2.ExpansionHubMotor
import kotlin.math.PI

class Shooter constructor(op : OpMode) {


    val shooter : ExpansionHubMotor
    var on : Boolean = false


    companion object Constants {

        const val motorTPR = 8192
        const val wheelRadius = 2.0
        const val gearRatio = 1.0
        // Left Right Center
    }

    init {

        shooter = op.hardwareMap.get(ExpansionHubMotor::class.java, "shooter")
        shooter.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER

        shooter.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
    }

    fun Int.e2Shooter() : Double = (Shooter.wheelRadius * 2.0 * PI * this) / Shooter.motorTPR

    fun toggle() {
        on = if (on) {
            off()
            false
        } else {
            on()
            true
        }
    }




    fun power (power: Double){
        shooter.power = power
    }

    fun on(){
        power(-0.9)
    }


    fun off(){
        shooter.power = 0.0

    }
}