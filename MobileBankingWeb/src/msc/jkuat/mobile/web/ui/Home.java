/*
   Copyright [2010] [Eric Njogu]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package msc.jkuat.mobile.web.ui;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;


public class Home extends GenericForwardComposer {
	/**logging facility*/
	Logger log = Logger.getLogger(getClass());
	
	/**handle the onclick event for the label*/
	public void onClick$lblLogout() {
		String logon = (String) self.getAttribute("logon");
		switchTo(logon);
	}
	
	public void onClick$lblChgPin() {
		String nxt = (String) self.getAttribute("changePin");
		switchTo(nxt);
	}
	
	public void onClick$lblFrx() {
		String nxt = (String) self.getAttribute("forex");
		switchTo(nxt);
	}
	
	public void onClick$lblAcs() {
		String nxt = (String) self.getAttribute("accounts");
		switchTo(nxt);
	}
	
	public void onClick$lblOps() {
		String nxt = (String) self.getAttribute("ops");
		switchTo(nxt);
	}

	private void switchTo(String nxt) {
		//log.debug("Logon is " + logon);
		try {
			self.detach();
			@SuppressWarnings("unused")
			Window wnd = (Window) Executions.createComponents(nxt, this.self.getParent(), null);
		} catch (Exception e) {
			log.error("switchTo", e);
		}
	}
}
