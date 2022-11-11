package ejercicios

import Ruta
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

fun main(){
    writeJSON()
}

fun writeJSON(){
    val f = ObjectInputStream(FileInputStream("src/main/resources/Rutes.obj"))
    val read = f.readObject() as MutableList<Ruta>
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val list = Types.newParameterizedType(List::class.java, Ruta::class.java)
    val rutas: JsonAdapter<List<Ruta>> = moshi.adapter(list)
    val json = rutas.toJson(read)

    File("src/main/resources/Rutes.json").writeText(json)
}