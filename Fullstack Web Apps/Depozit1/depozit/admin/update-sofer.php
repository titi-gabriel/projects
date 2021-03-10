<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class=wrapper>
        <h1>Update Sofer</h1>

        <?php 
            $id = $_GET['id'];

            $sql = "SELECT * from sofer";
            $stid = oci_parse($c, $sql);
            oci_execute($stid);

            $row = oci_fetch_assoc($stid);

            $nume = $row['NUME'];
            $cnp = $row['CNP'];
            $serie = $row['SERIE_PERMIS'];

        ?>

        <form action="" method="POST">

            <table>
                <tr>
                        <td>Nume sofer: </td>
                        <td><input type="text" name="Nume" value="<?php echo $nume ?>"></td>
                        <td>CNP: </td>
                        <td><input type="text" name="CNP" value="<?php echo $cnp ?>"></td>
                        <td>Serie permis: </td>
                        <td><input type="text" name="Serie" value="<?php echo $serie ?>"></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="hidden" name="id" value="<?php echo $id; ?>">
                        <input type="submit" name="submit" value="Update Sofer" class="btn-primary">
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
        $cnp = $_POST['CNP'];
        $serie = $_POST['Serie'];
        

        $sql = "UPDATE sofer SET
        NUME = '$nume',
        CNP = '$cnp',
        SERIE_PERMIS = '$serie'
        WHERE ID='$id'";

        $stid = oci_parse($c, $sql);
        oci_execute($stid);
        
    }
?>

<?php include('../partials/footer.php'); ?>