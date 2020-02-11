<?php 
$filepath = realpath(dirname(__FILE__));
include ($filepath.'/home/inc/header.php'); 
include ($filepath.'/home/inc/left-slider.php');
include ($filepath.'/../classes/room.php'); 
?>
<?php
	$rooms = new Room();
?>

<div class="tm-bg-primary-dark tm-block tm-block-products" style="padding-left: 250px;">
			<div style="text-align: auto;"">
				<h1>All Rooms</h1>
			</div>
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table">
                <thead>
                  <tr>
                    <th scope="col">&nbsp;</th>
                    <th scope="col">ROOM NAME</th>
                    <th scope="col">PRICE</th>
                    <th scope="col">SIZE</th>
                    <th scope="col">PLACE</th>
                    <th scope="col">STATUS</th>
                    <th scope="col">&nbsp;</th>
                    <th scope="col">&nbsp;</th>
                  </tr>
                </thead>
                <tbody>
                	<?php
					$show_all = $rooms->all_rooms();
					if ($show_all) {
						$id_show = 0;
						while ($result = $show_all->fetch_assoc()) {
							$id_show++;
					?>
                  	<tr>
	                    <th scope="row"><input type="checkbox" /></th>
	                    <td class="tm-product-name"><?php echo $result['ROOMNAME']; ?></td>
	                    <td><?php echo $result['ROOMPRICE']; ?></td>
	                    <td><?php echo $result['ROOMSIZE']; ?></td>
	                    <td><?php echo $result['ROOMPLACE']; ?></td>
	                    <td 
	                    <?php
	                    	if ($result['ROOMSTATUS'] == 'Available') {
	                    		echo 'style="color: green;"';
	                    	} else {
	                    		echo 'style="color: red;"';
	                    	}
	                    ?>><?php echo $result['ROOMSTATUS']; ?></td>
	                    <td>
	                    	<a href="edit-room.php?room_id=<?php echo $result['ROOMID']; ?>">Edit</a>
	                    </td>
	                    <td>
		                    <a href="#" class="tm-product-delete-link">
		                     	<i class="far fa-trash-alt tm-product-delete-icon"></i>
		                    </a>
	                    </td>
                  	</tr>
                  	<?php
                  		}
                  	}
                  	?>
                  
                </tbody>
              </table>
            </div>
            <!-- table container -->
            <a
              href="new-room.php"
              class="btn btn-primary btn-block text-uppercase mb-3">Add new rooms</a>
            <button class="btn btn-primary btn-block text-uppercase">
              Delete selected rooms
            </button>
          </div>

<?php include 'home/inc/footer.php'; ?>