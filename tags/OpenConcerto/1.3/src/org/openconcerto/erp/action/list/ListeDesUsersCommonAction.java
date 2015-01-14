/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2011 OpenConcerto, by ILM Informatique. All rights reserved.
 * 
 * The contents of this file are subject to the terms of the GNU General Public License Version 3
 * only ("GPL"). You may not use this file except in compliance with the License. You can obtain a
 * copy of the License at http://www.gnu.org/licenses/gpl-3.0.html See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each file.
 */
 
 package org.openconcerto.erp.action.list;

import org.openconcerto.erp.action.CreateFrameAbstractAction;
import org.openconcerto.sql.Configuration;
import org.openconcerto.sql.view.IListFrame;
import org.openconcerto.sql.view.ListeAddPanel;

import javax.swing.Action;
import javax.swing.JFrame;


public class ListeDesUsersCommonAction extends CreateFrameAbstractAction {

    public ListeDesUsersCommonAction() {
        super();
        this.putValue(Action.NAME, "Listes des utilisateurs");
    }

    public JFrame createFrame() {
        final IListFrame listFrame = new IListFrame(new ListeAddPanel(Configuration.getInstance().getDirectory().getElement("USER_COMMON")));
        System.err.println("Element Name " + listFrame.getPanel().getElement());

        return listFrame;
    }
}