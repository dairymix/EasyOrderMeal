<?php
require_once APPLICATION_PATH.'/models/UserModel.php';
require_once 'BaseController.php';
class UserController extends BaseController{
	public function init()
	{
		parent::init();
		
	}
	
	public function indexAction()
	{
		
	}
	
	public function doregAction()
	{
		$username = $this->getRequest()->getParam("user");
		$password = $this->getRequest()->getParam("pw");
		$sex = $this->getRequest()->getParam("sex",1);
		$telephone = $this->getRequest()->getParam("telephone","0");
		$name = $this->getRequest()->getParam("name","undefined");
		$level = $this->getRequest()->getParam("level",0);
		
		$user = new UserModel();
		$db = $user->getDefaultAdapter();
		$where = $db->quoteInto("tb_user_username like ?", $username);
		$res = $user->fetchAll($where)->toArray();
		if(count($res) > 0)
		{
			echo json_encode(array("status"=>1));
			exit();
		}
		
		$res = $user->insert(array("tb_user_username"=>$username, "tb_user_password" => $password
				,"tb_user_telephone" => $telephone, "tb_user_sex"=>$sex, "tb_user_name"=>$name,
				"tb_user_level"=>$level));
		if($res >0)
		{
			echo json_encode(array("status"=>0));
			exit();
		}
		echo json_encode(array("status"=>2));
		exit();
		
		
	}
	
	public function dologinAction()
	{
		
		$username = $this->getRequest()->getParam("user");
		$password = $this->getRequest()->getParam("pw");
		
		$user = new UserModel();
		$where = '';
		$order = null;
		$count = 1;
		$offset = 0;
		$db = $user->getAdapter();
		$db->query("set charset utf8");
		$where = $db->quoteInto("tb_user_username = ?", $username).$db->quoteInto("and tb_user_password = ?", $password);
		
		$res = $user->fetchAll($where,$order,$count,$offset)->toArray();
		if(count($res) == 0)
		{
			echo json_encode(array());
		}else{
			echo json_encode($res);
		}
		$db->query("set charset gbk");
		exit();
	}
	public function jsonAction(){
		$json_string = $this->getRequest()->getParam("json");
		$obj = json_decode($json_string);
		print_r($obj);
		exit();
	}
}
?>