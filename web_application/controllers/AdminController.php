<?php
require_once APPLICATION_PATH.'/models/MenuclassifyModel.php';
require_once APPLICATION_PATH.'/models/MerchantModel.php';
require_once APPLICATION_PATH.'/models/UserModel.php';
require_once APPLICATION_PATH.'/models/OrderModel.php';
require_once APPLICATION_PATH.'/models/MenuModel.php';
require_once 'BaseController.php';

class AdminController extends BaseController
{

	public function init()
	{
		/* Initialize action controller here */
		parent::init();
	}

	public function indexAction()
	{
		// action body
   
	}
	
	public function leftAction()
	{
		// action body
	  
	}
	
	public function topAction()
	{
		// action body
	  
	}
	
	public function menuAction()
	{
		// action body
	  
	}
	
	public function menuaddAction()
	{
		$merchant = new MerchantModel();
		$menuclassify = new MenuclassifyModel();
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		$this->view->sres = $merchant->fetchAll()->toArray();
		$this->view->mres = $menuclassify->fetchAll()->toArray();
		$adapter->query("set charset gbk");
	}
	
	public function doaddmenuAction()
	{
		
		foreach ($_POST as $key => $val)
		{
			$$key = $val;
		}
		$menu = new MenuModel();
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		$data = array("tb_menu_name"=>$name, "tb_menu_price"=>$price,"tb_menu_menuclassify_id"=>$menuclassify,
				"tb_menu_merchant_id"=>$merchant,"tb_menu_description"=>$desc,"tb_menu_stars"=>2,"tb_menu_vote"=>0);
		
		$menu->insert($data);
		$this->_forward("menuadd","admin");
		
		
	}
	
	public function menumanageAction()
	{
		

		$sql = "select * from v_menu ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		$this->view->res = $adapter->query($sql)->fetchAll();
		$adapter->query("set charset gbk");
	  
	}
	
	public function oldordermanageAction()
	{
		$sql = "select * from v_order where tb_order_method = 1 ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		$this->view->res = $adapter->query($sql)->fetchAll();
	  
	}
	
	public function dosendAction()
	{
		$id = $this->getRequest()->getParam("id");
		$order = new OrderModel();
		$order->update(array("tb_order_method"=>1), "tb_order_id=".$id);
		$this->_forward("ordermanage","admin");
	}
	
	public function ordermanageAction()
	{
		$sql = "select * from v_order where tb_order_method = 0 ";
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		$this->view->res = $adapter->query($sql)->fetchAll();
	  
	}
	
	public function rightAction()
	{
		// action body
	  
	}
	
	public function shopAction()
	{
		// action body
	  
	}
	
	public function shopaddAction()
	{
		// action body
	  
	}
	
	public function shopmanageAction()
	{
		// action body
	  	$shop = new MerchantModel();
	  	$adapter = Zend_Db_Table::getDefaultAdapter();
	  	//print_r($adapter);
	  	$adapter->query("set charset utf8");
	  	$this->view->res = $shop->fetchAll()->toArray();
	  	
	}
	
	public function userAction()
	{
		// action body
	  
	}
	
	public function useraddAction()
	{
		// action body
	  
	}
	
	public function usermanageAction()
	{
		// action body
	  
	}
}
?>