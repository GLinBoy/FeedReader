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
import java.util.List;
import javax.swing.Action;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Utilities;

public class RootNode extends FilterNode {

    public RootNode(Node filterNode) throws DataObjectNotFoundException {
        super(filterNode, new RssFolderChildren(filterNode));
    }

    @Override
    public Action[] getActions(boolean bln) {
        List<? extends Action> rootActions = Utilities.actionsForPath("Actions/RootActions");
        return rootActions.toArray(new Action[rootActions.size()]);
    }
}
