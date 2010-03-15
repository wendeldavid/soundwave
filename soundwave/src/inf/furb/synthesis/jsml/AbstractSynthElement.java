package inf.furb.synthesis.jsml;

import java.util.List;

abstract class AbstractSynthElement implements ISynthElement {

	private String mark;
	protected String text;

	@Override
	public abstract List<IAttribute> getAttributes();

	@Override
	public abstract List<ISynthElement> getInnerElements();

	@Override
	public final String getMark() {
		return mark;
	}

	@Override
	public abstract String getName();

	@Override
	public final String getText() {
		return text;
	}

	@Override
	public abstract void setAttribute(String name, String value);

	@Override
	public final void setMark(String mark) {
		this.mark = mark;
	}

}
