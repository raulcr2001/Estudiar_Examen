package ejercicios

import java.io.File

fun main(args: Array<String>){
    val cuadrado = File("src/main/resources/cuadrado.bmp")
    val triangulo = File("src/main/resources/triangulo.bmp")
    val salida = File("src/main/resources/res.bmp")

    val leeCuadrado = cuadrado.readBytes()
    val leeTriangulo = triangulo.readBytes()

    for (i in leeCuadrado.indices){
        if (leeTriangulo[i] >= 0){
            leeCuadrado[i] = leeTriangulo[i]
        }
    }

    salida.writeBytes(leeCuadrado)
}