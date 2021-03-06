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
 
 package org.openconcerto.erp.core.finance.payment.action;

import org.openconcerto.erp.action.CreateFrameAbstractAction;
import org.openconcerto.erp.core.common.ui.PanelFrame;
import org.openconcerto.erp.core.finance.accounting.element.MouvementSQLElement;
import org.openconcerto.erp.core.finance.accounting.ui.SuppressionEcrituresPanel;
import org.openconcerto.sql.Configuration;
import org.openconcerto.sql.element.SQLElement;
import org.openconcerto.sql.model.SQLRow;
import org.openconcerto.sql.view.IListFrame;
import org.openconcerto.sql.view.ListeAddPanel;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ListeDesChequesAction extends CreateFrameAbstractAction {

    private final SQLElement element;

    public ListeDesChequesAction(final String name, final String tableName) {
        super(name);
        this.element = Configuration.getInstance().getDirectory().getElement(tableName);
    }

    @Override
    public JFrame createFrame() {
        final IListFrame frame = new IListFrame(new ListeAddPanel(this.element) {
            @Override
            protected void handleAction(JButton source, ActionEvent evt) {
                if (this.getListe().getSelectedId() > 1) {
                    SQLRow row = this.getListe().fetchSelectedRow();
                    if (source == this.buttonModifier) {
                        MouvementSQLElement.showSource(row.getInt("ID_MOUVEMENT"));
                    } else {
                        if (source == this.buttonEffacer) {
                            PanelFrame frameDelete = new PanelFrame(new SuppressionEcrituresPanel(row.getInt("ID_MOUVEMENT")), "Suppression");
                            frameDelete.pack();
                            frameDelete.setLocationRelativeTo(null);
                            frameDelete.setResizable(false);
                            frameDelete.setVisible(true);
                        }
                    }
                } else {
                    super.handleAction(source, evt);
                }
            }
        });
        frame.getPanel().getListe().setSQLEditable(false);
        frame.getPanel().setAddVisible(false);
        return frame;
    }
}
