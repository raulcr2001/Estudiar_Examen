package ejercicios

import org.json.JSONArray
import org.json.JSONObject
import java.io.FileWriter
import javax.xml.parsers.DocumentBuilderFactory

fun main(){
    xmlToJson()
}

fun xmlToJson(){

    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("src/main/resources/Rutes.xml")

    val raizXML = doc.documentElement
    val raizJSON = JSONObject()

    val vehiculos = raizXML.childNodes
    val arrayVehiculos = JSONArray()

    raizJSON.put("oferta", arrayVehiculos)

    for(i in 0 until vehiculos.length){
        val vehiculoJSON = JSONObject()
        val vehiculo = vehiculos.item(i)

        for(j in 0 until vehiculo.childNodes.length){
            vehiculoJSON.put(vehiculo.childNodes.item(j).nodeName, vehiculo.childNodes.item(j).textContent)
        }
        arrayVehiculos.put(vehiculoJSON)
    }

    val file = FileWriter("src/main/resources/vehiculos.json")
    file.write(raizJSON.toString(4))
    file.close()

}