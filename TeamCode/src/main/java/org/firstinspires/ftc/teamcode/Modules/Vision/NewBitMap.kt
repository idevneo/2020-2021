package org.firstinspires.ftc.teamcode.Modules.Vision

import android.graphics.Bitmap
import android.graphics.Color.*

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.vuforia.Frame
import com.vuforia.Image
import com.vuforia.PIXEL_FORMAT
import com.vuforia.Vuforia

import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters

import java.util.ArrayList
import java.util.Collections
import java.util.concurrent.BlockingQueue

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.apache.commons.math3.distribution.IntegerDistribution

class NewBitMap constructor(val opMode: OpMode) {


    // importing vuforia class for taking an image
    lateinit var vuforia: VuforiaLocalizer
    val RED_THRESHOLD: Int = 200
    val BLUE_THRESHOLD: Int = 50
    val GREEN_THRESHOLD: Int = 100



    init {
        val cameraMonitorViewId = opMode.hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", opMode.hardwareMap.appContext.packageName)

        val params = VuforiaLocalizer.Parameters(cameraMonitorViewId)

        params.vuforiaLicenseKey = "AQvLCbX/////AAABmTGnnsC2rUXvp1TAuiOSac0ZMvc3GKI93tFoRn4jPzB3uSMiwj75PNfUU6MaVsNZWczJYOep8LvDeM/3hf1+zO/3w31n1qJTtB2VHle8+MHWNVbNzXKLqfGSdvXK/wYAanXG2PBSKpgO1Fv5Yg27eZfIR7QOh7+J1zT1iKW/VmlsVSSaAzUSzYpfLufQDdE2wWQYrs8ObLq2kC37CeUlJ786gywyHts3Mv12fWCSdTH5oclkaEXsVC/8LxD1m+gpbRc2KC0BXnlwqwA2VqPSFU91vD8eCcD6t2WDbn0oJas31PcooBYWM6UgGm9I2plWazlIok72QG/kOYDh4yXOT4YXp1eYh864e8B7mhM3VclQ"
        params.cameraName = opMode.hardwareMap.get(WebcamName::class.java, "Webcam 1")
        vuforia = ClassFactory.getInstance().createVuforia(params)
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true) //enables RGB565 format for the image
        vuforia.frameQueueCapacity = 4 //tells VuforiaLocalizer to only store one frame at a time
        vuforia.enableConvertFrameToBitmap()
    }

    @Throws(InterruptedException::class)
    fun getImage() : Bitmap {

        val frame = vuforia.frameQueue.take()
        var rgb: Image = frame.getImage(1)
        val numImages = frame.numImages


        for (i in 0 until numImages) {
            val fmt = frame.getImage(i.toInt()).format
            if (fmt == PIXEL_FORMAT.RGB565) {
                rgb = frame.getImage(i.toInt())
                break
            }
        }
        val bm = Bitmap.createBitmap(rgb.width, rgb.height, Bitmap.Config.RGB_565)
        bm.copyPixelsFromBuffer(rgb.pixels)

//        opMode.telemetry.addData("Image width", bm.getWidth())
//        opMode.telemetry.addData("Image height", bm.getHeight())
//        opMode.telemetry.update()

        frame.close()

        return bm
    }

    fun getImageHeight():Double{
        return getImage().height.toDouble()
    }

    fun getImageWeidth():Double{
        return getImage().width.toDouble()
    }

    @Throws(InterruptedException::class)
    fun vision(): String {
        val bitmap = getImage()
        val yValues = ArrayList<Int>()



        for (rowNum in 0..bitmap.height) {

            for (colNum in 200..600) {
                val pixel = bitmap.getPixel(colNum, rowNum)

                // receive R, G, and B values for each pixel
                val redPixel = red(pixel)
                val greenPixel = green(pixel)
                val bluePixel = blue(pixel)

                //checking if the pixel meets the thresholds to be assigned a gold value
                if (redPixel >= RED_THRESHOLD && greenPixel >= GREEN_THRESHOLD && bluePixel <= BLUE_THRESHOLD) {

                    yValues.add(colNum)
                }
            }

        }

        var avgY = 0.0
        for (y in yValues) {
            avgY += y
        }

        avgY /= yValues.size

        if (avgY < 160) {
            return "posC";

        }
        else if (avgY < 200) {
            return "posB";

        }
        else {
            return "posA";

        }
    }

    @Throws(InterruptedException::class)
    fun getAvgX(): Double {
        val bitmap = getImage()
        val yValues = ArrayList<Int>()



        for (rowNum in 0..bitmap.height) {

            for (colNum in 200..600) {
                val pixel = bitmap.getPixel(colNum, rowNum)

                // receive R, G, and B values for each pixel
                val redPixel = red(pixel)
                val greenPixel = green(pixel)
                val bluePixel = blue(pixel)

                //checking if the pixel meets the thresholds to be assigned a gold value
                if (redPixel >= RED_THRESHOLD && greenPixel >= GREEN_THRESHOLD && bluePixel <= BLUE_THRESHOLD) {

                    yValues.add(colNum)
                }
            }

        }

        var avgY = 0.0
        for (y in yValues) {
            avgY += y
        }

        return avgY
    }




}