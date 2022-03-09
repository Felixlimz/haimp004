# Toshokan ( "Perpustakaan" )
Aplikasi Management Library berbasis Web

## Preparation  :

Step 1 -> Make table in database
notes : in developtment process we using MySQL, if using other database maybe need some configuration later

Step 2 -> Configure database information at 'src -> main -> resources -> application '

spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:<database.mysql.port/table_name>
spring.datasource.username=<database.username>
spring.datasource.password=<database.password>

Step 3 -> Run the program
notes : the migration and seeder will automatically run

## Fitur Admin :

- Melihat, menghapus dan menambahkan daftar pinjaman buku.
- Melihat, mengedit, menambahkan daftar buku
- Melihat, mengedit, menambahkan daftar kategori
- Melihat, mengedit, menambahkan USER dengan password default “12345678”
- Mengubah password

## Fitur User :

- Melihat daftar buku yang tersedia.
- Melihat daftar kategori yang tersedia.
- Mengubah password

## Dependency :

- Spring Framework
- Spring Web
- Thymeleaf
- Thymeleaf Security5
- Spring Security
- Spring Data JPA
- Bootstrap + Thymeleaf Layout

