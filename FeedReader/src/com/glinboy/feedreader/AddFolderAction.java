/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glinboy.feedreader;

/**
 *
 * @author hojjat
 */
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.loaders.DataFolder;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(id = "org.myorg.feedreader.AddFolderAction", category = "RootActions")
@ActionRegistration(displayName = "#FN_addfolderbutton")
@Messages("FN_addfolderbutton=Add Folder")
public class AddFolderAction extends AbstractAction {

    private final DataFolder folder;

    public AddFolderAction(DataFolder df) {
        folder = df;
    }

    @Messages({"FN_askfolder_msg=Enter the folder name", "FN_askfolder_title=New Folder"})
    @Override
    public void actionPerformed(ActionEvent ae) {
        NotifyDescriptor.InputLine nd = new NotifyDescriptor.InputLine(Bundle.FN_askfolder_msg(), Bundle.FN_askfolder_title(), NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE);
        Object result = DialogDisplayer.getDefault().notify(nd);
        if (result.equals(NotifyDescriptor.OK_OPTION)) {
            final String folderString = nd.getInputText();
            try {
                DataFolder.create(folder, folderString);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
