<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class="wrapper">

        <form action="" method="POST">

            <table>
                <tr>
                        <td>Nume Material: </td>
                        <td><input type="text" name="Nume" placeholder="ex. Teava Inox 80mm"></td>
                        <td>Pret: </td>
                        <td><input type="text" name="Pret" placeholder="ex. 30.50"></td>
                        <td>Cantitate: </td>
                        <td><input type="text" name="Cantitate" placeholder="ex. 1500"></td>
                        <td>Depozit: </td>
                        <td>
                            <select name="Depozit">
                                <?php
                                    $sql = "Select id, nume
                                    from depozit";
                                    $stid = oci_parse($c, $sql);
                                    oci_execute($stid);
                                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                                        $nume_depozit = $row['NUME'];
                                        echo "<option value='$nume_depozit'>$nume_depozit</option>";
                                    }
                                ?>
                            </select>
                        </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" name="submit" value="Adaugare Material" class="btn-primary">
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
       $Pret = $_POST['Pret'] ;
       $Cantitate = $_POST['Cantitate'] ;
       $Depozit = $_POST['Depozit'] ;
       //sql query
       
       $sql = "INSERT INTO material (nume,pret,cantitate,id_depozit) VALUES ('${Nume}','${Pret}','${Cantitate}',(SELECT id FROM depozit WHERE nume='$Depozit'))";
       $stid = oci_parse($c, $sql);
       oci_execute($stid);

    }
?>