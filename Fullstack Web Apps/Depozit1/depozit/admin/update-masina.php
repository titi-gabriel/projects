<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class=wrapper>
        <h1>Update Masina</h1>

        <?php 
            $id = $_GET['id'];

            $sql = "SELECT * from masina";
            $stid = oci_parse($c, $sql);
            oci_execute($stid);

            $row = oci_fetch_assoc($stid);

            $nume = $row['NUMAR_MASINA'];
            $capacitate = $row['CAPACITATE'];
            $id_sofer = $row['ID_SOFER'];

        ?>

        <form action="" method="POST">

            <table>
                <tr>
                        <td>Numar masina: </td>
                        <td><input type="text" name="Nume" value="<?php echo $nume ?>"></td>
                        <td>Capacitate: </td>
                        <td><input type="text" name="Capacitate" value="<?php echo $capacitate ?>"></td>
                        <td>Sofer: </td>
                        <td>
                            <select name="Sofer">
                                <?php
                                    $sql1 = "Select nume from sofer where id in (SELECT id_sofer from masina where id_sofer=$id_sofer)";
                                    $stid1 = oci_parse($c, $sql1);
                                    oci_execute($stid1);
                                    while (($row = oci_fetch_array($stid1, OCI_BOTH)) != false) {
                                        $nume_sofer = $row[0];
                                        
                                        echo "<option value='$nume_sofer'>$nume_sofer</option>";
                                    }

                                    $sqll = "Select s.id, s.nume
                                    from sofer s, (select s.id from sofer s
                                    minus
                                    select m.id_sofer from masina m) m
                                    where s.id = m.id";
                                    $stidd = oci_parse($c, $sqll);
                                    oci_execute($stidd);
                                    while (($row = oci_fetch_array($stidd, OCI_BOTH)) != false) {
                                        $nume_soferr = $row[1];
                                        
                                        echo "<option value='$nume_soferr'>$nume_soferr</option>";
                                    }

                                ?>
                            </select>
                        </td>
                        
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="hidden" name="id" value="<?php echo $id; ?>">
                        <input type="submit" name="submit" value="Update Client" class="btn-primary">
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
        $nume = $_POST['Nume'];
        $capacitate = $_POST['Capacitate'];
        $sofer = $_POST['Sofer'];

        $sql = "UPDATE masina SET
        NUMAR_MASINA = '$nume',
        CAPACITATE = '$capacitate',
        ID_SOFER = (SELECT id FROM sofer WHERE nume='$sofer')
        WHERE ID='$id'";

        $stid = oci_parse($c, $sql);
        oci_execute($stid);
        
    }
?>

<?php include('../partials/footer.php'); ?>