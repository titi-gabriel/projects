<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class="wrapper">

        <form action="" method="POST">

            <table>
                <tr>
                        <td>Nume depozit: </td>
                        <td><input type="text" name="Nume" placeholder="ex. Gara1"></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" name="submit" value="Adaugare Depozit" class="btn-primary">
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

       //sql query
       
       $sql = "INSERT INTO depozit (NUME) VALUES ('${Nume}')";
       $stid = oci_parse($c, $sql);
       oci_execute($stid);

    }
?>