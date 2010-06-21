package msc.jkuat.mobile.web.ui;

import msc.jkuat.mobile.IF.CustomerAccountIF;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Button;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

public class Transfer extends ForwardComposer {
	private Button btnCancel = null;
	@SuppressWarnings("unused")
	private Button btnTransfer = null;
	private Decimalbox txtAmount = null;
	private Listbox lstAcsFrom = null;
	private Listbox lstAcsTo = null;
	private Logger log = Logger.getLogger(getClass());
	
	public void onClick$btnCancel() {
		String nxt = (String) btnCancel.getAttribute("target");
		switchTo(nxt);
	}
	
	public void onClick$btnTransfer() {
		if (txtAmount.getValue() == null) {
			throw new WrongValueException(txtAmount, "Should not be blank");
		}
		CustomerAccountIF caTo = (CustomerAccountIF) lstAcsTo.getSelectedItem().getValue();
		CustomerAccountIF caFrom = (CustomerAccountIF) lstAcsFrom.getSelectedItem().getValue();
		try {
			caFrom.transferFundsTo(caTo, txtAmount.getValue());
		} catch (Exception e) {
			log.error("onClick$btnTransfer", e);
			throw new WrongValueException(txtAmount, e.getMessage());
		}
		try {
			Messagebox.show("Amount Transfered");
		} catch (InterruptedException e) {
			log.error("Messagebox.show", e);
		}
		//TODO make this configurable on the ZUL file
		//do not switch
		/*String nxt = (String) btnTransfer.getAttribute("target");
		switchTo(nxt);*/
	}
	
	
	/*private void switchTo(String nxt) {
		try {
			self.detach();
			Window wnd = (Window) Executions.createComponents(nxt, this.self.getParent(), null);
			page.getFellow("content").setAttribute("current", wnd.getId());
		} catch (Exception e) {
			log.error("switchTo", e);
		}
	}*/
	
	/**
	 * validation is now onClick, not on change
	 */
	
	/*public void onChange$txtAmount() {
		manipulateBtnChange();
	}

	private void manipulateBtnChange() {
		if (txtAmount.getValue() != null) {
			btnTransfer.setDisabled(false);
		}
	}*/
}
