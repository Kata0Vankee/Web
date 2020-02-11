<?php 
$filepath = realpath(dirname(__FILE__));
include ($filepath.'/home/inc/header.php');
include ($filepath.'/home/inc/left-slider.php');
include ($filepath.'/../classes/room.php'); 
?>

<?php 
  $result_add_room = "";
  if (isset($_POST['name'])) {
    $new_room = new Room();
    
    $name = $_POST['name'];
    $place = $_POST['place'];
    $price = $_POST['price'];
    $size = $_POST['size'];
    $maxpeople = $_POST['maxpeople'];
    $phone = $_POST['phone'];
    $info = $_POST['info'];

    $result_add_room = $new_room->insert_room($name, $place, $price, $size, $maxpeople, $phone, $info);
  }
?>
<style>
  .new-input{
    width: 40%;
    display: inline-block;
  }
  </style>

<div class="container" style="margin-left: 250px;">
  <div class="container mt-5" style="margin: inherit;">
        <div class="row tm-content-row">        
              <h2 class="tm-block-title" style="margin: auto;">INSERT ROOM
              </h2>
              <h4 style="text-align: center; width: 100%;">
                <?php echo $result_add_room; ?>
              </h4>
        </div>
        <!-- row -->
        <div class="row tm-content-row" style="margin-top: 20px;">
          <div class="tm-block-col tm-col-avatar" style="width: 20%;">
            <div class="tm-bg-primary-dark tm-block tm-block-avatar">
              <h2 class="tm-block-title">Photo</h2>
              <div class="tm-avatar-container" style="height: 200px; border: groove; border-radius: 10px">
                <input id="photo" type="file" name="photo" style="display: none" form="insert" accept="image/*">
                <a href="#" class="tm-avatar-delete-link">
                  <img id="photo_show" style="width: 100%; height: 100%;">
                </a>
              </div>
              <button id="submit_photo" class="btn btn-primary btn-block text-uppercase">
                Upload Photo
              </button>
            </div>
          </div>
          <div class="tm-block-col tm-col-account-settings" style="width: 600px; margin:auto;">
            <div class="tm-bg-primary-dark tm-block tm-block-settings">
              <h2 class="tm-block-title">INSERT ROOM</h2>
              <form id="insert" action="new-room2.php?action=add-room" class="tm-signup-form row" method="POST">
                <div class="form-group col-lg-6">
                  <label for="name">Name:</label>
                  <input
                    id="name"
                    name="name"
                    type="text"
                    class="form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="place">Place:</label>
                  <input
                    id="place"
                    name="place"
                    type="text"
                    class="form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="free-time">Price:</label>
                  <input
                    id="price"
                    name="price"
                    type="text"
                    class="form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="rooms">Size:</label>
                  <input
                    id="size"
                    name="size"
                    type="text"
                    class="form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="owner-phone">Max people:</label>
                  <input
                    id="maxpeople"
                    name="maxpeople"
                    type="text"
                    class="form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="owner-phone">Owner Phone:</label>
                  <input
                    id="phone"
                    name="phone"
                    type="text"
                    class="form-control validate"
                  />
                </div>
                <div style="width: 100%; height: 200px;">
                  <label for="owner-phone">More Informations:</label>
                  <div style="width: 100%; padding-left: 7.5px; padding-right: 7.5px;">
                    <input
                    id="info"
                    name="info"
                    type="text"
                    class="form-control validate input-info"
                  />
                  </div>
                </div>
                
                <div class="col-12">
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
        </div>
      </div>
</div>

<?php include 'home/inc/footer.php'; ?>
