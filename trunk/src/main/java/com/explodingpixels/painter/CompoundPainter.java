package com.explodingpixels.painter;

import java.awt.*;

/**
 * An implementation of {@link Painter} that calls a series of {@code Painter}s in succession. {@code ComponentPainter}
 * does not do any painting itself.
 */
public class CompoundPainter<T> implements Painter<T> {

    private Painter<T>[] fPainters;

    /**
     * Creates a {@link Painter} that calls the given {@code Painter}s in the order they are supplied when
     * {@link #paint(java.awt.Graphics2D, Object, int, int)} is called.
     *
     * @param painters the {@code Painter}s to delegate to.
     */
    public CompoundPainter(Painter<T>... painters) {
        fPainters = painters;
    }

    public void paint(Graphics2D graphics, T objectToPaint, int width, int height) {
        for (Painter<T> painter : fPainters) {
            Graphics2D graphicsCopy = (Graphics2D) graphics.create();
            painter.paint(graphicsCopy, objectToPaint, width, height);
            graphicsCopy.dispose();
        }
    }
}
