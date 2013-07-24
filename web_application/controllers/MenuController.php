<?php
require_once APPLICATION_PATH.'/models/MenuclassifyModel.php';
require_once APPLICATION_PATH.'/models/MerchantModel.php';
require_once 'BaseController.php';

class MenuController extends BaseController
{
	
	private $count = 5;

	public function init(){
		parent::init();
		
	}
	public function indexAction()
	{
		 
		 
		// action body
	}
	
	public function getmenusbyidsAction()
	{
		
		$sql = "select * from v_menu ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		
		//处理id啊
		$ids = $this->getRequest()->getParams();
		foreach($ids as $key => $val)
		{
			$$key = $val;
		}
		$count = 0;
		$key = "id".$count;
		if(isset($$key))
		{
			$sql.= " where tb_menu_id = ".$$key;
		}
		$count++;
		$key = "id".$count;
		while(isset($$key))
		{
			$sql.=" or tb_menu_id = ".$$key;
			$count++;
			$key = "id".$count;
		}
		
		$res = $adapter->query($sql)->fetchAll();
		$adapter->query("set charset gbk");
		
		
		echo json_encode($res);
		
		exit();
	}
	
	public function getmenubyidAction()
	{
		$id = $this->getRequest()->getParam("id",0);
		$count = $this->count;
		$page = 1;
		$sql = "select * from v_menu ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		if($id != 0)
		{
			$sql.= " where tb_menu_id = ".$id;
		}
		$res = $adapter->query($sql)->fetchAll();
		$adapter->query("set charset gbk");
		
		
		echo json_encode($res);
		
		exit();
	}
	
	public function getpagebymerAction()
	{
		$sql = "select (round(count(*) / ".($this->count + 1).") + 1) cnt from v_menu ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		if($this->getRequest()->getParam("id" , 0) != 0)
		{
			$sql.= " where tb_menu_merchant_id = ".$this->getRequest()->getParam("id" );
		}
		
		
		
		
		$res = $adapter->query($sql)->fetchAll();
	
		
		$adapter->query("set charset gbk");
		
		
		echo json_encode($res);
		exit();
	}
	
	public function getpagebyclsAction()
	{
		$sql = "select (round(count(*) / ".($this->count + 1).") + 1) cnt from v_menu ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		if($this->getRequest()->getParam("id" , 0) != 0)
		{
			$sql.= " where tb_menu_menuclassify_id = ".$this->getRequest()->getParam("id" );
		}
	
	
	
	
		$res = $adapter->query($sql)->fetchAll();
	
	
		$adapter->query("set charset gbk");
	
	
		echo json_encode($res);
		exit();
	}

	public function getmenubymerAction()
	{
		$count = $this->count;
		$page = 1;
		$sql = "select * from v_menu ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		if($this->getRequest()->getParam("id" , 0) != 0)
		{
			$sql.= " where tb_menu_merchant_id = ".$this->getRequest()->getParam("id" );
		}
		$page = $this->getRequest()->getParam("page",1);	
		
		//exit();
		$sql = $adapter->limit($sql,$count,($page - 1) * 10);
		
		$res = $adapter->query($sql)->fetchAll();
		$adapter->query("set charset gbk");
		
		
		echo json_encode($res);
		
		exit();
	}
	
	public function getmenubyclsAction()
	{
		$count = $this->count;
		$page = 1;
		$sql = "select * from v_menu ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		if($this->getRequest()->getParam("id" , 0) != 0)
		{
			$sql.= " where tb_menu_menuclassify_id = ".$this->getRequest()->getParam("id" );
		}
// 		echo $sql;	
// 		exit();
		$page = $this->getRequest()->getParam("page",1);	
		
		//exit();
		$sql = $adapter->limit($sql,$count,($page - 1) * 10);
		
		$res = $adapter->query($sql)->fetchAll();
	
		$adapter->query("set charset gbk");
	
	
		echo json_encode($res);
	
		exit();
	}
	
	
	public function getclsAction()
	{
		
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		$clsModel = new MenuclassifyModel();
		
		$res =  $clsModel->fetchAll()->toArray();
		
		$adapter->query("set charset gbk");
		echo json_encode($res);
		exit();
	}
	
	public function getmerAction()
	{
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		$merModel = new MerchantModel();
		
		$res =  $merModel->fetchAll()->toArray();
		
		$adapter->query("set charset gbk");
		echo json_encode($res);
		exit();
	}
	
	public function addmerchantAction()
	{
		$mer = new MerchantModel();
		$res = $mer->fetchAll()->toArray();
		$this->view->res = $res;
	}
	
	public function deletemerchantAction()
	{
		$mer = new MerchantModel();
		$id = $this->getRequest()->getParam("id");
		$res = $mer->delete("tb_merchant_id=".$id);
		
		if($res >0)
		{
			echo "<script>alert('删除成功!');</script>";
			$this->_forward("addmerchant","menu");
		}else{
			echo "<script>alert('删除失败!');</script>";
			$this->_forward("addmerchant","menu");
		}
		
	}

	public function doaddmerchantAction()
	{
		$name = $this->getRequest()->getParam("name");
		$address = $this->getRequest()->getParam("address");
		$telephone = $this->getRequest()->getParam("telephone");
		
		$mer = new MerchantModel();
		$res = $mer->insert(array("tb_merchant_name" => $name,
				"tb_merchant_address" => $address,"tb_merchant_telephone" => $telephone));
		
		
		if($res >0)
		{
			echo "<script>alert('添加成功!');</script>";
			$this->_forward("index","admin");
		}else{
			echo "<script>alert('添加失败!');</script>";
			$this->_forward("index","admin");
		}
		
	}
	
	public function addclsAction()
	{
		$name = $this->getRequest()->getParam("name");
	
		$cls = new MenuclassifyModel();
		$res = $cls->insert(array("tb_menuclassify_name" => $name));
	
		if($res >0)
		{
			echo json_encode(array("status"=>0));
			exit();
		}
		echo json_encode(array("status"=>1));
		exit();
	}
	
	public function addmenuAction()
	{
		$name = $this->getRequest()->getParam("name");
		$address = $this->getRequest()->getParam("address");
		$telephone = $this->getRequest()->getParam("telephone");
	
		$mer = new MerchantModel();
		$res = $mer->insert(array("tb_merchant_name" => $name,
				"tb_merchant_address" => $address,"tb_merchant_telephone" => $telephone));
	
		if($res >0)
		{
			echo json_encode(array("status"=>0));
			exit();
		}
		echo json_encode(array("status"=>1));
		exit();
	}
}


?>