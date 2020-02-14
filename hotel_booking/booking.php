<?php
    include "inc/header.php";
?>
<?php 
    $room_id = $_GET['room_id'];
    $room_details = $room->select_room($room_id);
    $result = $room_details->fetch_assoc(); 
?>
    <!-- Breadcrumb Section Begin -->
    <div class="breadcrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <h2>Submit your informations</h2>
                        <div class="bt-option">
                            <a href="./home.php">Home</a>
                            <span>Rooms</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section End -->

    <!-- Room Details Section Begin -->
    <section class="room-details-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-8" style="margin: auto;">
                    <div class="room-details-item">
                        <img src="admin/uploads/<?php echo $result['ROOMPHOTO']; ?>" alt="">
                        <div class="rd-text">
                            <div class="rd-title">
                                <h3><?php echo $result['ROOMNAME']; ?></h3>
                                <div class="rdt-right">
                                    <div class="rating">
                                        <i class="icon_star"></i>
                                        <i class="icon_star"></i>
                                        <i class="icon_star"></i>
                                        <i class="icon_star"></i>
                                        <i class="icon_star-half_alt"></i>
                                    </div>
                                </div>
                            </div>
                            <h2><?php echo $result['ROOMPRICE']; ?><span>/Permonth</span></h2>
                            <table>
                                <tbody>
                                    <tr>
                                        <td class="r-o">Size:</td>
                                        <td><?php echo $result['ROOMSIZE']; ?></td>
                                    </tr>
                                    <tr>
                                        <td class="r-o">Capacity:</td>
                                        <td>Max person <?php echo $result['ROOMMAXP']; ?></td>
                                    </tr>
                                    <tr>
                                        <td class="r-o">Place:</td>
                                        <td><?php echo $result['ROOMPLACE']; ?></td>
                                    </tr>                          
                                </tbody>
                            </table>
                            <p class="f-para">
                                <?php echo $result['ROOMINFO']; ?>
                            </p>
                        </div>
                    </div>
                    
                </div>
                
            </div>
        </div>
    </section>
    <!-- Room Details Section End -->

    <!-- Customer information form begin-->
    <section class="room-details-section spad">
    	<div class="container">
    		<div class="row" style="margin-bottom: 25px; text-align: center;">
    			<label style="font-size: 40px; color: blue; margin:auto;">Confirm your informations</label>
    		</div>
			<div class="row" style="width: 80%; margin: auto;"> 
				<form id="insert" action="booking.php" class="tm-signup-form row" method="POST" enctype="multipart/form-data">
                <div class="form-group col-lg-6">
                  <label for="name">Name:</label>
                  <input
                    id="name"
                    name="name"
                    type="text"
                    class="input-form form-control validate"
                    placeholder="Type your full name"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="date">Date:</label>
                  <input
                    id="date"
                    name="date"
                    type="text"
                    class="input-form form-control validate"
                    placeholder="dd/mm/yy"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="rooms">Place:</label>
                  <input
                    id="place"
                    name="place"
                    type="text"
                    class="input-form form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="phone">Phone</label>
                  <input
                    id="phone"
                    name="phone"
                    type="text"
                    class="input-form form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="email">Email:</label>
                  <input
                    id="email"
                    name="email"
                    type="text"
                    class="input-form form-control validate" 
                    placeholder="ex: example@gmail.com"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="more">More:</label>
                  <input
                    id="more"
                    name="more"
                    type="text"
                    class="input-form form-control validate"
                  />
                </div>              
                <div class="col-4" style="margin: auto; margin-top: 40px;">
                  <button
                    id="submit"
                    type="submit"
                    class="btn btn-primary btn-block text-uppercase"
                  >
                    Submit
                  </button>
                </div>
              </form>
			</div>
    	</div>
    </section>

<?php
    include "inc/footer.php";
?>