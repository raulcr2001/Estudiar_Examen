import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream


fun main(args: Array<String>) {

    val archivodat = DataInputStream(FileInputStream("src/main/resources/Rutes.dat"))
    val inputStream: File = File("src/main/resources/saludo.txt")
    val inputString = inputStream.bufferedReader().use { it.readText() }
    println(inputString)

    while(archivodat.available() > 0){
        println("Nombre ruta " + archivodat.readUTF())
        println("Desnivell " + archivodat.readInt())
        println("Desnivell Acumulat " + archivodat.readInt())
        val nPuntos = archivodat.readInt()

        for(i in 1..nPuntos){
            println("Nombre punto $i: " + archivodat.readUTF())
            println("Latitud y Longitud: "+ archivodat.readDouble() + " "+ archivodat.readDouble())
        }
    }
    archivodat.close()

}