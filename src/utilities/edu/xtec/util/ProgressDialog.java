/*
 * File    : ProgressDialog.java
 * Created : 10-dec-2002 14:40
 * By      : fbusquets
 *
 * JClic - Authoring and playing system for educational activities
 *
 * Copyright (C) 2000 - 2005 Francesc Busquets & Departament
 * d'Educacio de la Generalitat de Catalunya
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details (see the LICENSE file).
 */

package edu.xtec.util;

import java.io.InputStream;
import javax.swing.*;

/**
 *
 * @author Francesc Busquets (fbusquets@xtec.cat)
 * @version 13.08.29
 */
public class ProgressDialog extends ExtendedJDialog implements ProgressInputStream.ProgressInputStreamListener{
    
    protected Options options=null;
    protected edu.xtec.util.SwingWorker worker=null;    
    protected boolean progressActive=false;
    protected boolean cancellable=false;
    protected boolean saving=false;
    
    /** Creates new form ProgressDialog */
    public ProgressDialog(JComponent parent, Options options) {
        //super(parent, "", true);
        super(parent, "", true);
        this.options=options;        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        fileLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        mainPanel.setLayout(new java.awt.GridBagLayout());

        label.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 5);
        mainPanel.add(label, gridBagConstraints);

        fileLabel.setText(" ");
        fileLabel.setFont(new java.awt.Font("Dialog", 0, 12));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        mainPanel.add(fileLabel, gridBagConstraints);

        progressBar.setPreferredSize(new java.awt.Dimension(250, 22));
        progressBar.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        mainPanel.add(progressBar, gridBagConstraints);

        cancelButton.setText(options.getMsg("CANCEL"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        mainPanel.add(cancelButton, gridBagConstraints);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

        if(cancellable && worker!=null){
            worker.cancel();
            //worker.interrupt();
        }

    }//GEN-LAST:event_cancelButtonActionPerformed

    
    public void start(String titleKey, String msgKey, edu.xtec.util.SwingWorker worker, boolean showProgressBar, boolean cancellable, boolean saving){
        this.worker=worker;
        this.cancellable=cancellable;
        this.saving=saving;
        setTitle(titleKey!=null ? options.getMsg(titleKey) : "");
        label.setText(options.getMsg(msgKey!=null ? msgKey : "WORKING"));
        cancelButton.setVisible(cancellable && worker!=null);
        progressBar.setVisible(showProgressBar);
        setProgressValue(0);
        mainPanel.revalidate();
        pack();
        centerOver(getParent());
        if(worker!=null){
            worker.startLater();
        }
        setVisible(true);        
    }
    
    public void setText(String txt){
        label.setText(txt);
        mainPanel.revalidate();
    }    
    
    public void setFileLabel(String txt){
        fileLabel.setText(txt==null ? "" : txt);
    }
    
    public void startProgress(String name){
        setProgressValue(0);
        name=(name==null ? "" : name.replace('\\', '/'));
        int i=name.lastIndexOf('/');
        if(i>0)
            name=name.substring(i+1);
        fileLabel.setText(name==null ? "" : options.getMsg(saving ? "SAVING_FILE" : "LOADING_FILE")+" "+name);
        progressActive=true;
    }
    
    public void endProgress() {
        progressActive=false;
    }
    
    public void setProgressMax(int max) {
        progressBar.setMaximum(max);
    }
    
    public void setProgressValue(int value) {
        progressBar.setValue(value);
    }
    
    public InputStream getProgressInputStream(InputStream is, int expectedLength, String name){
        InputStream result;        
        if(!progressActive){
            ProgressInputStream pi=new ProgressInputStream(is, expectedLength, name);
            pi.addProgressInputStreamListener(this);
            result=pi;
        }
        else{
            result=is;
        }
        return result;
    }            
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel fileLabel;
    private javax.swing.JLabel label;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
    
}
