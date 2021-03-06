/*
 * File    : TagReplaceEditorPanel.java
 * Created : 02-oct-2006 18:30
 * By      : fbusquets
 *
 * JClic - Authoring and playing system for educational activities
 *
 * Copyright (C) 2000 - 2006 Francesc Busquets & Departament
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

package edu.xtec.jclic.automation.tagreplace;

import edu.xtec.jclic.Activity;
import edu.xtec.jclic.automation.AutoContentProviderEditor;
import edu.xtec.jclic.edit.Editor;
import edu.xtec.jclic.edit.EditorPanel;
import edu.xtec.jclic.fileSystem.FileSystem;
import edu.xtec.jclic.misc.Utils;
import edu.xtec.util.Options;
import edu.xtec.util.StrUtils;
import java.util.EventObject;

/**
 *
 * @author Francesc Busquets (fbusquets@xtec.cat)
 * @version 13.08.09
 */
public class TagReplaceEditorPanel extends EditorPanel {
    
    /** Creates new form TagReplaceEditorPanel */
    public TagReplaceEditorPanel(Options options) {
        super(options);
        
        initComponents();
        postInit(250, false, false);        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        tagPanel = new javax.swing.JPanel();
        tagStartLb = new javax.swing.JLabel();
        tagStartTx = new javax.swing.JTextField();
        tagEndLb = new javax.swing.JLabel();
        tagEndTx = new javax.swing.JTextField();
        filePanel = new javax.swing.JPanel();
        fileLb = new javax.swing.JLabel();
        fileTx = new javax.swing.JTextField();
        browseBtn = new javax.swing.JButton();
        charsetLb = new javax.swing.JLabel();
        charsetCombo = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        tagPanel.setLayout(new java.awt.GridBagLayout());

        tagPanel.setBorder(new javax.swing.border.TitledBorder(options.getMsg("edit_tagRep_tags")));
        tagStartLb.setLabelFor(tagStartTx);
        tagStartLb.setText(options.getMsg("edit_tagRep_tagStart"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        tagPanel.add(tagStartLb, gridBagConstraints);

        tagStartTx.setColumns(5);
        tagStartTx.setText(TagReplace.DEFAULT_TAG_START);
        tagStartTx.setToolTipText(options.getMsg("edit_tagRep_tagStart_tooltip"));
        tagStartTx.getDocument().addDocumentListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        tagPanel.add(tagStartTx, gridBagConstraints);

        tagEndLb.setLabelFor(tagEndTx);
        tagEndLb.setText(options.getMsg("edit_tagRep_tagEnd"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        tagPanel.add(tagEndLb, gridBagConstraints);

        tagEndTx.setColumns(5);
        tagEndTx.setText(TagReplace.DEFAULT_TAG_END);
        tagEndTx.setToolTipText(options.getMsg("edit_tagRep_tagEnd_tooltip"));
        tagEndTx.getDocument().addDocumentListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        tagPanel.add(tagEndTx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(tagPanel, gridBagConstraints);

        filePanel.setLayout(new java.awt.GridBagLayout());

        filePanel.setBorder(new javax.swing.border.TitledBorder(options.getMsg("edit_tagRep_file")));
        fileLb.setLabelFor(fileTx);
        fileLb.setText(options.getMsg("edit_tagRep_fileName"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        filePanel.add(fileLb, gridBagConstraints);

        fileTx.setText(TagReplace.DEFAULT_TAG_START);
        fileTx.setToolTipText(options.getMsg("edit_tagRep_fileName_tooltip"));
        fileTx.getDocument().addDocumentListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        filePanel.add(fileTx, gridBagConstraints);

        browseBtn.setText(options.getMsg("edit_tagRep_browseFile"));
        browseBtn.setToolTipText(options.getMsg("edit_tagRep_browseFile_tooltip"));
        browseBtn.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        filePanel.add(browseBtn, gridBagConstraints);

        charsetLb.setLabelFor(charsetCombo);
        charsetLb.setText(options.getMsg("edit_tagRep_charset"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        filePanel.add(charsetLb, gridBagConstraints);

        charsetCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UTF8", "UTF-16", "ISO8859_1", "ASCII" }));
        charsetCombo.setSelectedItem(TagReplace.DEFAULT_CHARSET);
        charsetCombo.setToolTipText(options.getMsg("edit_tagRep_charset_tooltip"));
        charsetCombo.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        filePanel.add(charsetCombo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(filePanel, gridBagConstraints);

    }//GEN-END:initComponents

    public boolean checkIfEditorValid(Editor e) {
        return e instanceof TagReplaceEditor;        
    }    
    
    public TagReplaceEditor getTagReplaceEditor(){
        return (TagReplaceEditor)getEditor();
    }
    
    public TagReplace getTagReplace(){
        TagReplaceEditor tred=getTagReplaceEditor();
        return tred==null ? null : (TagReplace)tred.getAutoContentProvider();
    }
    
    
    protected void fillData() {
        TagReplace tr=getTagReplace();
        tagStartTx.setText(tr==null ? "" : tr.tagStart);
        tagEndTx.setText(tr==null ? "" : tr.tagEnd);
        fileTx.setText(tr==null ? "" : tr.mapFileName);        
        charsetCombo.setSelectedItem(tr==null ? TagReplace.DEFAULT_CHARSET : tr.fileCharset);
        setModified(false);
    }
    
    protected void saveData() {
        TagReplace tr=getTagReplace();
        if(tr!=null){
            tr.tagStart=tagStartTx.getText();
            tr.tagEnd=tagEndTx.getText();
            tr.mapFileName=StrUtils.nullableString(fileTx.getText());
            tr.fileCharset=(String)charsetCombo.getSelectedItem();
        }   
    }
    
    
    @Override
    public boolean eventPerformed(EventObject ev){
        
        Object cmp=ev==null ? null : ev.getSource();
        boolean result=(cmp!=null);
        
        if(cmp==browseBtn){
            selectTextFile();
        }        
        
        return result;
    }
    
    
    protected void selectTextFile(){
        TagReplaceEditor tred=getTagReplaceEditor();
        if(tred!=null){
            Activity act=tred.getActivity();
            if(act!=null){
                FileSystem fs=act.getProject().getFileSystem();
                int[] filters=new int[]{Utils.TEXT_FF};
                String[] files=fs.chooseFiles(null, false, filters, options, "edit_find_media", this, true, false);
                if(files!=null && files.length>0 && files[0]!=null){
                    fileTx.setText(files[0]);
                    setModified(true);
                }
            }
        }
    }
        
    @Override
    protected javax.swing.Icon getIcon(){
        return AutoContentProviderEditor.getIcon();
    }
    
    @Override
    protected String getTitle(){
        return options.getMsg("edit_tagRep_title");
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseBtn;
    private javax.swing.JComboBox charsetCombo;
    private javax.swing.JLabel charsetLb;
    private javax.swing.JLabel fileLb;
    private javax.swing.JPanel filePanel;
    private javax.swing.JTextField fileTx;
    private javax.swing.JLabel tagEndLb;
    private javax.swing.JTextField tagEndTx;
    private javax.swing.JPanel tagPanel;
    private javax.swing.JLabel tagStartLb;
    private javax.swing.JTextField tagStartTx;
    // End of variables declaration//GEN-END:variables
    
}
