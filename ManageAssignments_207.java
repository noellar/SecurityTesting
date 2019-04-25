package unitn.sectest;

	import net.sourceforge.jwebunit.api.IElement;
	import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
	import net.sourceforge.jwebunit.junit.WebTester;

	import org.junit.Before;
	import org.junit.Test;
	import org.xml.sax.helpers.AttributesImpl;

	import com.gargoylesoftware.htmlunit.html.DomElement;
	import com.gargoylesoftware.htmlunit.html.HtmlElement;
	import com.gargoylesoftware.htmlunit.html.InputElementFactory;

	public class ManageAssignments_207 {
		
	private WebTester tester;

		@Before
	    public void setup(){
	        tester = new WebTester();  
	        tester.setBaseUrl("http://localhost/schoolmate");
	    }
		
		@Test
		public void test1(){
			tester.beginAt("index.php");
	        tester.setTextField("username", "Jack");
	        tester.setTextField("password", "password@1");
	        tester.submit();
	        
	        tester.assertMatch("Jack Porter's Classes");
	        tester.clickLinkWithText("Security Testing");
	        
	        tester.assertMatch("Class Settings");
	        tester.clickLinkWithText("Assignments");
	        
	        tester.assertMatch("Manage Assignments");

        }
	}

