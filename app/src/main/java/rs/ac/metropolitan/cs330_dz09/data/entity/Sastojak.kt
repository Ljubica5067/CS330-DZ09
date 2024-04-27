package rs.ac.metropolitan.cs330_dz09.data.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sastojak(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                    @ColumnInfo(name = "naziv") val naziv: String,
                    @ColumnInfo(name = "kolicina") val kolicina: Int,
                    @ColumnInfo(name = "jedinica_mere") val jedinicaMere: String,
                    val tortaId: Int)
