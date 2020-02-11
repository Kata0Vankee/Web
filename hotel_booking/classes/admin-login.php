<?php
	$filepath = realpath(dirname(__FILE__));
	include_once ($filepath.'/../lib/session.php');
	Session::checkLogin();
	include_once ($filepath.'/../lib/database.php');
	include_once ($filepath.'/../helpers/format.php');
?>

<?php 
	/**
	 * 
	 */
	class AdminLogin {
		public $db;
		public $fm;

		public function __construct() {
			$this->db = new Database();
			$this->fm = new Format();
		}

		public function check($user, $pass) {
			$user = $this->fm->validation($user);
			$pass = $this->fm->validation($pass);

			if (empty($user) || empty($pass)) {
				$alert = "Khong duoc de trong Tai khoan va Mat khau!!!";
				return $alert;
			} else {
				$query = "SELECT * FROM tbl_admin_login WHERE USERNAME = '$user' AND PASSWORD = '$pass' LIMIT 1;";
				$result = $this->db->select($query);

				if ($result != false) {
					$value = $result->fetch_assoc();
					Session::set('login', true);
					
					Session::set('user', $value['USERNAME']);
					Session::set('id', $value['ID']);
					Session::set('admin_name', $value['NAME']);
					header('Location:../admin/home.php');
				} else {
					$alert = "User and Pass not match";
					return $alert;
				}

			}

		}
	}
?>