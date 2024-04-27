package rs.ac.metropolitan.cs330_dz09

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import rs.ac.metropolitan.cs330_dz09.data.AppDao
import rs.ac.metropolitan.cs330_dz09.data.AppDatabase
import rs.ac.metropolitan.cs330_dz09.data.entity.Recept
import rs.ac.metropolitan.cs330_dz09.data.entity.Sastojak
import rs.ac.metropolitan.cs330_dz09.data.entity.Torta

@RunWith(AndroidJUnit4::class)
class DBTEST {

    private lateinit var database: AppDatabase
    private lateinit var dao: AppDao

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        dao = database.appDao()
    }


    @After
    fun tearDown() {
        if (::database.isInitialized) {
            database.close()
        }
    }
    @Test
    fun testUpsertAndRetrieve() = runBlocking {
        // Test upsertRecept
        val recept = Recept(naziv = "Recept", uputstvo = "Uputstvo za recept ")
        val receptId = dao.upsertRecept(recept)
        assert(receptId > 0)

        // Test upsertSastojak
        val sastojak = Sastojak(naziv = "Sastojak", kolicina = 3, jedinicaMere = "kom", tortaId = 1)
        val sastojakId = dao.upsertSastojak(sastojak)
        assert(sastojakId > 0)

        // Test upsertTorta
        val torta = Torta(naziv = "Tortica", opis = "Njam tortica", tezina = 2000)
        val tortaId = dao.upsertTorta(torta)
        assert(tortaId > 0)
    }

    @Test
    fun testSearchRecepti() = runBlocking {
        val recept1 = Recept(naziv = "Recept", uputstvo = "Uputstvo za recept 1")
        val recept2 = Recept(naziv = "Recept", uputstvo = "Uputstvo za recept 2")
        dao.upsertRecept(recept1)
        dao.upsertRecept(recept2)

        val searchResults = dao.searchRecepti("Recept")
        assert(searchResults.isNotEmpty())
        assert(searchResults.all { it.naziv.contains("Recept", ignoreCase = true) })
    }

    @Test
    fun testListaTortiSaKomSastojcima() = runBlocking {
        val torta = Torta(naziv = "Tortica", opis = "Njam tortica", tezina = 2000)
        val tortaId = dao.upsertTorta(torta)

        val sastojak = Sastojak(naziv = "Sastojak", kolicina = 3, jedinicaMere = "kom", tortaId = tortaId.toInt())
        dao.upsertSastojak(sastojak)

        val listaTorti = dao.listaTortiSaKomSastojcima()
        assert(listaTorti.isNotEmpty())
        assert(listaTorti.all { it.id.toLong() == tortaId })
    }

    @Test
    fun testListaTortiTezeOdDvaKilograma() = runBlocking {
        val torta = Torta(naziv = "Tortica", opis = "Njam tortica", tezina = 2000)
        val tortaId = dao.upsertTorta(torta)

        val sastojak = Sastojak(naziv = "Sastojak", kolicina = 3000, jedinicaMere = "g", tortaId = tortaId.toInt())
        dao.upsertSastojak(sastojak)

        val listaTorti = dao.listaTortiTezeOdDvaKilograma()
        assert(listaTorti.isNotEmpty())
        assert(listaTorti.all { it.id.toLong() == tortaId })
    }

}
