import java.io.*
import java.io.DataInputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

    class Coordenades(val latitud: Double, var longitud: Double) : java.io.Serializable {
        companion object {
            private const val serialVersionUID: Long = 1
        }
    }

    class PuntGeo(val nom: String, val coord: Coordenades) : java.io.Serializable {
        companion object {
            private const val serialVersionUID: Long = 1
        }


    }

    class Ruta(
        var nom: String,
        var desnivell: Int,
        var desnivellAcumulat: Int,
        var llistaDePunts: MutableList<PuntGeo>
    ) : Serializable {

        companion object {
            private const val serialVersionUID: Long = 1
        }

        fun addPunt(p: PuntGeo) {
            llistaDePunts.add(p)
        }

        fun getPunt(i: Int): PuntGeo {
            return llistaDePunts.get(i)
        }

        fun getPuntNom(i: Int): String {
            return llistaDePunts.get(i).nom
        }

        fun getPuntLatitud(i: Int): Double {
            return llistaDePunts.get(i).coord.latitud
        }

        fun getPuntLongitud(i: Int): Double {
            return llistaDePunts.get(i).coord.longitud
        }

        fun size(): Int {
            return llistaDePunts.size
        }

        fun mostrarRuta() {
            // Aquest és el mètode que heu d'implementar vosaltres
            println("Ruta: $nom")
            println("Desnivell: $desnivell")
            println("Desnivell acumulat: $desnivellAcumulat")
            println("Te ${size()} punts")
            for (i in 0 until size()) {
                println("Punt $i: ${getPuntNom(i)} (${getPuntLatitud(i)}, ${getPuntLongitud(i)})")

            }
            println()
        }
    }
    fun main() {
        val f =
            DataInputStream(FileInputStream("/home/INFORMATICA/alu10186575/AndroidStudioProjects/T3Ex/app/src/main/java/com/example/t3ex/Rutes.dat"))
        var rutas = ArrayList<Ruta>()
        while (f.available() > 0) {
            val nom = f.readUTF()
            val desnivell = f.readInt()
            val desnivellAcumulat = f.readInt()
            val punts = f.readInt()
            val llistaDePunts = ArrayList<PuntGeo>(punts)
            val ruta = Ruta(nom, desnivell, desnivellAcumulat, llistaDePunts)
            for (i in 1..punts) {
                val nom = f.readUTF()
                val latitud = f.readDouble()
                val altitud = f.readDouble()
                val coordenades = Coordenades(latitud, altitud)
                val puntGeo = PuntGeo(nom, coordenades)
                ruta.addPunt(puntGeo)
            }

            rutas.add(ruta)
        }
        f.close()
        val f_obj = ObjectOutputStream(FileOutputStream("src/Rutas.obj"))
        for (i in rutas.indices) {
            rutas[i].mostrarRuta()
        }
        f_obj.writeObject(rutas)
        f_obj.close()
        f.close()
    }