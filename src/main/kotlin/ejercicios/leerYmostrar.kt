import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.ObjectInputStream
import java.io.EOFException


fun main(args: Array<String>) {

    //LEER Y MOSTRAR OBJ
    val archivoobj = ObjectInputStream(FileInputStream("src/main/resources/Rutes.obj"))

        try {
            while (true) {
                val r = archivoobj.readObject() as ArrayList<Ruta>
                for (i in r) {
                    i.mostrarRuta()
                }
            }

        } catch (eof: EOFException) {
            archivoobj.close()
        }

    //LEER Y MOSTRAR DAT Y TXT SIMULTÁNEAMENTE
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