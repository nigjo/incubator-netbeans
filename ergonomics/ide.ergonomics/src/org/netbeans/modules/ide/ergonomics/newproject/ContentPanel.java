/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.ide.ergonomics.newproject;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import org.netbeans.modules.ide.ergonomics.fod.FeatureManager;
import org.openide.util.RequestProcessor;

public final class ContentPanel extends JPanel {
    static String FINDING_MODULES = "finding-modules";
    private String name = null;

    /** Creates new form InstallMissingModulesWizardVisualPanel1 */
    public ContentPanel (String name) {
        initComponents();
        this.name = name;
        this.spTable.setVisible (false);
        this.tModules.setVisible (false);
    }

    public @Override String getName() {
        return name;
    }

    @Override
    public void addNotify () {
        super.addNotify ();
        FeatureManager.getInstance().create(new Runnable () {
            public void run () {
                firePropertyChange (FINDING_MODULES, null, Boolean.TRUE);
            }
        }).schedule(200);
    }

    private void doReplaceComponents (JComponent... comps) {
        assert pCentral != null;
        assert SwingUtilities.isEventDispatchThread () : "Must be called in EQ.";
        pCentral.removeAll ();
        pCentral.repaint ();
        addComponents (comps);
    }
    
    private void doAddComponents (JComponent... comps) {
        assert pCentral != null;
        assert SwingUtilities.isEventDispatchThread () : "Must be called in EQ.";
        if (comps != null) {
            for (JComponent c : comps) {
                if (c != null) {
                    pCentral.add (c);
                }
            }
        }
        pCentral.revalidate ();
        revalidate ();
    }
    
    public void replaceComponents (final JComponent... comps) {
        if (SwingUtilities.isEventDispatchThread ()) {
            doReplaceComponents (comps);
        } else {
            SwingUtilities.invokeLater (new Runnable () {
                public void run () {
                    doReplaceComponents (comps);
                }
            });
        }
    }
    
    public void addComponents (final JComponent... comps) {
        if (SwingUtilities.isEventDispatchThread ()) {
            doAddComponents (comps);
        } else {
            SwingUtilities.invokeLater (new Runnable () {
                public void run () {
                    doAddComponents (comps);
                }
            });
        }
    }
    
    private static String prepareToolTip (String original) {
        String res = "";
        res = "<html>" + original.replaceAll (",", "<br>")+ "</html>"; // NOI18N
        return res;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pCentral = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tModules = new JTable () {
            public Component prepareRenderer (TableCellRenderer renderer,
                int rowIndex,
                int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (c instanceof JComponent && vColIndex != 0) {
                    JComponent jc = (JComponent) c;
                    jc.setToolTipText (prepareToolTip ((String) getValueAt (rowIndex, vColIndex)));
                }
                return c;
            }
        };

        pCentral.setLayout(new javax.swing.BoxLayout(pCentral, javax.swing.BoxLayout.Y_AXIS));

        tModules.setRowSelectionAllowed(false);
        spTable.setViewportView(tModules);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pCentral;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable tModules;
    // End of variables declaration//GEN-END:variables

}

