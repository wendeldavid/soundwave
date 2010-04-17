package inf.furb.synthesis.mbrola.converter;

import inf.furb.synthesis.jsml.Break;
import inf.furb.synthesis.jsml.ISynthElement;

class BreakConverter implements IConverter {

	@Override
	public void convert(ISynthElement element, StringBuilder output) {
		output.append("_ ").append(element.getAttribute(Break.TIME).getValue()).append("\n");
	}

}
