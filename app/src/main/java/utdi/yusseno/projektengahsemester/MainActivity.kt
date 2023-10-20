// Package yang mengelompokkan kelas-kelas dan fungsi-fungsi terkait aplikasi.
package utdi.yusseno.projektengahsemester

// Import library yang diperlukan untuk pengembangan aplikasi Android.
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import utdi.yusseno.projektengahsemester.ui.theme.ProjekTengahSemesterTheme

// Kelas utama aktivitas yang merupakan titik masuk (entry point) aplikasi Android.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set tata letak kompos menggunakan Compose.
        setContent {
            // Menggunakan tema dari Material Design untuk UI yang konsisten.
            ProjekTengahSemesterTheme {
                // Wadah utama aplikasi dengan latar belakang sesuai tema.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Daftar pemain dan staff dengan data statis.
                    val goalkeeper = listOf(
                        Player("Allison backer", "Goalkeeper", R.drawable.alison),
                        Player("Adrian", "Goalkeeper", R.drawable.adrian),
                        Player("Caoimhin Kelleher", "Goalkeeper", R.drawable.kelleher)
                    )
                    val defender = listOf(
                        Player("Joe Gomez", "Defender", R.drawable.gomez),
                        Player("Virgil van Dijk", "Defender", R.drawable.virgil),
                        Player("Ibrahima Konate", "Defender", R.drawable.konate)
                    )
                    val midfielder = listOf(
                        Player("Wataru Endo", "Midfielder", R.drawable.endo),
                        Player("Dominik Szoboszlai", "Midfielder", R.drawable.dominik),
                        Player("Alexis Mac Allister", "Midfielder", R.drawable.alister)
                    )
                    val forward = listOf(
                        Player("Luis Diaz", "Forward", R.drawable.diaz),
                        Player("Darwin Nunez", "Forward", R.drawable.nunez),
                        Player("Mohamed Salah", "Forward", R.drawable.salah)
                    )
                    val staff = listOf(
                        Player("Jürgen Klopp", "Manager", R.drawable.kloop),
                        Player("Pepijn Lijnders", "Assistant Manager", R.drawable.pepijn),
                        Player("Peter Krawietz", "Assistant Manager", R.drawable.peter),
                        Player("Jonathan Power", "Club Doctor", R.drawable.power),
                        Player("Jordan Fairclough", "Assistant Fitness Manager ", R.drawable.jordan)
                    )

                    // Tampilkan halaman dengan daftar pemain dan staff.
                    Page(goalkeeper = goalkeeper, defender = defender, midfielder = midfielder, forward = forward, staff = staff)
                }
            }
        }
    }
}

// Data class untuk merepresentasikan pemain atau staff.
data class Player(val name: String, val position: String, val imageResource: Int)

// Komponen untuk menampilkan item pemain.
@Composable
fun PlayerItem(player: Player) {
    // Komponen Column digunakan untuk menyusun elemen secara vertikal.
    Column(
        modifier = Modifier
            .padding(16.dp) // Memberi jarak antara elemen dengan padding.
            .fillMaxWidth() // Mengisi lebar maksimum dari parent.
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)), // Menambahkan border dengan sudut melengkung.
        horizontalAlignment = Alignment.CenterHorizontally // Mengatur penempatan horizontal elemen di tengah.
    ) {
        // Menampilkan gambar pemain dari resource dengan aspek rasio 1:1.
        Image(
            painter = painterResource(id = player.imageResource), // Mengambil gambar dari resource.
            contentDescription = null,
            modifier = Modifier
                .size(240.dp) // Mengatur ukuran gambar.
                .clip(shape = RoundedCornerShape(8.dp)) // Menggunakan sudut melengkung pada gambar.
                .aspectRatio(1f), // Menjaga proporsi gambar.
            contentScale = ContentScale.Crop // Memotong gambar jika ukurannya tidak cocok.
        )

        // Menampilkan detail pemain seperti nama dan posisi.
        Text(text = player.name, fontWeight = FontWeight.Bold, fontSize = 18.sp) // Menampilkan nama dengan tebal dan ukuran font tertentu.
        Spacer(modifier = Modifier.height(8.dp)) // Memberi jarak antara elemen dengan spacer.
    }
}

// Komponen untuk menampilkan item staff dengan nama dan posisi.
@Composable
fun StaffItem(player: Player) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = player.imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(240.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )

        Text(text = player.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = player.position, fontSize = 16.sp) // Menampilkan posisi dengan ukuran font tertentu.
        Spacer(modifier = Modifier.height(8.dp))
    }
}

// Komponen untuk menampilkan daftar pemain atau staff dalam satu baris.
@Composable
fun PlayerList(players: List<Player>) {
    LazyRow {
        items(players) { player ->
            // Menampilkan detail pemain.
            PlayerItem(player = player)
        }
    }
}

