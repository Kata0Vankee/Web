<?php
	include_once 'lib/database.php';
	include_once 'helpers/format.php';
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

		public function add_room($name, $place, $price, $rooms, $maxpeople, $phone, $info) {
			//$name = $this->fm->validation($user);
			//$price = $this->fm->validation($pass);

			if (empty($name) || empty($place) || empty($price) || empty($rooms) || empty($maxpeople) || empty($phone) || empty($info)) {
				$alert = "<span class='add-result-alert red-alert'>Please fill all the input!!!<span>";
				return $alert;
			} else {
				$query = "INSERT INTO tbl_rooms (`ROOMNAME`, `ROOMPLACE`, `ROOMPRICE`, `ROOMROOMS`, `ROOMMAXP`, `ROOMPHONE`, `ROOMINFO`, `ROOMSTATUS`) VALUES ('$name','$place','$price','$rooms','$maxpeople','$phone','$info','Available');";
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