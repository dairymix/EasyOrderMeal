package com.zncm.EasyOrderMeal.Json;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zncm.EasyOrderMeal.Constants;

public class JsonUtils {
	String BASE_URL = Constants.BASE_URL;
	String USER_AGENT = "Mozilla/4.5";

	/**
	 * 根据传入的参数不同,获服务器返回json数据
	 * 
	 * @throws Exception
	 * @throws JSONException
	 */

	public JSONArray GetInfo(String what_part) throws JSONException, Exception {
		return new JSONArray(GetRequest(BASE_URL + what_part));

	}

	public JSONObject GetInfo2(String what_part) throws JSONException,
			Exception {
		System.out.println("what：　" + BASE_URL + what_part);
		return new JSONObject(GetRequest(BASE_URL + what_part));

	}

	public JSONObject GetAlRecommend(String what_part) throws JSONException,
			Exception {
		return new JSONObject(GetRequest(BASE_URL + what_part));

	}

	public JSONObject Login(String what_part) throws JSONException, Exception {
		return new JSONObject(GetRequest(BASE_URL + what_part));

	}

	/**
	 * 向api发送get请求，url需按照api要求写，返回从服务器取得的信息。
	 * 
	 * @param url
	 * @return String
	 */
	protected String GetRequest(String url) throws Exception {
		return getRequest(url, new DefaultHttpClient(new BasicHttpParams()));
	}

	protected String getRequest(String url, DefaultHttpClient client)
			throws Exception {
		String result = null;
		int statusCode = 0;
		HttpGet getMethod = new HttpGet(url);
		try {
			getMethod.setHeader("User-Agent", USER_AGENT);
			HttpResponse httpResponse = client.execute(getMethod);
			statusCode = httpResponse.getStatusLine().getStatusCode();
			result = retrieveInputStream(httpResponse.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.abort();
		}
		return result;
	}

	protected String retrieveInputStream(HttpEntity httpEntity) {
		Long l = httpEntity.getContentLength();
		int length = (int) httpEntity.getContentLength();
		if (length < 0)
			length = 10000;
		StringBuffer stringBuffer = new StringBuffer(length);
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(
					httpEntity.getContent(), HTTP.UTF_8);
			char buffer[] = new char[length];
			int count;
			while ((count = inputStreamReader.read(buffer, 0, length - 1)) > 0) {
				stringBuffer.append(buffer, 0, count);
			}
		} catch (UnsupportedEncodingException e) {
		} catch (IllegalStateException e) {
		} catch (IOException e) {
		}
		return stringBuffer.toString();
	}

}
