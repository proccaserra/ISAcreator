/**
 ISAcreator is a component of the ISA software suite (http://www.isa-tools.org)

 License:
 ISAcreator is licensed under the Common Public Attribution License version 1.0 (CPAL)

 EXHIBIT A. CPAL version 1.0
 �The contents of this file are subject to the CPAL version 1.0 (the �License�);
 you may not use this file except in compliance with the License. You may obtain a
 copy of the License at http://isa-tools.org/licenses/ISAcreator-license.html.
 The License is based on the Mozilla Public License version 1.1 but Sections
 14 and 15 have been added to cover use of software over a computer network and
 provide for limited attribution for the Original Developer. In addition, Exhibit
 A has been modified to be consistent with Exhibit B.

 Software distributed under the License is distributed on an �AS IS� basis,
 WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 the specific language governing rights and limitations under the License.

 The Original Code is ISAcreator.
 The Original Developer is the Initial Developer. The Initial Developer of the
 Original Code is the ISA Team (Eamonn Maguire, eamonnmag@gmail.com;
 Philippe Rocca-Serra, proccaserra@gmail.com; Susanna-Assunta Sansone, sa.sanson@gmail.com;
 http://www.isa-tools.org). All portions of the code written by the ISA Team are
 Copyright (c) 2007-2011 ISA Team. All Rights Reserved.

 EXHIBIT B. Attribution Information
 Attribution Copyright Notice: Copyright (c) 2008-2011 ISA Team
 Attribution Phrase: Developed by the ISA Team
 Attribution URL: http://www.isa-tools.org
 Graphic Image provided in the Covered Code as file: http://isa-tools.org/licenses/icons/poweredByISAtools.png
 Display of Attribution Information is required in Larger Works which are defined in the CPAL as a work which combines Covered Code or portions thereof with code not governed by the terms of the CPAL.

 Sponsors:
 The ISA Team and the ISA software suite have been funded by the EU Carcinogenomics project (http://www.carcinogenomics.eu), the UK BBSRC (http://www.bbsrc.ac.uk), the UK NERC-NEBC (http://nebc.nerc.ac.uk) and in part by the EU NuGO consortium (http://www.nugo.org/everyone).
 */

package org.isatools.isacreator.mgrast.ui;

import org.isatools.isacreator.common.UIHelper;
import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * MGRastConceptListCellRenderer
 *
 * @author eamonnmaguire
 * @date Sep 28, 2010
 */


public class MGRastConceptListCellRenderer extends JComponent
        implements ListCellRenderer {


    @InjectedResource
    private ImageIcon mappedTo, notMappedTo;
    // renderer will show which MGrast concept is mapped to the selected
    // ISAtab file term.
    private DefaultListCellRenderer listCellRenderer;


    public MGRastConceptListCellRenderer() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        ResourceInjector.get("exporters-package.style").inject(this);

        add(new CellImage(), BorderLayout.WEST);

        listCellRenderer = new DefaultListCellRenderer();


        add(listCellRenderer, BorderLayout.CENTER);

        setBorder(new EmptyBorder(2, 2, 2, 2));
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean cellGotFocus) {
        listCellRenderer.getListCellRendererComponent(list, value, index,
                selected, cellGotFocus);
        listCellRenderer.setBorder(null);

        //image.checkIsIdEntered(selected);
        Component[] components = getComponents();

        for (Component c : components) {
            ((JComponent) c).setToolTipText(value.toString());
            if (c instanceof CellImage) {
                ((CellImage) c).setSelected(selected);
            } else {
                if (selected) {
                    c.setForeground(UIHelper.GREY_COLOR);
                    c.setBackground(UIHelper.BG_COLOR);
                    c.setFont(UIHelper.VER_11_BOLD);

                } else {
                    c.setForeground(UIHelper.LIGHT_GREEN_COLOR);
                    c.setBackground(UIHelper.BG_COLOR);
                    c.setFont(UIHelper.VER_11_BOLD);
                }
            }
        }


        return this;
    }

    class CellImage extends JPanel {
        // this will contain the general panel layout for the list item and modifier elements to allow for changing of images
        // when rendering an item as being selected and so forth.
        private JLabel itemSelectedIndicator;

        CellImage() {
            setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            itemSelectedIndicator = new JLabel(notMappedTo);

            add(itemSelectedIndicator);
            add(Box.createHorizontalStrut(2));
        }

        public void setSelected(boolean selected) {

            if (selected) {
                itemSelectedIndicator.setIcon(mappedTo);
            } else {
                itemSelectedIndicator.setIcon(notMappedTo);
            }
        }
    }
}
