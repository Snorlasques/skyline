/*
 * SPDX-License-Identifier: MPL-2.0
 * Copyright © 2022 Skyline Team and Contributors (https://github.com/skyline-emu/)
 */

package emu.skyline.input.dialog

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import emu.skyline.R
import emu.skyline.input.onscreen.OnScreenControllerView
import petrov.kristiyan.colorpicker.ColorPicker


class OscColorDialog(val activity : AppCompatActivity, val onScreenControllerView : OnScreenControllerView) : Dialog(activity) {

    private var textColorButton : Button? = null
    private var backgroundColorButton : Button? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.osc_color_dialog)

        textColorButton = findViewById(R.id.text_color_button)
        backgroundColorButton = findViewById(R.id.background_color_button)

        textColorButton?.setOnClickListener {
            ColorPicker(activity).apply {
                setTitle(activity.getString(R.string.osc_text_color))
                setRoundColorButton(true)
                setColors(Color.GRAY, Color.argb(180, 0, 0, 0), Color.argb(180, 255, 255, 255), Color.argb(180, 255, 60, 40), Color.argb(180, 225, 15, 0), Color.argb(180, 10, 185, 230), Color.argb(180, 70, 85, 245), Color.argb(180, 30, 220, 0))
                setDefaultColorButton(onScreenControllerView.getTextColor())
                setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                    override fun onChooseColor(position : Int, color : Int) {
                        onScreenControllerView.setTextColor(color)
                    }

                    override fun onCancel() {/*Nothing to do*/
                    }
                })
            }.show()
        }

        backgroundColorButton?.setOnClickListener {
            ColorPicker(activity).apply {
                setTitle(activity.getString(R.string.osc_background_color))
                setRoundColorButton(true)
                setColors(Color.TRANSPARENT, Color.GRAY, Color.argb(180, 0, 0, 0), Color.argb(180, 255, 255, 255), Color.argb(180, 255, 60, 40), Color.argb(180, 225, 15, 0), Color.argb(180, 10, 185, 230), Color.argb(180, 70, 85, 245), Color.argb(180, 30, 220, 0))
                setDefaultColorButton(onScreenControllerView.getBGColor())
                setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                    override fun onChooseColor(position : Int, color : Int) {
                        onScreenControllerView.setBGColor(color)
                    }

                    override fun onCancel() {/*Nothing to do*/
                    }
                })
            }.show()
        }
    }
}