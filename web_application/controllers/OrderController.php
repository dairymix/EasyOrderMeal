<?php
require_once APPLICATION_PATH.'/models/UserModel.php';
require_once APPLICATION_PATH.'/models/OrderModel.php';
require_once APPLICATION_PATH.'/models/MenuModel.php';
require_once 'BaseController.php';
class OrderController extends BaseController{
	private $count = 5;
	
	public function init()
	{
		parent::init();

	}
	
	public function getpagebyuserAction()
	{
		$sql = "select (round(count(*) / ".($this->count + 1).") + 1) cnt from v_order ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		if($this->getRequest()->getParam("id" , 0) != 0)
		{
			$sql.= " where tb_order_user_id = ".$this->getRequest()->getParam("id" );
		}
	
	
	
	
		$res = $adapter->query($sql)->fetchAll();
	
	
		$adapter->query("set charset gbk");
	
	
		echo json_encode($res);
		exit();
	}
	
	public function getorderbyuserAction()
	{
		$count = $this->count;
		$page = 1;
		$sql = "select * from v_order ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		if($this->getRequest()->getParam("id" , 0) != 0)
		{
			$sql.= " where tb_order_user_id = ".$this->getRequest()->getParam("id" );
		}
		$page = $this->getRequest()->getParam("page",1);
		
		//exit();
		$sql = $adapter->limit($sql,$count,($page - 1) * 10);
		
		$res = $adapter->query($sql)->fetchAll();
		$adapter->query("set charset gbk");
		
		
		echo json_encode($res);
		
		exit();
	}
	
	public function doorderAction()
	{
		$userid = $this->getRequest()->getParam("userid");
		$menuid = $this->getRequest()->getParam("menuid");
		$mcount = $this->getRequest()->getParam("count");
		$method = $this->getRequest()->getParam("method",0);
		$ordertime = $this->getRequest()->getParam("time",date("Y-m-d G:i:s"));
		//echo $userid."<br>".$menuid."<br>".$mcount."<br>".$method."<br>".$ordertime."<br>";
		//exit();
		$where = '';
		$order = null;
		$count = 1;
		$offset = 0;
		$user = new UserModel();
		
		$db = $user->getAdapter();
		$where = $db->quoteInto("tb_user_id = ?", $userid);
		
		$res = $user->fetchAll($where,$order,$count,$offset)->toArray();
		
		$status = array("status"=>0);
		
		if(count($res) == 0)
		{
			echo json_encode($status);
			exit();
		}
		//exit();
		
		$menu = new MenuModel();
		$db = $menu->getAdapter();
		$where = $db->quoteInto("tb_menu_id=?",$menuid);
		$res = $menu->fetchAll($where)->toArray();
		
		if(count($res) == 0)
		{
			echo json_encode($status);
			exit();
		}
		$order = new OrderModel();
		$data = array(
				'tb_order_user_id' => $userid,
				'tb_order_menu_id' => $menuid,
				'tb_order_time' => $ordertime,
				'tb_order_method' => $method,
				'tb_order_count' => $mcount
				);
		
		$id = $order->insert($data);
		if($id == 0)
		{
			echo json_encode($status);
			exit();
		}
		
		$status["status"] = 1;
		echo json_encode($status);
		exit();
		
		
		
	}
}