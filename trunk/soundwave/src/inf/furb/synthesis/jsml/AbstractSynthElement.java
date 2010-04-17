package inf.furb.synthesis.jsml;


abstract class AbstractSynthElement implements ISynthElement {

	private String mark;
	protected String text;

	@Override
	public final String getMark() {
		return mark;
	}

	@Override
	public final String getText() {
		return text;
	}


	@Override
	public final void setMark(String mark) {
		this.mark = mark;
	}

	public String toString() {
		return this.getText();
	};
}