// Komponen untuk menampilkan judul halaman dengan logo dan teks.
@Composable
fun Header(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Menampilkan logo di sini. (Ganti R.drawable.logo dengan ID gambar logo Anda)
        Image(
            painter = painterResource(id = R.drawable.liverpoolfc),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp) // Mengatur ukuran logo.
                .padding(end = 8.dp) // Memberi jarak antara logo dan teks.
        )

        // Menampilkan teks judul halaman dengan format dan ukuran font tertentu.
        Text(
            text = "Liverpool in 2023-2024",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 26.sp)
        )
    }
}

// Komponen untuk menampilkan posisi goalkeeper.
@Composable
fun PositionGoalkeeper(name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Goalkeeper") // Menampilkan teks posisi.
    }
}

// Komponen untuk menampilkan posisi defender.
@Composable
fun PositionDefender(name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Defender") // Menampilkan teks posisi.
    }
}

// Komponen untuk menampilkan posisi midfielder.
@Composable
fun PositionMidfielder(name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Midfielder") // Menampilkan teks posisi.
    }
}

// Komponen untuk menampilkan posisi striker.
@Composable
fun PositionForward(name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Forward") // Menampilkan teks posisi.
    }
}

// Komponen untuk menampilkan halaman utama dengan daftar pemain dan staff.
@Composable
fun Page(goalkeeper: List<Player>, defender: List<Player>, midfielder: List<Player>, forward: List<Player>, staff: List<Player>) {
    // State untuk memantau apakah pemain atau staff yang sedang ditampilkan.
    var isShowingPlayers by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Memberi jarak antara elemen dengan padding.
        verticalArrangement = Arrangement.Top, // Mengatur penempatan elemen di atas.
        horizontalAlignment = Alignment.CenterHorizontally // Mengatur penempatan horizontal elemen di tengah.
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp)) // Memberi jarak di atas halaman.
        }
        item {
            // Menampilkan judul halaman dengan teks kosong.
            Header("")
        }

        // Menambahkan tombol untuk beralih antara pemain dan staff.
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), // Memberi jarak di bawah tombol.
                horizontalArrangement = Arrangement.SpaceEvenly // Mengatur jarak antara tombol.
            ) {
                // Tombol untuk menampilkan pemain.
                Button(onClick = { isShowingPlayers = true }) {
                    Text(text = "Players")
                }
                // Tombol untuk menampilkan staff.
                Button(onClick = { isShowingPlayers = false }) {
                    Text(text = "Staff")
                }
            }
        }

        // Menampilkan daftar pemain atau staff tergantung pada tombol yang ditekan.
        if (isShowingPlayers) {
            item {
                // Menampilkan posisi goalkeeper.
                PositionGoalkeeper("")
            }

            item {
                // Menampilkan daftar pemain untuk posisi goalkeeper.
                PlayerList(players = goalkeeper)
            }

            item {
                // Menampilkan posisi defender.
                PositionDefender("")
            }

            item {
                // Menampilkan daftar pemain untuk posisi defender.
                PlayerList(players = defender)
            }

            item {
                // Menampilkan posisi midfielder.
                PositionMidfielder("")
            }

            item {
                // Menampilkan daftar pemain untuk posisi midfielder.
                PlayerList(players = midfielder)
            }

            item {
                // Menampilkan posisi striker.
                PositionForward("")
            }

            item {
                // Menampilkan daftar pemain untuk posisi striker.
                PlayerList(players = forward)
            }
        } else {
            // Menampilkan daftar staff.
            items(staff) { player ->
                StaffItem(player = player)
            }
        }
    }
}

// Pratinjau tampilan halaman.
@Preview(showBackground = true)
@Composable
fun PagePreview() {
    ProjekTengahSemesterTheme {
        // Data pemain dan staff untuk pratinjau.
        val goalkeeper = listOf(
            Player("Allison backer", "Goalkeeper", R.drawable.alison),
            Player("Adrian", "Goalkeeper", R.drawable.adrian),
            Player("Caoimhin Kelleher", "Goalkeeper", R.drawable.kelleher)
        )

        val defender = listOf(
            Player("Joe Gomez", "Defender", R.drawable.gomez),
            Player("Virgil van Dijk", "Defender", R.drawable.virgil),
            Player("Ibrahima Konate", "Defender", R.drawable.konate)
        )

        val midfielder = listOf(
            Player("Wataru Endo", "Midfielder", R.drawable.endo),
            Player("Dominik Szoboszlai", "Midfielder", R.drawable.dominik),
            Player("Alexis Mac Allister", "Midfielder", R.drawable.alister)
        )

        val forward = listOf(
            Player("Luis Diaz", "Forward", R.drawable.diaz),
            Player("Darwin Nunez", "Forward", R.drawable.nunez),
            Player("Mohamed Salah", "Forward", R.drawable.salah)
        )

        val staff = listOf(
            Player("Jürgen Klopp", "Manager", R.drawable.kloop),
            Player("Pepijn Lijnders", "Assistant Manager", R.drawable.pepijn),
            Player("Peter Krawietz", "Assistant Manager", R.drawable.peter),
            Player("Jonathan Power", "Club Doctor", R.drawable.power),
            Player("Jordan Fairclough", "Assistant Fitness Manager ", R.drawable.jordan)
        )

        // Menampilkan pratinjau halaman dengan daftar pemain dan staff.
        Page(goalkeeper = goalkeeper, defender = defender, midfielder = midfielder, forward = forward, staff = staff)
    }
}

