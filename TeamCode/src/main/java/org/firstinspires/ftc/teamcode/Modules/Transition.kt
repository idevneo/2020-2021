package org.firstinspires.ftc.teamcode.Modules

import android.os.Handler
import android.os.Looper
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.ElapsedTime
import org.openftc.revextensions2.ExpansionHubMotor

class Transition constructor(op : OpMode) {


    lateinit var transition : ExpansionHubMotor

    init {
       
        transition = op.hardwareMap.get(ExpansionHubMotor::class.java, "transition")

    }






    fun power (power: Float){

        transition.power = (-power).toDouble()
    }


    fun stopIntake(){

        transition.power = 0.0
    }
}