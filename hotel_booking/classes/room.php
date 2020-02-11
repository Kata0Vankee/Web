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

		public function insert_room($data, $file) {
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
		    $permited = array('jpg', 'jpeg', 'png', 'gif');
		    $file_name = $file['image']['name'];
		    $file_size = $file['image']['size'];
		    $file_temp = $file['image']['tmp_name'];

		    $div = explode('.', $file_name);

			if (empty($name) || empty($place) || empty($price) || empty($size) || empty($maxpeople) || empty($phone) || empty($info)) {
				$alert = "<span class='add-result-alert red-alert'>Please fill all the input!!!<span>";
				return $alert;
			} else {
				$query = "INSERT INTO tbl_rooms (`ROOMNAME`, `ROOMPLACE`, `ROOMPRICE`, `ROOMSIZE`, `ROOMMAXP`, `ROOMPHONE`, `ROOMINFO`, `ROOMSTATUS`) VALUES ('$name','$place','$price','$size','$maxpeople','$phone','$info','Available');";
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