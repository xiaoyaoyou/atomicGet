package com.atomic.get;


import java.io.File;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class HtmlUnitTest {
	
	private static Logger logger = Logger.getLogger(HtmlUnitTest.class.getName());
	
	public static void huaweiTest() {
		try{
			final WebClient webClient = new WebClient();
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.setJavaScriptTimeout(5000);
			//打开主页，此步可省
			final HtmlPage page = webClient.getPage("http://www.vmall.com/");
			//点击登录，此步可省
			HtmlAnchor loginAnchor1 = page.getAnchorByText("[登录]");
			//保存验证码图片
			HtmlPage loginPage = loginAnchor1.click();
			HtmlImage CaptchaImg = (HtmlImage)loginPage.getByXPath("//img[@id='randomCodeImg']").get(0);
			CaptchaImg.saveAs(new File("img\\captcha.gif"));
			
			//填写用户名
			HtmlDivision userNameDiv =  (HtmlDivision)loginPage.getByXPath("//label[@for='login_userName']/div").get(0);
			userNameDiv.setTextContent("yukim.2008@163.com");
			//填写密码
			HtmlDivision userPWDDiv =  (HtmlDivision)loginPage.getByXPath("//label[@for='login_password']/div").get(0);
			userPWDDiv.setTextContent("123456789");
			HtmlDivision captchaDiv =  (HtmlDivision)loginPage.getByXPath("//label[@for='randomCode']/div").get(0);
			//填写验证码
			captchaDiv.setTextContent("gggg");
			HtmlSubmitInput loginButton = (HtmlSubmitInput)loginPage.getByXPath("//input[@class='button-login']").get(0);
			//点击登录按钮，此步没有反应，需要跟源码
			loginButton.click();
			
			webClient.closeAllWindows();
			
		}catch(Exception e){
			logger.error("", e);
		}
	}
	
	public static void xiaomiTest() {
		try{
			final WebClient webClient = new WebClient();
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.setJavaScriptTimeout(10000);
			//打开主页，此步可省
			final HtmlPage page = webClient.getPage("https://account.xiaomi.com/pass/serviceLogin?callback=http%3A%2F%2Forder.mi.com%2Flogin%2Fcallback%3Ffollowup%3Dhttp%253A%252F%252Fwww.mi.com%26sign%3DMjMyMGJhNjNmZmM2NTc0YWM4NzdkN2IzMjNlZjhmMzhhODAxMDZiNg%2C%2C&sid=mi_eshop");
			System.out.println(page.asText());
			//点击登录，此步可省
//			HtmlAnchor loginAnchor1 = page.getAnchorByText("登录");
			
			Set<Cookie>  cookies = webClient.getCookieManager().getCookies();
			Iterator<Cookie> cookiesIterator = cookies.iterator();
			while(cookiesIterator.hasNext()){
				Cookie temp = cookiesIterator.next();
				logger.debug(temp.getName() + " : " + temp.getValue() + " : " + temp.getExpires());
			}
			
//			HtmlPage loginPage = loginAnchor1.click();
			
			
			webClient.closeAllWindows();
		}catch(Exception e) {
			logger.error("", e);
		}
	}
	
	public static void main(String[] args) throws Exception{
		HtmlUnitTest.xiaomiTest();
	}
}

