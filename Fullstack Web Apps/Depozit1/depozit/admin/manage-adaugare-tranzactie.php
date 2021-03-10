<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class="wrapper">

        <form action="" method="POST">

            <table>
                <tr>
                    
                        <td>Client: </td>
                        <td>
                            <select name="Client">
                                <?php
                                    $sql = "Select id, nume
                                    from client";
                                    $stid = oci_parse($c, $sql);
                                    oci_execute($stid);
                                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                                        $nume_client = $row['NUME'];
                                        echo "<option value='$nume_client'>$nume_client</option>";
                                    }
                                ?>
                            </select>
                        </td>

                        <td>Masina: </td>
                        <td>
                            <select name="Masina">
                                <?php
                                    $sql = "Select id,numar_masina
                                    from masina";
                                    $stid = oci_parse($c, $sql);
                                    oci_execute($stid);
                                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                                        $Numar_masina = $row[1];
                                        echo "<option value='$Numar_masina'>$Numar_masina</option>";
                                    }
                                ?>
                            </select>
                        </td>

                        <td>Material: </td>
                        <td>
                            <select name="Material">
                                <?php
                                    $sql = "Select id, nume
                                    from material";
                                    $stid = oci_parse($c, $sql);
                                    oci_execute($stid);
                                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                                        $material = $row[1];
                                        echo "<option value='$material'>$material</option>";
                                    }
                                ?>
                            </select>
                        </td>
                    
                        <td>Cantitate: </td>
                        <td><input type="text" name="Cantitate" placeholder="ex. 1500"></td>

                        <td>Data: </td>
                        <td><input type="date" name="Data"></td>
                        
                        <!--
                        <td><input type="text" name="Data" placeholder="ex: 01-JAN-2020"></td>    
                        <td><input type="date" name="Data"></td> -->
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" name="submit" value="Adaugare Tranzactie" class="btn-primary">
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
       $Material = $_POST['Material'];
       $Cantitate = $_POST['Cantitate'] ;
       $Masina = $_POST['Masina'];
       $Client = $_POST['Client'] ;
       $Data = date('d-M-Y', strtotime($_POST['Data'])); 
       
       
       //sql query
       
       $sql = "INSERT INTO tranzactie (id_client,id_masina,id_material,cantitate,data) VALUES ((SELECT id FROM client WHERE nume='$Client'),(SELECT id FROM masina WHERE numar_masina='$Masina'),(SELECT id FROM material WHERE nume='$Material'),${Cantitate},'${Data}')";
       $stid = oci_parse($c, $sql);
       oci_execute($stid);

    }
?>