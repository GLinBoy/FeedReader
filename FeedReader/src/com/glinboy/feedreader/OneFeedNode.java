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
import com.sun.syndication.feed.synd.SyndFeed;
import java.awt.Image;
import javax.swing.Action;
import org.openide.actions.DeleteAction;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

public class OneFeedNode extends FilterNode {

    OneFeedNode(Node feedFileNode, SyndFeed feed) {
        super(feedFileNode, Children.create(new FeedChildFactory(feed), false), Lookups.fixed(feed));
    }

    @Override
    public String getDisplayName() {
        return getLookup().lookup(SyndFeed.class).getTitle();
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/myorg/feedreader/rss16.gif");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{SystemAction.get(DeleteAction.class)};
    }
}
