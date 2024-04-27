package rs.ac.metropolitan.cs330_dz09.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import rs.ac.metropolitan.cs330_dz09.data.entity.Sastojak
import rs.ac.metropolitan.cs330_dz09.data.entity.Torta

data class TortaSastojci(@Embedded val torta: Torta,
                         @Relation(
                             parentColumn = "id",
                             entityColumn = "tortaId",
                             associateBy = Junction(ReceptTorta::class)
                         )
                         val sastojci: List<Sastojak>)
