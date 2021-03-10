<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class="wrapper">

        <form action="" method="POST">

            <table>
                <tr>
                        <td>Nume sofer: </td>
                        <td><input type="text" name="Nume" placeholder="ex. Guzu Titi"></td>
                        <td>CNP: </td>
                        <td><input type="text" name="CNP" placeholder="ex. 1231231231231"></td>
                        <td>Serie Permis: </td>
                        <td><input type="text" name="Serie" placeholder="ex. IS1234"></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" name="submit" value="Adaugare Sofer" class="btn-primary">
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
       $CNP = $_POST['CNP'] ;
       $Serie = $_POST['Serie'] ;
       //sql query
       
       $sql = "INSERT INTO sofer (NUME,cnp,serie_permis) VALUES ('${Nume}','${CNP}','${Serie}')";
       $stid = oci_parse($c, $sql);
       oci_execute($stid);

    }
?>