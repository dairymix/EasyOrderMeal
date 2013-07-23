<?php
	class BaseController extends Zend_Controller_Action {
		
		protected $db;
		public function init()
		{
			//��ʼ�����ݿ��������
			session_start();
			$url = constant("APPLICATION_PATH") . DIRECTORY_SEPARATOR . 'configs' . DIRECTORY_SEPARATOR . 'application.ini';
			$dbconfig = new Zend_Config_Ini($url, "mysql");
			$this->db = Zend_Db::factory($dbconfig->db);
			
			$this->db->query("set names utf8");
			Zend_Db_Table::setDefaultAdapter($this->db);
		}
	}