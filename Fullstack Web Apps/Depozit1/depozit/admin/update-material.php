<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class=wrapper>
        <h1>Update Material</h1>

        <?php 
            $id = $_GET['id'];

            $sql = "SELECT * from material";
            $stid = oci_parse($c, $sql);
            oci_execute($stid);

            $row = oci_fetch_assoc($stid);

            $nume = $row['NUME'];
            $pret = $row['PRET'];
            $cantitate = $row['CANTITATE'];
            $id_depozit = $row['ID_DEPOZIT'];

        ?>

        <form action="" method="POST">

            <table>
                <tr>
                        <td>Nume material: </td>
                        <td><input type="text" name="Nume" value="<?php echo $nume ?>"></td>
                        <td>Pret: </td>
                        <td><input type="text" name="Pret" value="<?php echo $pret ?>"></td>
                        <td>Cantitate: </td>
                        <td><input type="text" name="Cantitate" value="<?php echo $cantitate ?>"></td>
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
                        <input type="hidden" name="id" value="<?php echo $id; ?>">
                        <input type="submit" name="submit" value="Update Material" class="btn-primary">
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
        $pret = $_POST['Pret'];
        $cantitate = $_POST['Cantitate'];
        $depozit = $_POST['Depozit'];

        $sql = "UPDATE material SET
        NUME = '$nume',
        CANTITATE = '$cantitate',
        PRET = '$pret',
        ID_DEPOZIT = (SELECT id FROM depozit WHERE nume='$depozit')
        WHERE ID='$id'";

        $stid = oci_parse($c, $sql);
        oci_execute($stid);
        
    }
?>

<?php include('../partials/footer.php'); ?>