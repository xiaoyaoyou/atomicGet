package com.atomic.get;


import java.io.File;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;


public class HtmlUnitTest {
	public static void main(String[] args) throws Exception{
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
	    
	}
}

