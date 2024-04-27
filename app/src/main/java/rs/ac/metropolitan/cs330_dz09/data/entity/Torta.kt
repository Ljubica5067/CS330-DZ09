package rs.ac.metropolitan.cs330_dz09.data.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Torta(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                 @ColumnInfo(name = "naziv") val naziv: String,
                 @ColumnInfo(name = "tezina") val tezina: Int,
                 @ColumnInfo(name = "opis") val opis: String)
