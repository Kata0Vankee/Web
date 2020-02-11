<?php
    include "inc/header.php";
?>
  
    <!-- Breadcrumb Section Begin -->
    <div class="breadcrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <h2>Our Rooms</h2>
                        <div class="bt-option">
                            <a href="index.php">Home</a>
                            <span>Rooms</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section End -->
    <!-- Rooms Section Begin -->
    <section class="rooms-section spad">
        <div class="container">
            <div class="row">
                <?php 
                    $all_rooms = $room->all_rooms();
                    if ($all_rooms) {
                        while ($result = $all_rooms->fetch_assoc()) {
                ?>
                <div class="col-lg-4 col-md-6">
                    <div class="room-item">
                        <img src="img/room/room-1.jpg" alt="">
                        <div class="ri-text">
                            <h4><?php echo $result['ROOMNAME']; ?></h4>
                            <h3><?php echo $result['ROOMPRICE']; ?>đ<span>/Permonth</span></h3>
                            <table>
                                <tbody>
                                    <tr>
                                        <td class="r-o">Size:</td>
                                        <td>30 ft</td>
                                    </tr>
                                    <tr>
                                        <td class="r-o">Max person:</td>
                                        <td><?php echo $result['ROOMMAXP']; ?></td>
                                    </tr>
                                    <tr>
                                        <td class="r-o">Size:</td>
                                        <td><?php echo $result['ROOMSIZE']; ?></td>
                                    </tr>
                                    <tr>
                                        <td class="r-o">Place:</td>
                                        <td><?php echo $result['ROOMPLACE']; ?></td>
                                    </tr>
                                </tbody>
                            </table>
                            <a href="#" class="primary-btn">More Details</a>
                        </div>
                    </div>
                </div>
                <?php
                        }
                    }
                ?>
                <div class="col-lg-12">
                    <div class="room-pagination">
                        <a href="#">1</a>
                        <a href="#">2</a>
                        <a href="#">Next <i class="fa fa-long-arrow-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Rooms Section End -->
    
<?php   
    include "inc/footer.php";
?>