package drawable

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.hendra.myviewmodel.R

class MyButton : AppCompatButton {
    private var txtColor: Int = 0
    private var enableBackgroundButton: Drawable? = null
    private var disabledBackgroundButton: Drawable? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    // this function is created to add attribute for the Button
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        background = if (isEnabled) enableBackgroundButton else disabledBackgroundButton
        setTextColor(txtColor)
        text = if (isEnabled) "Submit" else "Harap isi data terlebih dahulu"
        textSize = 12f
        gravity = Gravity.CENTER
    }

    private fun init() {
        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
        enableBackgroundButton = ResourcesCompat.getDrawable(resources, R.drawable.bg_button, null)
        disabledBackgroundButton = ResourcesCompat.getDrawable(resources, R.drawable.bg_disable_button, null)
    }
}