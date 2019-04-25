package unitn.sectest;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.WebTester;

public class ViewAnnouncements_148 {
	
	
	private WebTester tester;
    
    @Before
    public void setup(){
        tester = new WebTester();  
        tester.setBaseUrl("http://localhost/schoolmate");
		tester.beginAt("index.php");
		tester.setTextField("username", "Jack");
        tester.setTextField("password", "password@1");
        tester.submit();
        tester.assertMatch("Jack Porter's Classes");
        tester.clickLinkWithText("Security Testing");
        tester.assertMatch("Class Settings");
        tester.clickLinkWithText("Announcements");
        tester.assertMatch("View Announcements");
      }

    
    @Test
	public void page(){
		tester.setWorkingForm("teacher");
        tester.setTextField("page", "2'> <a href =http://unitn.it>malicious link</a> <br'");
        tester.clickLinkWithExactText("Announcements");
        tester.assertMatch("View Announcements");     
        tester.assertLinkNotPresentWithText("malicious link");
	}
    
	 @Test
	 public void onpage(){
		 tester.setWorkingForm("announcements");
		 tester.setTextField("onpage", "1'> <a href =http://unitn.it>malicious link</a> <br'");
		 addSubmitButton("//form[@name='announcements']");			 
		 tester.submit();
		 
		 tester.assertMatch("View Announcements"); 
		 tester.assertLinkNotPresentWithText("malicious link"); 
	     }
	
	 @Test
	 public void page2(){
		 tester.setWorkingForm("teacher");
		 tester.setTextField("page2", "9'> <a href =http://unitn.it>malicious link</a> <br'");
		 addSubmitButton("//form[@name='teacher']");
		 tester.submit();
		 tester.assertMatch("View Announcements"); 
		 tester.assertLinkNotPresentWithText("malicious link"); 
	     }
	 
		
		private void addSubmitButton(String fromXpath){
			   IElement element = tester.getElementByXPath(fromXpath);
			   DomElement form= ((HtmlUnitElementImpl)element).getHtmlElement();
			   InputElementFactory factory = InputElementFactory.instance;
			   AttributesImpl attributes =  new AttributesImpl();
			   attributes.addAttribute("", "", "type", "", "submit");
			   HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
			   form.appendChild(submit);
		}			
}



