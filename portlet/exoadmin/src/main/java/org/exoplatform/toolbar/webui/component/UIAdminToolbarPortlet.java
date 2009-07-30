package org.exoplatform.toolbar.webui.component;

import org.exoplatform.portal.config.UserACL;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

@ComponentConfig(
		lifecycle = UIApplicationLifecycle.class,
		template = "app:/groovy/admintoolbar/webui/component/UIAdminToolbarPortlet.gtmpl"
)
public class UIAdminToolbarPortlet extends UIPortletApplication {
	public static final int ADMIN              = 2;

	public static final int EDITOR             = 1;

	public static final int REDACTOR           = 0;

	public static final int VISITOR           = -1;

	/** The role of the current user. it can be VISITOR, REDACTOR, EDITOR or ADMINISTRATOR */
	private int role = VISITOR;  

	public UIAdminToolbarPortlet() throws Exception {
		ConversationState currentState = ConversationState.getCurrent();
		if(currentState == null) return;
		UserACL userACL = getApplicationComponent(UserACL.class);

		String userId = Util.getPortalRequestContext().getRemoteUser();
		if (userACL.getSuperUser().equals(userId)) {
			role = UIAdminToolbarPortlet.ADMIN;
			return;
		}

		role = UIAdminToolbarPortlet.ADMIN;
		role = UIAdminToolbarPortlet.VISITOR;		
	}

	public int getRole() throws Exception {    
		return role;
	}
}
