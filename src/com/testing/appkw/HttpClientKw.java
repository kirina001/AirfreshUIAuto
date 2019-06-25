package com.testing.appkw;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;


public class HttpClientKw {

	// 匹配unicode编码格式的正则表达式。
	private static final Pattern reUnicode = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");

	/**
	 * 查找字符串中的unicode编码并转换为中文。
	 * @param u
	 * @return
	 */
	public String DeCode(String u) {
		try {
			Matcher m = reUnicode.matcher(u);
			StringBuffer sb = new StringBuffer(u.length());
			while (m.find()) {
				m.appendReplacement(sb, Character.toString((char) Integer.parseInt(m.group(1), 16)));
			}
			m.appendTail(sb);
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return u;
		}
	}
	
	/**
	 * SSLcontext用于绕过ssl验证，使发包的方法能够对https的接口进行请求。
	 */
	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");

		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}

	/**
	 * 通过httpclient实现get方法
	 * @param url 接口的url地址
	 * @param param 接口的参数列表。
	 */
	public String doGet(String url, String param) throws Exception {

		String body = "";

		// 采用绕过验证的方式处理https请求
		SSLContext sslcontext = createIgnoreVerifySSL();

		// 设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sslcontext)).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//		HttpClients.custom().setConnectionManager(connManager);

		// 创建自定义的httpclient对象
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
		// CloseableHttpClient client = HttpClients.createDefault();

		try {
			//拼接url和param，形成最终请求的url格式。
			String urlWithParam = "";
			if (param.length() > 0){
				urlWithParam = url + "?" + param;
				}
			else {
				urlWithParam = url;
			}
			// 创建get方式请求对象
			HttpGet get = new HttpGet(urlWithParam);

			// 指定报文头Content-type、User-Agent
			get.setHeader("accept", "*/*");
			get.setHeader("Content-type", "application/x-www-form-urlencoded");
			get.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");

			// httpclient执行httpget方法。
			CloseableHttpResponse response = client.execute(get);

			// 获取结果实体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity, "UTF-8");
			}
			// 关闭流实体
			EntityUtils.consume(entity);
			// 释放链接
			response.close();
			String result = DeCode(body);
			System.out.println("result:" + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		return null;
	}

	
	/**
	 * 通过httpclient实现post方法
	 * @param url 接口的url地址
	 * @param param 接口的参数列表。
	 */	
	public String doPost(String url, String param) throws Exception {
		String body = "";
		
		// 采用绕过验证的方式处理https请求
		SSLContext sslcontext = createIgnoreVerifySSL();
		// 设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sslcontext)).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		HttpClients.custom().setConnectionManager(connManager);

		// 创建自定义的httpclient对象
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
		// CloseableHttpClient client = HttpClients.createDefault();

		try {
			String urlWithParam = "";
			if (param.length() > 0){
				urlWithParam = url + "?" + param;
				}
			else {
				urlWithParam = url;
			}
			// 创建post方式请求对象
			HttpPost httpPost = new HttpPost(urlWithParam);

			// 指定报文头Content-type、User-Agent
			httpPost.setHeader("accept", "*/*");
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			httpPost.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");

			
			// 执行请求操作，并拿到结果
			CloseableHttpResponse response = client.execute(httpPost);

			// 获取结果实体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity, "UTF-8");
			}

			EntityUtils.consume(entity);
			// 释放链接
			response.close();
			String result = DeCode(body);
			System.out.println("body:" + body);
			System.out.println("result:" + result);
			return result;
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			client.close();
		}
		return null;
	}

	
	
}
