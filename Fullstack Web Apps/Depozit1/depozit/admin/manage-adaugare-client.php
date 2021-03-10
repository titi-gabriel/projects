<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class="wrapper">

        <form action="" method="POST">

            <table>
                <tr>
                        <td>Nume client: </td>
                        <td><input type="text" name="Nume" placeholder="ex. Guzu Titi"></td>
                        <td>Telefon: </td>
                        <td><input type="text" name="Telefon" placeholder="ex. 0761231234"></td>
                        <td>Adresa: </td>
                        <td><input type="text" name="Adresa" placeholder="ex. Aleea Frunzelor, nr.4"></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" name="submit" value="Adaugare Client" class="btn-primary">
                    </td>
                </tr>
            </table>

        </form>

    </div>
</div>

<?php include('../partials/footer.php'); ?>

<?php 
    //Procesare date din casute si salvare in baza de date
    //verificare buton apasat
    if(isset($_POST['submit']))
    {
       $Nume = $_POST['Nume'] ;
       $Telefon = $_POST['Telefon'] ;
       $Adresa = $_POST['Adresa'] ;
       //sql query
       
       $sql = "INSERT INTO client (NUME,telefon,adresa) VALUES ('${Nume}','${Telefon}','${Adresa}')";
       $stid = oci_parse($c, $sql);
       oci_execute($stid);

    }
?>