package com.atomic.get;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;



public class HttpClientLogin {
	// Post方法，模拟表单提交参数登录到网站。
    // 结合了上面两个方法：grabPageHTML/downloadFile，同时增加了Post的代码。
    public void login2Lashou() throws Exception {
        // 第一步：先下载验证码到本地
//        String url = "http://www.mi.com/";
//        String destfilename = "D:\\TDDOWNLOAD\\yz.png";
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpGet httpget = new HttpGet(url);
//        File file = new File(destfilename);
//        if (file.exists()) {
//            file.delete();
//        }
//        
//        HttpResponse response = httpclient.execute(httpget);
//        HttpEntity entity = response.getEntity();
//        InputStream in = entity.getContent();
//        try {
//            FileOutputStream fout = new FileOutputStream(file);
//            int l = -1;
//            byte[] tmp = new byte[2048]; 
//            while ((l = in.read(tmp)) != -1) {
//                fout.write(tmp);
//            } 
//            fout.close();
//        } finally {
//            in.close();
//        }
//        httpget.releaseConnection();
//
//        
//        // 第二步：用Post方法带若干参数尝试登录，需要手工输入下载验证码中显示的字母、数字
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("请输入下载下来的验证码中显示的数字...");
//        String yan = br.readLine();
    	HttpClient httpClient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("https://account.xiaomi.com/pass/serviceLogin");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("miniLogin_username", "13476015997"));
        params.add(new BasicNameValuePair("miniLogin_pwd", "kevin523523"));
      //将POST参数以UTF-8编码并包装成表单实体对象  
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));  
        //打印请求地址  
        System.out.println("executing request " + httppost.getRequestLine().getUri());  
        ResponseHandler<String> responseHandler = new BasicResponseHandler();  
        //执行请求并获取结果  
        String responseBody = httpClient.execute(httppost, responseHandler);  
        System.out.println("----------------------------------------");  
        System.out.println(responseBody);  
        System.out.println("----------------------------------------");  
        // 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断登录是否成功
//        String postResult = EntityUtils.toString(entity, "GBK"); 
//        // 我们这里只是简单的打印出当前Cookie值以判断登录是否成功。
//        List<Cookie> cookies = ((AbstractHttpClient)httpclient).getCookieStore().getCookies();
//        for(Cookie cookie: cookies)
//            System.out.println(cookie);
        httppost.releaseConnection();

        
        // 第三步：打开会员页面以判断登录成功（未登录用户是打不开会员页面的）
//        String memberpage = "http://www.lashou.com/account/orders/";
//        httpget = new HttpGet(memberpage);
//        response = httpclient.execute(httpget); // 必须是同一个HttpClient！
//        entity = response.getEntity();
//        String html = EntityUtils.toString(entity, "GBK");
//        httpget.releaseConnection();
//
//        System.out.println(html);
    }
    
    public static void main(String[] args) throws Exception {
		new HttpClientLogin().login2Lashou();
		System.out.println();
	}
}
