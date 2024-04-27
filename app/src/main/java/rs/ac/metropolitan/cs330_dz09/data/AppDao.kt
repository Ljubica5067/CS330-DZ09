package rs.ac.metropolitan.cs330_dz09.data
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import rs.ac.metropolitan.cs330_dz09.data.entity.Recept
import rs.ac.metropolitan.cs330_dz09.data.entity.Sastojak
import rs.ac.metropolitan.cs330_dz09.data.entity.Torta
@Dao
interface AppDao {
    @Upsert
    suspend fun upsertRecept(recept: Recept): Long

    @Upsert
    suspend fun upsertSastojak(sastojak: Sastojak): Long

    @Upsert
    suspend fun upsertTorta(torta: Torta): Long

    @Query("SELECT * FROM Recept WHERE naziv LIKE :naziv")
    suspend fun searchRecepti(naziv: String): List<Recept>

    @Query("SELECT * FROM Torta INNER JOIN Sastojak ON Torta.id = Sastojak.tortaId WHERE Sastojak.jedinica_mere = 'kom'")
    suspend fun listaTortiSaKomSastojcima(): List<Torta>

    @Query("SELECT * FROM Torta INNER JOIN Sastojak ON Torta.id = Sastojak.tortaId WHERE Sastojak.kolicina > 2000 AND Sastojak.jedinica_mere = 'g'")
    suspend fun listaTortiTezeOdDvaKilograma(): List<Torta>

}