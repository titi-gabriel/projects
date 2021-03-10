<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class=wrapper>
        <h1>Update Tranzactie</h1>

        <?php 
            $id = $_GET['id'];

            $sql = "SELECT * from tranzactie";
            $stid = oci_parse($c, $sql);
            oci_execute($stid);

            $row = oci_fetch_assoc($stid);

            $id_client = $row['ID_CLIENT'];
            $id_masina = $row['ID_MASINA'];
            $id_material = $row['ID_MATERIAL'];
            $cantitate = $row['CANTITATE'];
            $data = $row['DATA'];


        ?>

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
                    
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="hidden" name="id" value="<?php echo $id; ?>">
                        <input type="submit" name="submit" value="Update Tranzactie" class="btn-primary">
                    </td>
                </tr>
            </table>

        </form>
    </div>
</div>

<?php
    if(isset($_POST['submit']))
    {
        $id = $_POST['id'];
        $Material = $_POST['Material'];
        $Cantitate = $_POST['Cantitate'] ;
        $Masina = $_POST['Masina'];
        $Client = $_POST['Client'] ;
        $Data = date('d-M-Y', strtotime($_POST['Data'])); 
        

        $sql = "UPDATE tranzactie SET
        ID_CLIENT = (SELECT id FROM client WHERE nume='$Client'),
        ID_MASINA = (SELECT id FROM masina WHERE numar_masina='$Masina'),
        ID_MATERIAL = (SELECT id FROM material WHERE nume='$Material'),
        CANTITATE = '$Cantitate',
        DATA = '$Data'
        WHERE ID='$id'";

        $stid = oci_parse($c, $sql);
        oci_execute($stid);
        
    }
?>

<?php include('../partials/footer.php'); ?>