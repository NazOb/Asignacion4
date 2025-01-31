package mx.edu.itesca.asignacion4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pesoK:EditText = findViewById(R.id.et_weight)
        val alturaE:EditText = findViewById(R.id.et_height)
        val imc:TextView = findViewById(R.id.tv_imc)
        val rango:TextView = findViewById(R.id.tv_range)
        val calc:Button = findViewById(R.id.btn_calcular)

        calc.setOnClickListener {
            var peso:Double = 0.0
            var est:Double = 0.0

            try {

                peso = pesoK.text.toString().toDouble()
                est = alturaE.text.toString().toDouble()

            } catch (e: java.lang.Exception){

                imc.setText("Debe ingresar valores reales")
                println(e)

            }

            var res = calcularIMC(est,peso)
            val formattedNumber = "%.2f".format(res)
            imc.setText(formattedNumber)

            var sal:String
            var color:Int

            when{
                res < 18.5 ->{
                    sal="Bajo peso"
                    color=R.color.colorRed
                }
                res >= 18.5 && res <= 24.9 ->{
                    sal="Saludable"
                    color=R.color.colorGreenish
                }
                res >= 25 && res <= 29.9 ->{
                    sal="Sobrepeso"
                    color=R.color.colorYellow
                }
                res >= 30 && res <= 34.9 ->{
                    sal="Obesidad Grado 1"
                    color=R.color.colorOrange
                }
                res >= 35 && res <= 39.9 ->{
                    sal="Obesidad Grado 2"
                    color=R.color.colorBrown
                }
                res >= 40 ->{
                    sal="Obesidad Grado 3"
                    color=R.color.colorRed
                }
                else -> {
                    sal="Error"
                    color=0
                }
            }

            rango.setBackgroundColor(color)
            rango.setText(sal)
        }

    }

    fun calcularIMC(height:Double,weight:Double):Double{
        return weight/(height*height)
    }
}