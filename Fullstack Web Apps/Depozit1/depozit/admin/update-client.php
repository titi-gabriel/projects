<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class=wrapper>
        <h1>Update Client</h1>

        <?php 
            $id = $_GET['id'];

            $sql = "SELECT * from client";
            $stid = oci_parse($c, $sql);
            oci_execute($stid);

            $row = oci_fetch_assoc($stid);

            $nume = $row['NUME'];
            $telefon = $row['TELEFON'];
            $adresa = $row['ADRESA'];

        ?>

        <form action="" method="POST">

            <table>
                <tr>
                        <td>Nume client: </td>
                        <td><input type="text" name="Nume" value="<?php echo $nume ?>"></td>
                        <td>Telefon: </td>
                        <td><input type="text" name="Telefon" value="<?php echo $telefon ?>"></td>
                        <td>Adresa: </td>
                        <td><input type="text" name="Adresa" value="<?php echo $adresa ?>"></td>
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
        $telefon = $_POST['Telefon'];
        $adresa = $_POST['Adresa'];

        $sql = "UPDATE client SET
        NUME = '$nume',
        TELEFON = '$telefon',
        ADRESA = '$adresa'
        WHERE ID='$id'";

        $stid = oci_parse($c, $sql);
        oci_execute($stid);
        
    }
?>

<?php include('../partials/footer.php'); ?>