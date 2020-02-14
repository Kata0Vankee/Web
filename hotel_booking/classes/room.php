<?php
	$filepath = realpath(dirname(__FILE__));
	include_once ($filepath.'/../lib/database.php');
	include_once ($filepath.'/../helpers/format.php');
?>

<?php 
	/**
	 * 
	 */
	class Room {
		public $db;
		public $fm;

		public function __construct() {
			$this->db = new Database();
			$this->fm = new Format();
		}

		public function insert_room($data, $files) {
			//$name = $this->fm->validation($user);
			//$price = $this->fm->validation($pass);
			$name = $data['name'];
		    $place = $data['place'];
		    $price = $data['price'];
		    $size = $data['size'];
		    $maxpeople = $data['maxpeople'];
		    $phone = $data['phone'];
		    $info = $data['info'];

		    //Xu li hinh anh		    
			if (empty($name) || empty($place) || empty($price) || empty($size) || empty($maxpeople) || empty($phone) || empty($info) || !isset($files)) {
				$alert = "<span class='add-result-alert red-alert'>Please fill all the input!!!<span>";
				return $alert;
			} else {

				$permited = array('jpg', 'jpeg', 'png', 'gif');
			    $file_name = $files['photo']['name'];
			    $file_size = $files['photo']['size'];
			    $file_temp = $files['photo']['tmp_name'];
			    $error = $files['photo']['error'];
			    
			    $div = explode('.', $file_name);
			    $file_ext = strtolower(end($div));
			    $unique_image = substr(md5(time()), 0, 10).'.'.$file_ext;
			    echo "<script>console.log($error)</script>";
			    $upload_image = "uploads/".$unique_image;
				move_uploaded_file($file_temp, $upload_image);

				$query = "INSERT INTO tbl_rooms (`ROOMNAME`, `ROOMPLACE`, `ROOMPRICE`, `ROOMSIZE`, `ROOMMAXP`, `ROOMPHONE`, `ROOMINFO`, `ROOMSTATUS`, `ROOMPHOTO`) VALUES ('$name','$place','$price','$size','$maxpeople','$phone','$info','Available','$unique_image');";
				$result = $this->db->insert($query);

				if ($result != false) {
					$alert = "<span class='add-result-alert green-alert'>Insert successfully<span>";
					return $alert;
				} else {
					$alert = "<span class='add-result-alert red-alert'>Can not insert<span>";
					return $alert;
				}
			}
		}

		public function all_rooms() {
			$query = "SELECT * FROM tbl_rooms ORDER BY ROOMID DESC;";
			$result = $this->db->select($query);
			return $result;
		}

		public function select_room($id) {
			$query = "SELECT * FROM tbl_rooms WHERE ROOMID='$id' LIMIT 1;";
			$result = $this->db->select($query);
			return $result;
		}

	}
?>