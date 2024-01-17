package com.ggr.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;

public class Ring {
	private DoubleProperty width;
	private DoubleProperty height;
	private double leftOffset;
	private int order;

	public Ring(double height, double width, double leftOffset, int order) {
		this.height = new SimpleDoubleProperty(height);
		this.width = new SimpleDoubleProperty(width);
		this.leftOffset = leftOffset;
		this.order = order;
	}

	public Ring(Ring ring) {
		this.height = ring.height;
		this.width = ring.width;
		this.leftOffset = ring.leftOffset;
		this.order = ring.order;
	}

	@Override
	public String toString() {
		return "Ring [width=" + width + ", height=" + height + ", leftOffset=" + leftOffset + ", order=" + order + "]";
	}

	public double getWidth() {
		return width.get();
	}

	public void setWidth(double width) {
		this.width.set(width);
	}

	public double getHeight() {
		return height.get();
	}

	public void setHeight(double height) {
		this.height.set(height);
	}

	public double getLeftOffset() {
		return leftOffset;
	}

	public void setLeftOffset(double leftOffset) {
		this.leftOffset = leftOffset;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public DoubleProperty widthProperty() {
		return width;
	}

	public DoubleProperty heightProperty() {
		return height;
	}
}