// Keterangan Tambahan:

// 1. **Package dan Import:** Di dalam kode, digunakan paket `utdi.yusseno.projektengahsemester`
//    yang mengelompokkan kelas-kelas dan fungsi-fungsi terkait aplikasi. Import-import juga digunakan
//    untuk mengakses kelas-kelas dan fungsi-fungsi yang ada di dalam library Android.

// 2. **MainActivity:** Kelas ini adalah kelas utama aplikasi yang mewarisi dari `ComponentActivity`.
//    Di dalamnya terdapat fungsi `onCreate` yang merupakan titik masuk aplikasi. Di dalam fungsi ini,
//    dipersiapkan tata letak menggunakan Compose dan ditampilkan daftar pemain dan staff menggunakan komponen `Page`.

// 3. **Data Class Player:** Didefinisikan data class `Player` untuk merepresentasikan informasi pemain dan staff
//    seperti nama, posisi, dan sumber gambar (dalam bentuk ID resource).

// 4. **Komponen PlayerItem:** Komponen ini digunakan untuk menampilkan detail pemain. Menampilkan gambar pemain,
//    nama pemain (dengan tebal), dan memberi jarak dengan elemen lain menggunakan `Spacer`.

// 5. **Komponen StaffItem:** Komponen ini mirip dengan `PlayerItem` namun digunakan untuk menampilkan staff.
//    Menampilkan gambar staff, nama staff (dengan tebal), posisi staff, dan memberi jarak dengan elemen lain menggunakan `Spacer`.

// 6. **Komponen PlayerList:** Komponen ini menampilkan daftar pemain atau staff dalam satu baris secara horizontal
//    menggunakan `LazyRow`. Setiap elemen dipetakan ke dalam `PlayerItem` atau `StaffItem` sesuai dengan kondisi.

// 7. **Komponen Header:** Komponen ini menampilkan judul halaman dengan logo klub (gambar diambil dari resource)
//    dan teks "Liverpool in 2023-2024".

// 8. **Komponen PositionGoalkeeper, PositionDefender, PositionMidfielder, PositionForward:**
//    Komponen-komponen ini menampilkan teks posisi seperti "Goalkeeper", "Defender", "Midfielder", "Forward".

// 9. **Komponen Page:** Ini adalah komponen utama yang menampilkan halaman dengan pemain dan staff.
//    Terdapat tombol "Players" dan "Staff" untuk beralih antara mode tampilan pemain dan staff.
//    Di dalamnya, terdapat komponen-komponen sebelumnya yang diatur secara berurutan.

// 10. **Pratinjau PagePreview:** Ini adalah pratinjau tampilan halaman menggunakan data pemain dan staff
//    yang didefinisikan di dalamnya. Hal ini memungkinkan pengembang untuk melihat tampilan tanpa harus menjalankan aplikasi.

// Implementasi Materi:
// - **Tata Letak (Layout):** Menggunakan komponen Column, Row, dan LazyRow untuk menyusun elemen secara vertikal dan horizontal.
// - **Element UI (Scaffold, Text, Image):** Menggunakan elemen UI seperti Text dan Image untuk menampilkan teks dan gambar.
// - **Element UI Input (Button):** Menggunakan elemen UI Button untuk membuat tombol "Players" dan "Staff".
// - **Scrollable List (LazyRow, LazyColumn):** Menggunakan komponen LazyRow dan LazyColumn untuk menampilkan daftar pemain dan staff yang dapat discroll.
// - **UI dan State:** Menggunakan mutableStateOf untuk memantau apakah tampilan yang sedang ditampilkan adalah pemain atau staff.

// Pada implementasi ini, penggunaan komponen-komponen tersebut menghasilkan antarmuka yang user-friendly
// dan memungkinkan pengguna untuk dengan mudah menjelajahi daftar pemain dan staff Liverpool FC di musim 2023-2024.


