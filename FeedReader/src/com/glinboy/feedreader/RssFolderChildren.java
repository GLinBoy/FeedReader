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
import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

public class RssFolderChildren extends FilterNode.Children {

    RssFolderChildren(Node rssFolderNode) {
        super(rssFolderNode);
    }

    @Override
    protected Node[] createNodes(Node n) {
        FileObject fo = n.getLookup().lookup(FileObject.class);
        if (fo != null && fo.isFolder()) {
            try {
                return new Node[]{new RootNode(n)};
            } catch (DataObjectNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            }
        } else {
            Feed feed = getFeed(fo.getLookup());
            if (feed != null) {
                try {
                    return new Node[]{new OneFeedNode(n, feed.getSyndFeed())};
                } catch (IOException ioe) {
                    Exceptions.printStackTrace(ioe);
                }
            }
        }
// best effort
        return new Node[]{new FilterNode(n)};
    }

    /**
     * * Looking up a feed
     */
    private static Feed getFeed(Lookup lookup) {
        Feed f = FileUtil.getConfigObject("RssFeeds/sample.instance", Feed.class);
        if (f == null) {
            throw new IllegalStateException("Bogus file in feeds folder: " + lookup.lookup(FileObject.class));
        }
        return f;
    }
}
