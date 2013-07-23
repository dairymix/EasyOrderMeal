<?php


require_once 'BaseController.php';

class RecommendationController extends BaseController
{

	public function init(){
		parent::init();
	}
	public function indexAction()
	{
		 
		 
		// action body
	}
	
	public function getrecAction()
	{
		
		
		$adapter = Zend_Db_Table::getDefaultAdapter();
		//print_r($adapter);
		$adapter->query("set charset utf8");
		
		//exit();
		$res = $adapter->query("select * from v_recommendation")->fetchAll();
		
		$adapter->query("set charset gbk");
		
		
		echo json_encode($res);
		
		exit();
	}
	



}


?>