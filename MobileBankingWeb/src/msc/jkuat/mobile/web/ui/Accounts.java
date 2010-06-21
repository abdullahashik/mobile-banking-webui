package msc.jkuat.mobile.web.ui;

import java.util.HashMap;
import java.util.Map;

import msc.jkuat.mobile.IF.CustomerAccountIF;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;


public class Accounts extends ForwardComposer {
	Logger log = Logger.getLogger(getClass());
	Listbox lstForex = null;
	Label lblMsg = null;
	
	/**not in use. The menu now holds the operations
	 * as opposed to a central home screen
	 * @param nxt
	 * @param arg
	 */
	/*public void onClick$lblHome() {
		String nxt = (String) self.getAttribute("home");
		switchTo(nxt, null);
	}*/

	/*private void switchTo(String nxt, Map<String, Object> arg) {
		//log.debug("Logon is " + logon);
		try {
			self.detach();
			Window wnd = (Window) Executions.createComponents(nxt, this.self.getParent(), arg);
			page.getFellow("content").setAttribute("current", wnd.getId());
		} catch (Exception e) {
			log.error("switchTo", e);
		}
		
	}*/
	
	public void onSelect$lstForex() {
		CustomerAccountIF ca = (CustomerAccountIF) lstForex.getSelectedItem().getValue();
		lblMsg.setValue("Interest:" + ca.getInterestRate() +"% Min Bal:" + ca.getMinimumBalance());
		
	}
	
	public void onClick$btnStmt() {
		if (lstForex.getSelectedItem() != null) {
			CustomerAccountIF ca = (CustomerAccountIF) lstForex.getSelectedItem().getValue();
			log.debug("stmt for " + ca.getNumber());
			if (ca.getMiniStatement() == null) {
				lblMsg.setValue(ca.getNumber() + " has no transactions");
			} else {
				Map<String, Object> map = new HashMap<String, Object>(1);
				map.put("account", ca);
				switchTo((String) self.getAttribute("stmt"), map);
			}
		} else {
			throw new WrongValueException(lstForex, "Please select an account from one in the list");
		}
	}
}
