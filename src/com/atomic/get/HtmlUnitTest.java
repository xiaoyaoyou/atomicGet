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
		//����ҳ���˲���ʡ
	    final HtmlPage page = webClient.getPage("http://www.vmall.com/");
	    //�����¼���˲���ʡ
	    HtmlAnchor loginAnchor1 = page.getAnchorByText("[��¼]");
	    //������֤��ͼƬ
	    HtmlPage loginPage = loginAnchor1.click();
	    HtmlImage CaptchaImg = (HtmlImage)loginPage.getByXPath("//img[@id='randomCodeImg']").get(0);
	    CaptchaImg.saveAs(new File("img\\captcha.gif"));
	    
	    //��д�û���
	    HtmlDivision userNameDiv =  (HtmlDivision)loginPage.getByXPath("//label[@for='login_userName']/div").get(0);
	    userNameDiv.setTextContent("yukim.2008@163.com");
	    //��д����
	    HtmlDivision userPWDDiv =  (HtmlDivision)loginPage.getByXPath("//label[@for='login_password']/div").get(0);
	    userPWDDiv.setTextContent("123456789");
	    HtmlDivision captchaDiv =  (HtmlDivision)loginPage.getByXPath("//label[@for='randomCode']/div").get(0);
	    //��д��֤��
	    captchaDiv.setTextContent("gggg");
	    HtmlSubmitInput loginButton = (HtmlSubmitInput)loginPage.getByXPath("//input[@class='button-login']").get(0);
	    //�����¼��ť���˲�û�з�Ӧ����Ҫ��Դ��
	    loginButton.click();

	    webClient.closeAllWindows();
	    
	}
}

